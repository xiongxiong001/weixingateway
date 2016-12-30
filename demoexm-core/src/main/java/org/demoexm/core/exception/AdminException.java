package org.demoexm.core.exception;

/**自定义异常：中信接口异常类
 * 
 * @author chenweixian
 *
 */
public class AdminException  extends IafclubException{

	public AdminException(){
		super();
	}

	public AdminException(String errorCodeDesc) {
		super(errorCodeDesc);
	}

	public AdminException(String errorCode, String errorCodeDesc) {
		super(errorCode, errorCodeDesc);
	}


}
