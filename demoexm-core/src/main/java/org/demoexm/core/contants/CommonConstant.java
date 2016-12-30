package org.demoexm.core.contants;

import org.demoexm.core.util.ConfigPropertiesUtil;

public class CommonConstant {

	/**HTTP连接超时时间和socket连接时默认5秒*/
	public final static int HTTP_TIMEOUT_MILLS_5000 = 5000; 
	/**授权获取openid,access_token的url*/
	public final static String WECHAT_URL_GET_AUTH = ConfigPropertiesUtil.getProp("wechat.url.get.auth");
	/**获取用户信息url*/
	public final static String WECHAT_URL_GET_USERINFO = ConfigPropertiesUtil.getProp("wechat.url.get.userinfo");
	/**获取公众号access_token的url,用于批量拉去用户信息*/
	public final static String WECHAT_URL_GET_PUBLICTOKEN = ConfigPropertiesUtil.getProp("wechat.url.get.publictoken");
	/**批量获取用户信息url*/
	public final static String WECHAT_URL_POST_BATCHUSERINFOLIST = ConfigPropertiesUtil.getProp("wechat.url.post.batchuserinfolist");
	/**发送模板消息url*/
	public final static String POST_TEMPLATE_SEND = ConfigPropertiesUtil.getProp("wechat.url.post.template.send");
	/**获取公众号用户列表*/
	public final static String GET_USER_LIST = ConfigPropertiesUtil.getProp("wechat.url.get.user.list");
	/**获取自定义菜单列表*/
	public final static String GET_MENU_LIST = ConfigPropertiesUtil.getProp("wechat.url.get.menu.list");
	/**创建自定义菜单*/
	public final static String CREATE_MENU = ConfigPropertiesUtil.getProp("wechat.url.post.create.menu");
	/**删除自定义菜单*/
	public final static String DELETE_MENU = ConfigPropertiesUtil.getProp("wechat.url.post.delete.menu");
	/**#获取一个素材*/
	public final static String MATERIAL_ONE = ConfigPropertiesUtil.getProp("wechat.url.post.material.one");
	/**#分类型获取素材的列表*/
	public final static String MATERIAL_LIST = ConfigPropertiesUtil.getProp("wechat.url.post.material.list");
	/**#分类型获取素材的总数*/
	public final static String MATERIAL_COUNT = ConfigPropertiesUtil.getProp("wechat.url.get.material.count");
	/**获取accessToken文件全路径*/
	public final static String FILE_ACCESS_TOKEN_PATH = ConfigPropertiesUtil.getProp("wechat.access.token.path");
	/**#新增永久图文素材*/
	public final static String NEWS_ADD = ConfigPropertiesUtil.getProp("wechat.url.post.news.add");
	/**#新增其他类型永久素材*/
	public final static String MATERIAL_ADD = ConfigPropertiesUtil.getProp("wechat.url.post.material.add");	
	
}
