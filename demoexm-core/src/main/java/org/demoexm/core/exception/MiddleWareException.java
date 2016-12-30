package org.demoexm.core.exception;

/**自定义异常：中间件异常类
 * 
 * @author chenweixian
 *
 */
public class MiddleWareException  extends IafclubException{

	public MiddleWareException(){
		super();
	}

	public MiddleWareException(String errorCodeDesc) {
		super(errorCodeDesc);
	}

	public MiddleWareException(String errorCode, String errorCodeDesc) {
		super(errorCode, errorCodeDesc);
	}
}
