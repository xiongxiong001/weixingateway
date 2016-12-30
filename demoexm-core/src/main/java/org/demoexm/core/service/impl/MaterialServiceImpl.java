package org.demoexm.core.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.demoexm.core.contants.CommonConstant;
import org.demoexm.core.contants.ControllerContants;
import org.demoexm.core.contants.StatusContants;
import org.demoexm.core.exception.GatewayWeChatException;
import org.demoexm.core.exception.IafclubException;
import org.demoexm.core.service.MaterialService;
import org.demoexm.core.util.HttpClientUtil;
import org.demoexm.core.util.MyStringUtils;
import org.demoexm.core.vo.AddMaterialResponseVo;
import org.demoexm.core.vo.MaterialCountResponseVo;
import org.demoexm.core.vo.MaterialNewsResponse;
import org.demoexm.core.vo.MaterialNewsVo;
import org.demoexm.core.vo.StringResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/***发送模板消息文档
 * 
 * @author 陈惟鲜
 * @date 2016年10月20日 下午12:53:58
 *
 */
@Service
public class MaterialServiceImpl implements MaterialService
{
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private HttpClientUtil httpClientUtil;
    /**获取单个素材
     * 
     * @param access_token token地址
     * @param media_id 素材ID
     * @return
     * @author : chewneixian 陈惟鲜
     * @create_date 2016年11月23日 下午5:43:32
     */
    public StringResponseVo materialOne(String access_token, String media_id){
    	String message = "获取单个素材";
        logger.info(message + ControllerContants.MESSAGE_START);
        
    	String httpUrl = CommonConstant.MATERIAL_ONE;// 获得链接信息
    	// 更改替换链接信息
    	httpUrl = httpUrl.replace("ACCESS_TOKEN_PARAMETER", access_token);
        try {
			String response = httpClientUtil.sendHttpPostJson(httpUrl, "{\"media_id\":\""+media_id+"\"}");
			StringResponseVo stringResponseVo = (StringResponseVo) JSONObject.toBean(JSONObject.fromObject(response), StringResponseVo.class);
			if (StringUtils.isEmpty(stringResponseVo.getErrmsg())){// 成功
				stringResponseVo.setContent(response);
			}
			return stringResponseVo;
        } catch (IOException e) {
			logger.error(message+"异常", e);
			throw new IafclubException(message+"异常:"+MyStringUtils.getExceptionDetail(e));
		}finally{
	        logger.info(message + ControllerContants.MESSAGE_END);
		}
    }
    

    /**获取批量素材
     * 
     * @param access_token token
     * @param type	 是	 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset	 是	 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count	 是	 返回素材的数量，取值在1到20之间
     * @return 素材集合
     * @author : chewneixian 陈惟鲜
     * @create_date 2016年11月23日 下午5:43:57
     */
    public MaterialNewsResponse materialList(String access_token, String type, int offset, int count){
    	String message = "获取批量素材";
        logger.info(message + ControllerContants.MESSAGE_START);
        
    	String httpUrl = CommonConstant.MATERIAL_LIST;// 获得链接信息
    	// 更改替换链接信息
    	httpUrl = httpUrl.replace("ACCESS_TOKEN_PARAMETER", access_token);
    	MaterialNewsResponse newsResponse = new MaterialNewsResponse();
        try { 
			String response = httpClientUtil.sendHttpPostJson(httpUrl, "{\"type\":\""+type+"\",\"offset\":\""+offset+"\",\"count\":\""+count+"\"}");
			JSONObject json = JSONObject.fromObject(response);
			if(null != json.get("errmsg") &&
					StringUtils.isNotBlank(json.get("errmsg").toString())){
				newsResponse.setErrcode(Integer.valueOf( json.get("errcode").toString() )); 
				newsResponse.setErrmsg(json.get("errmsg").toString());
			}else{
				//图文素材
				if(type.equals(StatusContants.WECHAT_MATERIAL_TYPE_TYPE_NEWS.getIndex())){
					newsResponse.setMaterialType(StatusContants.WECHAT_MATERIAL_TYPE_TYPE_NEWS.getIndex());
					analyzeMaterialType(json,newsResponse);
				}
				
				
			}
			return newsResponse;
        } catch (Exception e) {
			logger.error(message+"异常", e);
			throw new IafclubException(message+"异常:"+MyStringUtils.getExceptionDetail(e));
		} finally{
	        logger.info(message + ControllerContants.MESSAGE_END);
		}
    }


