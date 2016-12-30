package org.demoexm.core.exception;

/**自定义异常：车险分期网关异常类
 * 
 * @author chenweixian
 *
 */
public class GatewayInstalmentException  extends IafclubException{

	public GatewayInstalmentException(){
		super();
	}

	public GatewayInstalmentException(String errorCodeDesc) {
		super(errorCodeDesc);
	}

	public GatewayInstalmentException(String errorCode, String errorCodeDesc) {
		super(errorCode, errorCodeDesc);
	}

}
