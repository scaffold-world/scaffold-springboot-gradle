package com.cms.scaffold.route.operate.shiro;

import com.cms.scaffold.sys.sys.domain.SysMenu;
import com.cms.scaffold.sys.sys.domain.SysOperate;
import com.cms.scaffold.sys.sys.domain.SysRoleOperate;
import com.cms.scaffold.sys.sys.service.SysMenuService;
import com.cms.scaffold.sys.sys.service.SysOperateService;
import com.cms.scaffold.sys.sys.service.SysRoleOperateService;
import com.cms.scaffold.sys.sys.service.SysRoleService;
import com.cms.scaffold.common.constant_manual.BasicsConstantManual;
import com.cms.scaffold.common.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroRealm  extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysOperateService sysOperateService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleOperateService sysRoleOperateService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String  username =(String) principalCollection.getPrimaryPrincipal();

        SysOperate sysOperate =sysOperateService.findByUserName(username);

        SysRoleOperate sysRoleOperate = sysRoleOperateService.selectByOperateId(sysOperate.getId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //日后走缓存
//        if(sysOperate.getPartnerId()!=null){
//            Partner basicsPartner = partnerFacade.selectById(sysOperate.getPartnerId());
//            if(basicsPartner!=null&&basicsPartner.getStatus()!=0){
//                logger.info("id:{}商户已被停用，请联系管理员",basicsPartner.getId());
//                return  info;
//            }
//        }

        //String key = String.format(RedisConstant.OPERATE_SYS_MENU_OPERATE,sysRoleOperate.getRoleId());

        //List<SysMenuReq> menuList = (List)JedisUtils.getObject(key);

        List<SysMenu> menuList = null;

        if(menuList==null){
            menuList = sysMenuService.findByOpId(sysOperate.getId());
            //JedisUtils.setObject(key,menuList,0);
        }
        if(menuList!=null && !menuList.isEmpty()){
            for(SysMenu menu: menuList){
                if(StringUtil.isNotBlank(menu.getCode())){
                    info.addStringPermission(menu.getCode());
                }
            }
        }

        return info;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        SysOperate operator = sysOperateService.findByUserName(username);

        if(operator==null) throw new UnknownAccountException();
        if (operator.getStatus()==null || operator.getStatus()==2) {
            throw new LockedAccountException(); // 帐号锁定
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, //用户
                operator.getPwd(), //密码
                ByteSource.Util.bytes(username),
                getName()  //realm name
        );
        //当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(BasicsConstantManual.SESSION_ATTRIBUTE_KEY_OPERATOR, operator);
        session.setAttribute(BasicsConstantManual.SESSION_ATTRIBUTE_KEY_OPERATOR_ID, operator.getId());
        return authenticationInfo;

    }

    /**
     * 清理权限缓存 防止加一次重启一次
     */
    public void clearCachedAuthorization(){
        //清空权限缓存
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
