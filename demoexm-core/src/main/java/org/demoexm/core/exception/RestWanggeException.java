package org.demoexm.core.exception;


public class RestWanggeException  extends IafclubException{

	private static final long serialVersionUID = 1L;
	
	public String code;
	
	public RestWanggeException(){
		super();
	}
	

	public RestWanggeException(String errorCodeDesc) {
		super(errorCodeDesc);
	}

	public RestWanggeException(String errorCode, String errorCodeDesc) {
		super(errorCode, errorCodeDesc);
		this.setCode(errorCode);
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	
}
