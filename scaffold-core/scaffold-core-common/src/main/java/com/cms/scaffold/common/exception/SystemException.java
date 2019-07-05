/**  
 * @Title: SystemException.java
 * @Package com.gjj.p2p.common.exception
 * 
 * @author zjh
 * @date 2017-7-18
 */
package com.cms.scaffold.common.exception;


/**
 * 系统授权异常类
 * 
 * @author zjh
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
