package org.demoexm.core.vo;

public class AuthRequestVo extends BaseVo {

	/* 公众号的唯一标识 */
	private String appid;
	/* 公众号的appsecret */
	private String secret;
	/* 填写第一步获取的code参数 */
	private String code;
	/* 填写为authorization_code */
	private String grant_type;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

}
