package com.cms.scaffold.route.operate.util;

import com.cms.scaffold.common.constant_manual.BasicsConstantManual;
import com.cms.scaffold.sys.sys.domain.SysOperate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

/**
 * Created by zjh on 2018/2/13.
 */
public class UserUtil {


    /**
     * 获取授权主要对象
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static Session getSession(){
        try{
            SecurityManager securityManager = ThreadContext.getSecurityManager();
            if(securityManager == null){
                return null;
            }

            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
        }catch (InvalidSessionException e){

        }
        return null;
    }

    /**
     * 从Session获取当前用户信息
     *
     * @return
     */
    public static SysOperate getOperatorFromSession() {
        if(getSession() != null){
            Object attribute = getSession().getAttribute(BasicsConstantManual.SESSION_ATTRIBUTE_KEY_OPERATOR);
            return attribute == null ? null : (SysOperate) attribute;
        }

        return  null;
    }


    /**
     * 获取当前登录者对象
     */
    public static Object getPrincipal(){
        Subject subject = SecurityUtils.getSubject();

        return subject.getPrincipal();
    }

    public static String getPartnerId(){
        SysOperate sysOperate = getOperatorFromSession();
        if(sysOperate == null){
            return null;
        }
        if(sysOperate.getPartnerId() ==null){
            return null;
        }


        return String.valueOf(sysOperate.getPartnerId());
    }

}


