/**  
 * @Title: Md5CredentialsMatcher.java
 * @package com.cms.scaffold.route.operate.common.security.shiro
 * 
 * @author zhangjiahengpoping@gmail.com
 * @date 2017-7-15
 */
package com.cms.scaffold.route.operate.config;


import com.cms.scaffold.common.util.MD5;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 重写密码MD5加密比较
 * @author zhangjiahengpoping@gmail.com
 * @date 2017-7-15
 */
public class Md5CredentialsMatcher extends SimpleCredentialsMatcher{

	/**
	 * 
	 * @param token
	 * @param info
	 * @return   
	 * @author zhangjiahengpoping@gmail.com
	 * @date 2017-7-15
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
        Object tokenCredentials = getCredentials(token);
        Object accountCredentials = getCredentials(info);

        return equals(MD5.encode(new String((char[])tokenCredentials)).toUpperCase(), accountCredentials) ||
				equals(tokenCredentials,accountCredentials);
	}


}
