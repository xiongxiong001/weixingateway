package org.demoexm.core.exception;

/**自定义异常：中信接口异常类
 * 
 * @author chenweixian
 *
 */
public class GatewayCnbcException  extends IafclubException{

	public GatewayCnbcException(){
		super();
	}

	public GatewayCnbcException(String errorCodeDesc) {
		super(errorCodeDesc);
	}

	public GatewayCnbcException(String errorCode, String errorCodeDesc) {
		super(errorCode, errorCodeDesc);
	}

}
