package org.demoexm.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.demoexm.core.contants.ControllerContants;
import org.demoexm.core.util.FileUtils;
import org.demoexm.core.vo.MaterialNewsVo;

public class ReadTest extends BaseTest
{
    public static void main(String[] args){
    	// 读取文件
    	String content = FileUtils.readFile("D:/Users/chenweixian/git/gatewayWeChat/doc", "tuwen.json", ControllerContants.CHARSET_UTF8);
    	
    	JSONObject json = JSONObject.fromObject(content);
    	
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
//		response.setNewsMaterials(newsMaterials); 
        
    }
    
}
