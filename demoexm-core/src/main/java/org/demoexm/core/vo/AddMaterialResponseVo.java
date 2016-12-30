package org.demoexm.core.vo;

/**
 * 新增永久素材返回
* @author zhangfeng
* @date 2016年12月2日 下午2:55:29 
*
 */
public class AddMaterialResponseVo extends ResponseVo {
	private static final long serialVersionUID = 1L;

	private String media_id;
	
	private String url;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
