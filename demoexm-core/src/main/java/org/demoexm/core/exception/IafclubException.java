package org.demoexm.core.exception;

/**自定义异常
 * 
 * @author chenweixian
 *
 */
public class IafclubException  extends RuntimeException{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**错误码
     */
    private String errorCode;
    
    /**错误描述
     */
    private String errorCodeDesc;
    
    public IafclubException() {
        super();
    }
    
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for 
     *          later retrieval by the {@link #getMessage()} method.
     */
    public IafclubException(String message) {
        super(message);
    }
    
    public IafclubException(String errorCode, String errorCodeDesc) {
        super(errorCodeDesc);
    	this.errorCode = errorCode;
    	this.errorCodeDesc = errorCodeDesc;
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCodeDesc() {
		return errorCodeDesc;
	}

	public void setErrorCodeDesc(String errorCodeDesc) {
		this.errorCodeDesc = errorCodeDesc;
	}

}
