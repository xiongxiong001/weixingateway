package org.demoexm.core.vo;

import java.util.Map;

/**发送模板消息
 * 
 * @author : chewneixian 陈惟鲜
 * @create_date 2016年10月20日 下午3:34:48
 *
 */
public class TemplateMessageSendRequest extends BaseVo {
	
	/** 用户的唯一标识OpenId */
	private String openid;
	/** 使用模板ID */
	private String template_id;
	/** 点击消息跳转链接 */
	private String url;
	/** 内容信息，根据不同的模板内容填充不同的信息 */
	private Map<String, String> data;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
}
