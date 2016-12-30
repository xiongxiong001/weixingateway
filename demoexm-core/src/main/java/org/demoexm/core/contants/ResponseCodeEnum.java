package org.demoexm.core.contants;

public enum ResponseCodeEnum {

	UNKNOWN_EXCEPTION						(-3,"未知异常"),
	HTTP_TIME_OUT							(-2,"请求超时"),
	SYSTEM_BUSY								(-1,"系统繁忙，此时请开发者稍候再试"),
	SUCCESS									(0,"请求成功"),
	INVALID_CREDENTIAL						(40001,"不合法的调用凭证"),
	INVALID_GRANT_TYPE						(40002,"不合法的grant_type"),
	INVALID_OPENID							(40003,"不合法的OpenID"),
	INVALID_MEDIA_TYPE                  	(40004,"不合法的媒体文件类型"),
	INVALID_MEDIA_ID                    	(40007,"不合法的media_id"),
	INVALID_MESSAGE_TYPE                   	(40008,"不合法的message_type"),
	INVALID_IMAGE_SIZE                     	(40009,"不合法的图片大小"),
	INVALID_VOICE_SIZE                     	(40010,"不合法的语音大小"),
	INVALID_VIDEO_SIZE                     	(40011,"不合法的视频大小"),
	INVALID_THUMB_SIZE                     	(40012,"不合法的缩略图大小"),
	INVALID_APPID                          	(40013,"不合法的AppID"),
	INVALID_ACCESS_TOKEN                   	(40014,"不合法的access_token"),
	INVALID_MENU_TYPE                      	(40015,"不合法的菜单类型"),
	INVALID_BUTTON_SIZE                    	(40016,"不合法的菜单按钮个数"),
	INVALID_BUTTON_TYPE                    	(40017,"不合法的按钮类型"),
	INVALID_BUTTON_NAME_SIZE               	(40018,"不合法的按钮名称长度"),
	INVALID_BUTTON_KEY_SIZE                	(40019,"不合法的按钮KEY长度"),
	INVALID_BUTTON_URL_SIZE                	(40020,"不合法的url长度"),
	INVALID_SUB_BUTTON_SIZE                	(40023,"不合法的子菜单按钮个数"),
	INVALID_SUB_BUTTON_TYPE                	(40024,"不合法的子菜单类型"),
	INVALID_SUB_BUTTON_NAME_SIZE           	(40025,"不合法的子菜单按钮名称长度"),
	INVALID_SUB_BUTTON_KEY_SIZE            	(40026,"不合法的子菜单按钮KEY长度"),
	INVALID_SUB_BUTTON_URL_SIZE            	(40027,"不合法的子菜单按钮url长度"),
	INVALID_CODE                           	(40029,"不合法或已过期的code"),
	INVALID_REFRESH_TOKEN                  	(40030,"不合法的refresh_token"),
	INVALID_TEMPLATE_ID_SIZE               	(40036,"不合法的template_id长度"),
	INVALID_TEMPLATE_ID                    	(40037,"不合法的template_id"),
	INVALID_URL_SIZE                       	(40039,"不合法的url长度"),
	INVALID_URL_DOMAIN                     	(40048,"不合法的url域名"),
	INVALID_SUB_BUTTON_URL_DOMAIN          	(40054,"不合法的子菜单按钮url域名"),
	INVALID_BUTTON_URL_DOMAIN              	(40055,"不合法的菜单按钮url域名"),
	INVALID_URL                            	(40066,"不合法的url"),
	ACCESS_TOKEN_MISSING                   	(41001,"缺失access_token参数"),
	APPID_MISSING                          	(41002,"缺失appid参数"),
	REFRESH_TOKEN_MISSING                  	(41003,"缺失refresh_token参数"),
	APPSECRET_MISSING                      	(41004,"缺失secret参数"),
	MEDIA_DATA_MISSING                     	(41005,"缺失二进制媒体文件"),
	MEDIA_ID_MISSING                       	(41006,"缺失media_id参数"),
	SUB_MENU_DATA_MISSING                  	(41007,"缺失子菜单数据"),
	MISSING_CODE                           	(41008,"缺失code参数"),
	MISSING_OPENID                         	(41009,"缺失openid参数"),
	MISSING_URL                            	(41010,"缺失url参数"),
	ACCESS_TOKEN_EXPIRED                   	(42001,"access_token超时"),
	REFRESH_TOKEN_EXPIRED                  	(42002,"refresh_token超时"),
	CODE_EXPIRED                           	(42003,"code超时"),
	REQUIRE_GET_METHOD                     	(43001,"需要使用GET方法请求"),
	REQUIRE_POST_METHOD                    	(43002,"需要使用POST方法请求"),
	REQUIRE_HTTPS                          	(43003,"需要使用HTTPS"),
	REQUIRE_SUBSCRIBE                      	(43004,"需要订阅关系"),
	EMPTY_MEDIA_DATA                       	(44001,"空白的二进制数据"),
	EMPTY_POST_DATA                        	(44002,"空白的POST数据"),
	EMPTY_NEWS_DATA                        	(44003,"空白的news数据"),
	EMPTY_CONTENT                          	(44004,"空白的内容"),
	EMPTY_LIST_SIZE                        	(44005,"空白的列表"),
	MEDIA_SIZE_OUT_OF_LIMIT                	(45001,"二进制文件超过限制"),
	CONTENT_SIZE_OUT_OF_LIMIT              	(45002,"content参数超过限制"),
	TITLE_SIZE_OUT_OF_LIMIT                	(45003,"title参数超过限制"),
	DESCRIPTION_SIZE_OUT_OF_LIMIT          	(45004,"description参数超过限制"),
	URL_SIZE_OUT_OF_LIMIT                  	(45005,"url参数长度超过限制"),
	PICURL_SIZE_OUT_OF_LIMIT            	(45006,"picurl参数超过限制"),
	PLAYTIME_OUT_OF_LIMIT                  	(45007,"播放时间超过限制（语音为60s最大）"),
	ARTICLE_SIZE_OUT_OF_LIMIT              	(45008,"article参数超过限制"),
	API_FREQ_OUT_OF_LIMIT                  	(45009,"接口调动频率超过限制"),
	CREATE_MENU_LIMIT                      	(45010,"建立菜单被限制"),
	API_LIMIT                              	(45011,"频率限制"),
	TEMPLATE_SIZE_OUT_OF_LIMIT             	(45012,"模板大小超过限制"),
	CANOT_MODIFY_SYS_GROUP                 	(45016,"不能修改默认组"),
	SYS_GROUP_NAME_TOO_LONG				   	(45017,"修改组名过长"),
	TOO_MANY_GROUP						   	(45018,"组数量过多"),
	API_UNAUTHORIZED                       	(50001,"接口未授权");
		
	private int errcode;
	private String descript;
	
	ResponseCodeEnum(int errcode,String descript){
		this.errcode = errcode;
		this.descript = descript;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	 public static String getDescriptByCode(int errcode)
	    {
	        for (ResponseCodeEnum e : values())
	        {
	            if (errcode == e.getErrcode())
	                return e.getDescript();
	        }
	        return null;
	    }
	

	
	
	
	
	
	
}
