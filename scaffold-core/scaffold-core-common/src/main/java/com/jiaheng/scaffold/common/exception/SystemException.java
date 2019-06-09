/**  
 * @Title: SystemException.java
 * @Package com.jiaheng.scaffold.p2p.common.exception
 * 
 * @author yangdeke@jianbing.com
 * @date 2017-7-18
 */
package com.jiaheng.scaffold.common.exception;


/**
 * 系统授权异常类
 * 
 * @author yangdeke@jianbing.com
 * @date 2017-7-18
 */
public class SystemException extends BaseException {

	public SystemException(BaseResultCodeEnum resultCodeEnum) {
		super(resultCodeEnum);
	}

	public SystemException(Integer code, String message, Integer outCode) {
		super(code, message, outCode);
	}

	public SystemException(String message) {
		super(message);
	}
}
