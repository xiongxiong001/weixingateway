package org.demoexm.web;

import net.sf.json.JSONObject;

import org.demoexm.core.service.MaterialService;
import org.demoexm.core.vo.AddMaterialResponseVo;
import org.demoexm.core.vo.MaterialCountResponseVo;
import org.demoexm.core.vo.MaterialNewsResponse;
import org.demoexm.core.vo.StringResponseVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MaterialServiceTest extends BaseTest
{
    
    @Autowired
    private MaterialService materialService;
    
    /**获取单个素材
     * 
     * @param access_token token地址
     * @param media_id 素材ID
     * @return
     * @author : chewneixian 陈惟鲜
     * @create_date 2016年11月23日 下午5:43:32
     */
    @Test
    public void materialOne(){
    	String access_token="lV9UQ9hFwpeAV2HAxnDOYXW8AYDxkJFi4JpZ9PkhDo7yUx-5yOChPBIGAUX54M7NtkBBXgKHlifJvVwfZHOvhsSNhuAn3OLruOGrSuKvZp4VWKdAAAYZN";
    	String media_id = "IvMbq0qBkN4K3OBs9lY0ZEaC9I6o835B9FHxd3SiLcc";
    	StringResponseVo responseVo = materialService.materialOne(access_token, media_id);
    	System.out.println(JSONObject.fromObject(responseVo));
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
    @Test
    public void materialList(){
    	String access_token="lV9UQ9hFwpeAV2HAxnDOYXW8AYDxkJFi4JpZ9PkhDo7yUx-5yOChPBIGAUX54M7NtkBBXgKHlifJvVwfZHOvhsSNhuAn3OLruOGrSuKvZp4VWKdAAAYZN";
    	// 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
    	String type="news"; 
    	int offset=0; 
    	int count=3;
    	
    	MaterialNewsResponse responseVo = materialService.materialList(access_token, type, offset, count);
    	System.out.println(JSONObject.fromObject(responseVo));
    }
    

    /**获取素材总数
     * voice_count	 语音总数量
		video_count	 视频总数量
		image_count	 图片总数量
		news_count	 图文总数量
     * */
    @Test
    public void materialCount(){
    	String access_token="lV9UQ9hFwpeAV2HAxnDOYXW8AYDxkJFi4JpZ9PkhDo7yUx-5yOChPBIGAUX54M7NtkBBXgKHlifJvVwfZHOvhsSNhuAn3OLruOGrSuKvZp4VWKdAAAYZN";
    	MaterialCountResponseVo responseVo = materialService.materialCount(access_token);
    	System.out.println(JSONObject.fromObject(responseVo));
    }
    

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
    @Test
    public void addMaterial(){
    	String access_token="lV9UQ9hFwpeAV2HAxnDOYXW8AYDxkJFi4JpZ9PkhDo7yUx-5yOChPBIGAUX54M7NtkBBXgKHlifJvVwfZHOvhsSNhuAn3OLruOGrSuKvZp4VWKdAAAYZN";
    	AddMaterialResponseVo responseVo = materialService.addMaterial(access_token, 
							    			"C:/Users/zhangfeng/Pictures/Camera Roll/show_201102220926036856.jpg", 
							    			"发送图片", 
							    			"测试图片",
							    			"image");
    	System.out.println(JSONObject.fromObject(responseVo));
    }
    
    

    
}
