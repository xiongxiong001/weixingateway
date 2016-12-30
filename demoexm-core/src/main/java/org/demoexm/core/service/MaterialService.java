package org.demoexm.core.service;

import org.demoexm.core.vo.AddMaterialResponseVo;
import org.demoexm.core.vo.MaterialCountResponseVo;
import org.demoexm.core.vo.MaterialNewsResponse;
import org.demoexm.core.vo.StringResponseVo;
/**素材信息
 * 
 * @author : chewneixian 陈惟鲜
 * @create_date 2016年11月23日 下午6:20:33
 *
 */
public interface MaterialService {
    /**获取单个素材
     * 
     * @param access_token token地址
     * @param media_id 素材ID
     * @return
     * @author : chewneixian 陈惟鲜
     * @create_date 2016年11月23日 下午5:43:32
     */
    public StringResponseVo materialOne(String access_token, String media_id);
    

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
    public MaterialNewsResponse materialList(String access_token, String type, int offset, int count);
    

    /**获取素材总数
     * voice_count	 语音总数量
		video_count	 视频总数量
		image_count	 图片总数量
		news_count	 图文总数量
     * */
    public MaterialCountResponseVo materialCount(String access_token);
    
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
    public AddMaterialResponseVo addMaterial(String access_token,String filePath,String title,String introduction,String type);
}
