package com.jiaheng.scaffold.route.operate.shiro;

import com.jiaheng.scaffold.common.util.StringUtil;
import com.jiaheng.scaffold.route.operate.util.Servlets;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义授权会话管理类
 * Created by 张嘉恒 on 2018/4/12.
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

    private String keyPrefix = "merchant:session:";


    @Override
    protected Serializable doCreate(Session session) {
        HttpServletRequest request = Servlets.getRequest();
        if (request != null){
            String uri = request.getServletPath();
            // 如果是静态文件，则不创建SESSION
            if (Servlets.isStaticFile(uri)){
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
            /*Session s = (Session)JedisUtils.getObject(keyPrefix + sessionId);
            return s;*/
            return null;
        }
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        HttpServletRequest request = Servlets.getRequest();
        if (request != null){
            String uri = request.getServletPath();
            // 如果是静态文件，则不更新SESSION
            if (Servlets.isStaticFile(uri)){
                return;
            }
            // 如果是视图文件，则不更新SESSION
            if (StringUtil.startsWith(uri, ".ftl")
                    && StringUtil.endsWith(uri, ".ftl")){
                return;
            }
        }

        this.saveSession(session);
    }

    private void saveSession(Session session) throws UnknownSessionException {
        if (session != null && session.getId() != null) {
            int timeoutSeconds = (int)(session.getTimeout() / 1000);
            //JedisUtils.setObject(keyPrefix + session.getId(),session,timeoutSeconds);
        } else {
            logger.error("session or session id is null");
        }
    }

    @Override
    public void delete(Session session) {
        if (session != null && session.getId() != null) {
            //JedisUtils.delObject(keyPrefix + session.getId());
        } else {
            logger.error("session or session id is null");
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet();
        /*Set<byte[]> keys = JedisUtils.hkeys(this.keyPrefix + "*");
        if (keys != null && keys.size() > 0) {
            Iterator i$ = keys.iterator();

            while(i$.hasNext()) {
                byte[] key = (byte[])i$.next();
                Session s = (Session)JedisUtils.getObject((String)JedisUtils.getObjectKey(key));
                sessions.add(s);
            }
        }*/

        return sessions;
    }

    public String getKeyPrefix() {
        return this.keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
