package org.demoexm.core.exception;
/**
 * 自定义异常，微信异常
* @author zhangfeng
* @date 2016年12月2日 下午2:51:18 
*
 */
public class GatewayWeChatException extends IafclubException {
	
	public GatewayWeChatException(){
		super();
	}

	public GatewayWeChatException(String errorCodeDesc) {
		super(errorCodeDesc);
	}

	public GatewayWeChatException(String errorCode, String errorCodeDesc) {
		super(errorCode, errorCodeDesc);
	}

}