    /**
     * 解析图文素材
     * @param json
     * @return
     * @author zhangfeng
     * @date 2016年12月6日 下午4:37:33
     */
	private void analyzeMaterialType(JSONObject json,MaterialNewsResponse response) {
		 
		List<MaterialNewsVo> newsMaterials = new ArrayList<MaterialNewsVo>();
		
		JSONArray itemArray = JSONArray.fromObject(json.get("item"));
		for(int i = 0 ; i < itemArray.size() ; i++){
			JSONObject itemObject = (JSONObject) itemArray.get(i);
			String media_id = (String) itemObject.get("media_id");
			
			JSONObject contentJson = JSONObject.fromObject(itemObject.get("content"));
			JSONArray newItemArray = JSONArray.fromObject(contentJson.get("news_item"));
			Date createTime = new Date(Long.valueOf(contentJson.getString("create_time")) * 1000);
			Date updateTime = new Date(Long.valueOf(contentJson.getString("update_time")) * 1000);
			MaterialNewsVo bean =  null ;
			for(int j = 0 ; j < newItemArray.size() ; j++){
				bean = new MaterialNewsVo();
				JSONObject newItemObject = (JSONObject) newItemArray.get(j);
				bean.setMedial_id(media_id);
				bean.setTitle(newItemObject.getString("title"));
				bean.setAuthor(newItemObject.getString("author"));
				bean.setDigest(newItemObject.getString("digest"));
				bean.setContent(newItemObject.getString("content"));
				bean.setContent_source_url(newItemObject.getString("content_source_url"));
				bean.setThumb_media_id(newItemObject.getString("thumb_media_id"));
				bean.setShow_cover_pic(newItemObject.getString("show_cover_pic"));
				bean.setUrl(newItemObject.getString("url"));
				bean.setThumb_url(newItemObject.getString("thumb_url"));
				bean.setCreateTime(createTime);
				bean.setUpdateTime(updateTime);
				newsMaterials.add(bean);
			}
		}
		response.setNewsMaterials(newsMaterials); 
	}
    

    /**获取素材总数
     * voice_count	 语音总数量
		video_count	 视频总数量
		image_count	 图片总数量
		news_count	 图文总数量
     * */
    public MaterialCountResponseVo materialCount(String access_token){
    	String message = "获取总数";
        logger.info(message + ControllerContants.MESSAGE_START);
        
    	String httpUrl = CommonConstant.MATERIAL_COUNT;// 获得链接信息
    	// 更改替换链接信息
    	httpUrl = httpUrl.replace("ACCESS_TOKEN_PARAMETER", access_token);
        try {
			String response = httpClientUtil.sendHttpGet(httpUrl);
			MaterialCountResponseVo responseVo = (MaterialCountResponseVo) JSONObject.toBean(JSONObject.fromObject(response), MaterialCountResponseVo.class);
			return responseVo;
        } catch (IOException e) {
			logger.error(message+"异常", e);
			throw new IafclubException(message+"异常:"+MyStringUtils.getExceptionDetail(e));
		}finally{
	        logger.info(message + ControllerContants.MESSAGE_END);
		}
    }
   
//    /**
//     * 新增图文素材
//     * @param access_token
//     * @return
//     * @author zhangfeng
//     * @date 2016年12月1日 下午3:38:17
//     */
//    public String addNews(String access_token){
//    	String mediaID = "";
//    	String message = "新增图文素材";
//        logger.info(message + ControllerContants.MESSAGE_START);
//        
//    	String httpUrl = CommonConstant.NEWS_ADD;// 获得链接信息
//    	// 更改替换链接信息
//    	httpUrl = httpUrl.replace("ACCESS_TOKEN_PARAMETER", access_token);
//        try {
//			String response = httpClientUtil.sendHttpsPostJson(httpUrl, null);
//			MaterialCountResponseVo responseVo = (MaterialCountResponseVo) JSONObject.toBean(JSONObject.fromObject(response), MaterialCountResponseVo.class);
//			return responseVo;
//        } catch (IOException e) {
//			logger.error(message+"异常", e);
//			throw new IafclubException(message+"异常:"+MyStringUtils.getExceptionDetail(e));
//		}finally{
//	        logger.info(message + ControllerContants.MESSAGE_END);
//		}
//    	
//    	
//    	return mediaID;
//    }
    
    /**
     * 新增其他类型永久素材
     * @param access_token
     * @param filePath 文件地址
     * @param title	 标题
     * @param introduction	描述信息
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @return
     * @author zhangfeng
     * @date 2016年12月2日 下午3:53:55
     */
    public AddMaterialResponseVo addMaterial(String access_token,String filePath,String title,String introduction,String type){
    	String message = "新增其他类型永久素材";
        logger.info(message + ControllerContants.MESSAGE_START);
        
    	String httpUrl = CommonConstant.MATERIAL_ADD;// 获得链接信息
    	// 更改替换链接信息
    	httpUrl = httpUrl.replace("ACCESS_TOKEN_PARAMETER", access_token);
    	httpUrl = httpUrl+"&type="+type;
    	FileInputStream input  = null;
    	try {
    		String response = httpClientUtil.sendWeChatMaterial(filePath, type, httpUrl,title, introduction);
			AddMaterialResponseVo responseVo = (AddMaterialResponseVo) JSONObject.toBean(JSONObject.fromObject(response), AddMaterialResponseVo.class);
			return responseVo;
        }  catch (Exception e) {
			logger.error(message+"异常", e);
			throw new GatewayWeChatException(message+"异常:"+MyStringUtils.getExceptionDetail(e));
		}finally{
			if(null != input){
				try {
					input.close();
				} catch (IOException e) {
					logger.error(message+"异常", e);
					throw new GatewayWeChatException(message+"异常:"+MyStringUtils.getExceptionDetail(e));
				}
			}
	        logger.info(message + ControllerContants.MESSAGE_END);
		}
    	
    }
    
}
