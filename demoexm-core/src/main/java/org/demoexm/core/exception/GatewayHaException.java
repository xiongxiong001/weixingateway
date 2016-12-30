package org.demoexm.core.exception;

/**自定义异常：华安接口异常类
 * 
 * @author chenweixian
 *
 */
public class GatewayHaException  extends IafclubException{

	public GatewayHaException(){
		super();
	}

	public GatewayHaException(String errorCodeDesc) {
		super(errorCodeDesc);
	}

	public GatewayHaException(String errorCode, String errorCodeDesc) {
		super(errorCode, errorCodeDesc);
	}


}
