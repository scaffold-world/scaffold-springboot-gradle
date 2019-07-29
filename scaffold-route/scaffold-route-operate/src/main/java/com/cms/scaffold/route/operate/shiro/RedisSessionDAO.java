package com.cms.scaffold.route.operate.shiro;

import com.cms.scaffold.common.util.StringUtil;
import com.cms.scaffold.code.jedis.JedisUtils;
import com.cms.scaffold.route.operate.util.Servlets;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/** 自定义授权会话管理类 Created by dekeyang on 2018/4/12. */
@Component(value = "sessionDao")
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

    private static final String KEY_PREFIX = "sys:shiro:session:";

    @Value("${server.session.timeout:36000}")
    private Long sessionTimeOut;

    @Override
    protected Serializable doCreate(Session session) {
        HttpServletRequest request = Servlets.getRequest();
        if (request != null) {
            String uri = request.getServletPath();
            // 如果是静态文件，则不创建SESSION
            if (Servlets.isStaticFile(uri)) {
                return null;
            }
        }
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        } else {
            logger.debug("准备从redis中获取session sessionId = 『{}』", KEY_PREFIX + sessionId);
            return (Session) JedisUtils.getObject(KEY_PREFIX + sessionId, true);
        }
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        HttpServletRequest request = Servlets.getRequest();
        if (request != null) {
            String uri = request.getServletPath();
            // 如果是静态文件，则不更新SESSION
            if (Servlets.isStaticFile(uri)) {
                return;
            }
            // 如果是视图文件，则不更新SESSION
            if (StringUtil.startsWith(uri, ".ftl") && StringUtil.endsWith(uri, ".ftl")) {
                return;
            }
        }

        this.saveSession(session);
    }

    private void saveSession(Session session) throws UnknownSessionException {
        if (session != null && session.getId() != null) {
            logger.debug("更新或保存session: {}", KEY_PREFIX + session.getId());
            JedisUtils.setObject(KEY_PREFIX + session.getId(), session, sessionTimeOut.intValue(), true);
        } else {
            logger.error("session or session id is null");
        }
    }

    @Override
    public void delete(Session session) {
        if (session != null && session.getId() != null) {
            logger.debug("准备删除session：{}", KEY_PREFIX + session.getId());
            JedisUtils.delObject(KEY_PREFIX + session.getId());
        } else {
            logger.error("session or session id is null");
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet();
        Set<byte[]> keys = JedisUtils.hkeys(KEY_PREFIX + "*");
        if (keys != null && keys.size() > 0) {
            Iterator i$ = keys.iterator();

            while (i$.hasNext()) {
                byte[] key = (byte[]) i$.next();
                Session s = (Session) JedisUtils.getObject((String) JedisUtils.getObjectKey(key), true);
                sessions.add(s);
            }
        }

        return sessions;
    }
}
