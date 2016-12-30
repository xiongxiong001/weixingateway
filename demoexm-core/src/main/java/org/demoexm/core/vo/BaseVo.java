package org.demoexm.core.vo;

import java.io.Serializable;

public class BaseVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	 
    /**
     * 
     */
    /* 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同 */
    private String access_token;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}
