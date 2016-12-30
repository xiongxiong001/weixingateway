package org.demoexm.web;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import net.sf.json.JSONObject;

import org.demoexm.core.service.WechatService;
import org.demoexm.core.vo.AuthDataVo;
import org.demoexm.core.vo.AuthRequestVo;
import org.demoexm.core.vo.BatchUserInfoListRequestVo;
import org.demoexm.core.vo.BatchUserInfoListVo;
import org.demoexm.core.vo.MenuButtonVo;
import org.demoexm.core.vo.MenuReponseVo;
import org.demoexm.core.vo.MenuVo;
import org.demoexm.core.vo.ResponseVo;
import org.demoexm.core.vo.UserInfoRequestVo;
import org.demoexm.core.vo.UserInfoVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WechatServiceTest extends BaseTest
{
    
    @Autowired
    private WechatService wechatService;
    
    @Test
    public void testGetAuthInfo(){
        AuthRequestVo authRequestVo = new AuthRequestVo();
        authRequestVo.setAppid("wx1abc4d5d85e7bf34");
        authRequestVo.setSecret("36fb2d06d71ff1669d08b36b9b559145");
        authRequestVo.setCode("02119qIa1v8ygr0MlIHa1h2mIa119qII"); // 由微信开发�?工具请求回调返回code
        authRequestVo.setGrant_type("authorization_code");
        AuthDataVo authDataVo = wechatService.getAuthInfo(authRequestVo);
        Assert.assertEquals(0, authDataVo.getErrcode());
        
    }
    
    @Test
    public void testGetUserInfo() {
        UserInfoRequestVo userInfoRequestVo = new UserInfoRequestVo();
        userInfoRequestVo.setOpenid("oWWRKwEf-py74iNjyQ-qpNbyk6jU");
        userInfoRequestVo.setAccess_token("6kq2ngSFz37br2GcKJUpAc4vYW3ZHyy67M5hgnwhMSmafrVzZGXyQY_uziYoRg02T80VIBfkL77ddrcGVRTJDbA46f-Ck8-m6UHAl57hrIc");
        userInfoRequestVo.setLang("zh_CN");
        UserInfoVo userInfoVo = wechatService.getUserInfo(userInfoRequestVo);
        Assert.assertEquals(0, userInfoVo.getErrcode());
    }
    
    @Test
    public void testGetPublicToken(){
    	AuthDataVo authDataVo = initAuthDataVo();
        System.out.println(JSONObject.fromObject(authDataVo));
        Assert.assertEquals(0, authDataVo.getErrcode());
    }
    
    /**获取token信息
     * 
     * @return
     * @author : chewneixian 陈惟�?     * @create_date 2016�?0�?4�?下午4:09:08
     */
    private AuthDataVo initAuthDataVo(){
    	 AuthRequestVo authRequestVo = new AuthRequestVo();
         authRequestVo.setAppid("wx1abc4d5d85e7bf34");
         authRequestVo.setSecret("36fb2d06d71ff1669d08b36b9b559145");
         authRequestVo.setGrant_type("client_credential");
         AuthDataVo authDataVo = wechatService.getPublicToken(authRequestVo);
         return authDataVo;
    }
    
    @Test
    public void testGetUserInfoList(){
    	AuthDataVo authDataVo = initAuthDataVo();
    	
        BatchUserInfoListRequestVo batchUserInfoListRequestVo = new BatchUserInfoListRequestVo();
        batchUserInfoListRequestVo.setPublic_token(authDataVo.getAccess_token());
        /* 构建list */
        UserInfoRequestVo userInfoRequestVo = new UserInfoRequestVo();
        List<UserInfoRequestVo> user_list = new ArrayList<UserInfoRequestVo>();
        userInfoRequestVo.setOpenid("oWWRKwEf-py74iNjyQ-qpNbyk6jU");
        userInfoRequestVo.setLang("zh-CN");
        user_list.add(userInfoRequestVo);
        batchUserInfoListRequestVo.setUser_list(user_list);
        BatchUserInfoListVo batchUserInfoListVo = wechatService.getUserInfoList(batchUserInfoListRequestVo);
        Assert.assertEquals(0, batchUserInfoListVo.getErrcode());
        
    }
    @Test
    public void testGetMenuVo(){
    	AuthDataVo authDataVo = initAuthDataVo();
    	MenuReponseVo menuReponseVo = wechatService.getMenuVo(authDataVo.getAccess_token());
    	System.out.println("*********"+JSONObject.fromObject(menuReponseVo));
    	Assert.assertEquals(0, menuReponseVo.getErrcode());
    }
    
    @Test
    public void testDeleteMenuVo(){
    	AuthDataVo authDataVo = initAuthDataVo();
    	ResponseVo response = wechatService.deleteMenuVo(authDataVo.getAccess_token());
    	System.out.println("*********"+JSONObject.fromObject(response));
    	Assert.assertEquals(0, response.getErrcode());
    }
    
    @Test
    public void testCreateMenuVo(){
    	AuthDataVo authDataVo = initAuthDataVo();
    	MenuVo menuVo = new MenuVo();
    	List<MenuButtonVo> button = new ArrayList<MenuButtonVo>();
    	MenuButtonVo menuButtonVo = new MenuButtonVo();
    	menuButtonVo.setName("测试创建菜单接口");
    	menuButtonVo.setType("view");
    	menuButtonVo.setUrl("http://jrt-pcsit.iafclub.com");
    	button.add(menuButtonVo);
    	menuVo.setButton(button);
    	ResponseVo response = wechatService.createMenuVo(authDataVo.getAccess_token(),String.valueOf(JSONObject.fromObject(menuVo)));
    	System.out.println("*********"+JSONObject.fromObject(response));
    	Assert.assertEquals(0, response.getErrcode());
    }
    
}
