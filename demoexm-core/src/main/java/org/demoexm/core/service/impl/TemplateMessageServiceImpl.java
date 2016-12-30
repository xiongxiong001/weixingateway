package org.demoexm.core.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.demoexm.core.contants.CommonConstant;
import org.demoexm.core.contants.ControllerContants;
import org.demoexm.core.exception.IafclubException;
import org.demoexm.core.service.TemplateMessageService;
import org.demoexm.core.util.HttpClientUtil;
import org.demoexm.core.util.MyStringUtils;
import org.demoexm.core.vo.ResponseVo;
import org.demoexm.core.vo.TemplateMessageSendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/***发送模板消息文档
 * 
 * @author 陈惟鲜
 * @date 2016年10月20日 下午12:53:58
 *
 */
@Service
public class TemplateMessageServiceImpl implements TemplateMessageService
{
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private HttpClientUtil httpClientUtil;
    /**发送模板消息*/
    public ResponseVo send(TemplateMessageSendRequest request){
    	String message = "发送模板消息";
        logger.info(message + ControllerContants.MESSAGE_START);
        
    	String httpUrl = CommonConstant.POST_TEMPLATE_SEND;// 获得链接信息
    	// 更改替换链接信息
    	httpUrl = httpUrl.replace("ACCESS_TOKEN_PARAMETER", request.getAccess_token());
	
        try {
			String response = httpClientUtil.sendHttpPostJson(httpUrl, this.initTemplateParam(request));
			return (ResponseVo) JSONObject.toBean(JSONObject.fromObject(response), ResponseVo.class);
		} catch (IOException e) {
			logger.error(message+"异常", e);
			throw new IafclubException(message+"异常:"+MyStringUtils.getExceptionDetail(e));
		}finally{
	        logger.info(message + ControllerContants.MESSAGE_END);
		}
    }
    
    /** 初始化参数
     * @param request
     * @return
     * @author : chewneixian 陈惟鲜
     * @create_date 2016年10月20日 下午4:20:29
     */
    private String initTemplateParam(TemplateMessageSendRequest request){
    	// 内容参数
    	JSONObject data = new JSONObject();
    	if (request.getData() != null){
    		for (String key : request.getData().keySet()){
    			JSONObject json = new JSONObject();
    			json.put("color", "#173177");
    			json.put("value", request.getData().get(key));
    			data.put(key, json);
    		}
    	}
    	// 所有参数
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("touser", request.getOpenid());
    	param.put("template_id", request.getTemplate_id());
    	param.put("topcolor", "#FF0000");
    	param.put("url", request.getUrl());
    	param.put("data", data);
    	
    	return JSONObject.fromObject(param).toString();
    }
    
}
