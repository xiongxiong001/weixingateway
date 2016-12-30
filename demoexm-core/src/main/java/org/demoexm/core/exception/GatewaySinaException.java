package org.demoexm.core.exception;

/**自定义异常：新浪接口异常类
 * 
 * @author chenweixian
 *
 */
public class GatewaySinaException  extends IafclubException{

	public GatewaySinaException(){
		super();
	}

	public GatewaySinaException(String errorCodeDesc) {
		super(errorCodeDesc);
	}

	public GatewaySinaException(String errorCode, String errorCodeDesc) {
		super(errorCode, errorCodeDesc);
	}

}
