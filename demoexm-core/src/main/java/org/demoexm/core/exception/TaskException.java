package org.demoexm.core.exception;

/**自定义异常：中信接口异常类
 * 
 * @author chenweixian
 *
 */
public class TaskException  extends IafclubException{

	public TaskException(){
		super();
	}

	public TaskException(String errorCodeDesc) {
		super(errorCodeDesc);
	}

	public TaskException(String errorCode, String errorCodeDesc) {
		super(errorCode, errorCodeDesc);
	}
}
