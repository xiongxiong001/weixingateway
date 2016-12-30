package org.demoexm.core.exception;

/**自定义异常：中信接口异常类
 * 
 * @author chenweixian
 *
 */
public class TradeException  extends IafclubException{

	public TradeException(){
		super();
	}

	public TradeException(String errorCodeDesc) {
		super(errorCodeDesc);
	}

	public TradeException(String errorCode, String errorCodeDesc) {
		super(errorCode, errorCodeDesc);
	}
}
