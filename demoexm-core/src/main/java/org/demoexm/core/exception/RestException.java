package org.demoexm.core.exception;
/**
 * rest 自定义异常，该异常抛出后会以状态为500的的错误代码返回
* @author zhangfeng
* @date 2016年5月13日 下午2:14:31 
*
 */
public class RestException extends IafclubException {

	private static final long serialVersionUID = 1L;

	public RestException(){
		super();
	}

	public RestException(String errorCodeDesc) {
		super(errorCodeDesc);
	}

	public RestException(String errorCode, String errorCodeDesc) {
		super(errorCode, errorCodeDesc);
	}
	
	
}
