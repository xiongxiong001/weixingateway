package org.demoexm.web;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.demoexm.core.service.TemplateMessageService;
import org.demoexm.core.service.WechatService;
import org.demoexm.core.util.MyDateUtil;
import org.demoexm.core.util.MyNumberUtils;
import org.demoexm.core.vo.AuthRequestVo;
import org.demoexm.core.vo.ResponseVo;
import org.demoexm.core.vo.TemplateMessageSendRequest;
import org.demoexm.core.vo.UserListResponseVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TemplateMessageServiceTest extends BaseTest
{
    
    @Autowired
    private WechatService wechatService;
    
    @Autowired
    private TemplateMessageService templateMessageService;
    
    @Test
    public void testGetAuthInfoIafclubOne()
    {

        String access_token = initTokenString();
        TemplateMessageSendRequest request = new TemplateMessageSendRequest();
        request.setAccess_token(access_token);
        request.setUrl("m.iafclub.com");
        request.setTemplate_id("XtMRVtkE-HhT9dyIPxeqc1oMzz9FdjIuIgcEYrs7teAH6OPWzCHA");
        // 本人openid
        request.setOpenid("oWWRKwJ63c8USQM7mjYMyZLqQqCY");
        Map<String, String> data = new HashMap<String, String>();
        data.put("first", "我在测试，别理我");
    	data.put("keyword1", "2");
    	data.put("keyword2", MyNumberUtils.fen2Yuan(55555).toString());
    	data.put("keyword3", MyNumberUtils.fen2Yuan(5039).toString());
    	data.put("keyword4", MyDateUtil.getCurrentDateTimeStr());
    	data.put("remark", "寻找谢鹏openId，您的是�?+request.getOpenid()+");
        request.setData(data);
        System.out.println("request:"+JSONObject.fromObject(request));
        ResponseVo responseVo = templateMessageService.send(request);
        System.out.println("response:"+JSONObject.fromObject(responseVo));
        System.out.println("\n\n\n\n");
    }
    
    @Test
    public void testGetAuthInfoIafclub()
    {
        String access_token = initTokenString();
        TemplateMessageSendRequest request = new TemplateMessageSendRequest();
        request.setAccess_token(access_token);
        request.setUrl("m.iafclub.com");
        request.setTemplate_id("HhT9dyIPxeqc1oMzz9FdjIuIgcEYrs7teAH6OPWzCHA");
        // 本人openid
//        request.setOpenid("oWWRKwJ63c8USQM7mjYMyZLqQqCY");
    	UserListResponseVo userListResponseVo = wechatService.getUserList(this.initTokenString(), null);
        if (userListResponseVo.getOpenid().length>0){
        	for (String openid : userListResponseVo.getOpenid()){
		        request.setOpenid(openid);
		        Map<String, String> data = new HashMap<String, String>();
		        data.put("first", "我在测试，别理我");
		    	data.put("keyword1", "2");
		    	data.put("keyword2", MyNumberUtils.fen2Yuan(55555).toString());
		    	data.put("keyword3", MyNumberUtils.fen2Yuan(5039).toString());
		    	data.put("keyword4", MyDateUtil.getCurrentDateTimeStr());
		    	data.put("remark", "寻找谢鹏openId，您的是�?+request.getOpenid()+");
		        request.setData(data);
		        ResponseVo responseVo = templateMessageService.send(request);
		        System.out.println("request:"+JSONObject.fromObject(request) + "\nresponse:"+JSONObject.fromObject(responseVo));
		        System.out.println("\n\n\n\n");
        	}
        }
    }
    
    @Test
    public void testGetAuthInfoMy()
    {
        AuthRequestVo authRequestVo = new AuthRequestVo();
        authRequestVo.setAppid("wx2ddc184b6eede62c");
        authRequestVo.setSecret("5dbba14361045533517e31924665c517");
        authRequestVo.setGrant_type("client_credential");
        
        String access_token = wechatService.getAccessToken(authRequestVo);
        TemplateMessageSendRequest request = new TemplateMessageSendRequest();
        request.setAccess_token(access_token);
        request.setUrl("m.iafclub.com");
        request.setTemplate_id("Z0OyfI8fwJlq-oUhLLHfCbWb1RkwSl6PuvsNKd0BSkg");
        // 本人openid
//        request.setOpenid("oWWRKwJ63c8USQM7mjYMyZLqQqCY");
    	UserListResponseVo userListResponseVo = wechatService.getUserList(this.initTokenString(), null);
        if (userListResponseVo.getOpenid().length>0){
        	for (String openid : userListResponseVo.getOpenid()){
		        request.setOpenid(openid);
		        Map<String, String> data = new HashMap<String, String>();
		        data.put("datetime", MyDateUtil.getCurrentDateTimeStr());
		    	data.put("project", "2");
		    	data.put("money", MyNumberUtils.fen2Yuan(55555).toString());
		    	data.put("remark", "您当前关注的公众号openid为："+request.getOpenid());
		        request.setData(data);
		        ResponseVo responseVo = templateMessageService.send(request);
		        System.out.println("request:"+JSONObject.fromObject(request) + "\nresponse:"+JSONObject.fromObject(responseVo));
		        System.out.println("\n\n\n\n");
        	}
        }
    }
    
    /**获取token信息
     * 
     * @return
     * @author : chewneixian 陈惟�?     * @create_date 2016�?0�?4�?下午4:09:08
     */
    private String initTokenString(){
    	 AuthRequestVo authRequestVo = new AuthRequestVo();
         authRequestVo.setAppid("wx1abc4d5d85e7bf34");
         authRequestVo.setSecret("36fb2d06d71ff1669d08b36b9b559145");
         authRequestVo.setGrant_type("client_credential");
         String tokenString = wechatService.getAccessToken(authRequestVo);
         
         return tokenString;
    }

    @Test
    public void testGetUserListIafclub()
    {
    	UserListResponseVo UserListResponseVo = wechatService.getUserList(this.initTokenString(), null);
    	System.out.println(JSONObject.fromObject(UserListResponseVo));
    }
    
    
}
