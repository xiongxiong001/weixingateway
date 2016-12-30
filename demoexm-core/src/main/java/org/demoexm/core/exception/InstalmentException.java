package org.demoexm.core.exception;

/**自定义异常：车险分期系统异常类
 * 
 * @author chenweixian
 *
 */
public class InstalmentException  extends IafclubException{

	public InstalmentException(){
		super();
	}

	public InstalmentException(String errorCodeDesc) {
		super(errorCodeDesc);
	}

	public InstalmentException(String errorCode, String errorCodeDesc) {
		super(errorCode, errorCodeDesc);
	}

}
