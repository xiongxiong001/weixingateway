package org.demoexm.core.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.demoexm.core.contants.CommonConstant;
import org.demoexm.core.contants.ControllerContants;
import org.demoexm.core.contants.RedisContants;
import org.demoexm.core.contants.ResponseCodeEnum;
import org.demoexm.core.exception.IafclubException;
import org.demoexm.core.service.WechatService;
import org.demoexm.core.util.FileUtils;
import org.demoexm.core.util.HttpClientUtil;
import org.demoexm.core.util.MyDateUtil;
import org.demoexm.core.util.MyStringUtils;
import org.demoexm.core.vo.AuthDataVo;
import org.demoexm.core.vo.AuthRequestVo;
import org.demoexm.core.vo.BatchUserInfoListRequestVo;
import org.demoexm.core.vo.BatchUserInfoListVo;
import org.demoexm.core.vo.MenuButtonVo;
import org.demoexm.core.vo.MenuReponseVo;
import org.demoexm.core.vo.ResponseVo;
import org.demoexm.core.vo.UserInfoRequestVo;
import org.demoexm.core.vo.UserInfoVo;
import org.demoexm.core.vo.UserListResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class WechatServiceImpl implements WechatService
{
    private Logger logger = Logger.getLogger(WechatServiceImpl.class);
    
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HttpClientUtil httpClientUtil;
    
    /**
     * @Description 获取授权信息包含openid和access_token
     * @author xguan
     * @param AuthRequestVo
     * @return AuthDataVo
     */
    public AuthDataVo getAuthInfo(AuthRequestVo authRequestVo){
        AuthDataVo authDataVo = null;
        String message = "【微信网关接口】：获取授权个人用户信息";
        try{
            logger.info(message + ControllerContants.MESSAGE_START);
            /*
             * 获取code后，请求以下链接获取openId和access_token： https://api.weixin.qq.com/sns
             * /oauth2/access_token?appid=APPID&secret =SECRET&code=CODE&grant_type=authorization_code
             */
            String httpUrl = CommonConstant.WECHAT_URL_GET_AUTH;
            httpUrl = httpUrl.replace("APPID_PARAMETER", authRequestVo.getAppid());
            httpUrl = httpUrl.replace("SECRET_PARAMETER", authRequestVo.getSecret());
            httpUrl = httpUrl.replace("CODE_PARAMETER", authRequestVo.getCode());
            httpUrl = httpUrl.replace("GRANT_TYPE_PARAMETER", authRequestVo.getGrant_type());
            //String response = HttpClientUtil.sendHttpGet(CommonConstant.WECHAT_URL_GET_AUTH + HttpClientUtil.transVoToParamforGet(authRequestVo));
            String response = httpClientUtil.sendHttpGet(httpUrl);
            authDataVo = (AuthDataVo)JSONObject.toBean(JSONObject.fromObject(response), AuthDataVo.class);
            authDataVo.setDescript(ResponseCodeEnum.getDescriptByCode(authDataVo.getErrcode())==null?authDataVo.getErrmsg():ResponseCodeEnum.getDescriptByCode(authDataVo.getErrcode()));
        }catch (IOException ioe){
            logger.error(message + "出现异常,异常：" + ioe.getMessage(), ioe);
            authDataVo = new AuthDataVo();
            authDataVo.setErrcode(ResponseCodeEnum.HTTP_TIME_OUT.getErrcode());
            authDataVo.setDescript(ResponseCodeEnum.HTTP_TIME_OUT.getDescript());
            authDataVo.setErrmsg(ioe.getMessage());
        }catch (Exception e){
            logger.error(message + "出现异常,异常：" + e.getMessage(), e);
            authDataVo = new AuthDataVo();
            authDataVo.setErrcode(ResponseCodeEnum.UNKNOWN_EXCEPTION.getErrcode());
            authDataVo.setDescript(ResponseCodeEnum.UNKNOWN_EXCEPTION.getDescript());
            authDataVo.setErrmsg(e.getMessage());
        }
        logger.info(message + ControllerContants.MESSAGE_END);
        return authDataVo;
    }
    
    /**
     * @Description 获取微信用户信息
     * @author xguan
     * @param UserInfoRequestVo
     * @return UserInfoVo
     */
    public UserInfoVo getUserInfo(UserInfoRequestVo uerInfoRequestVo){
        UserInfoVo userInfoVo = null;
        String message = "【微信网关接口】：获取个人用户信息";
        try{
            logger.info(message + ControllerContants.MESSAGE_START);
            /*
             * 拉取用户信息(需scope为 snsapi_userinfo) http：GET（请使用https协议）https://api.weixin .qq.com/sns/userinfo?access_token
             * =ACCESS_TOKEN&openid=OPENID&lang=zh_CN
             */
            String httpUrl = CommonConstant.WECHAT_URL_GET_USERINFO;
            httpUrl = httpUrl.replace("ACCESS_TOKEN_PARAMETER", uerInfoRequestVo.getAccess_token());
            httpUrl = httpUrl.replace("OPENID_PARAMETER", uerInfoRequestVo.getOpenid());
            httpUrl = httpUrl.replace("LANG_PARAMETER", uerInfoRequestVo.getLang());
            //String response = HttpClientUtil.sendHttpGet(CommonConstant.WECHAT_URL_GET_USERINFO+ HttpClientUtil.transVoToParamforGet(uerInfoRequestVo));
            String response = httpClientUtil.sendHttpGet(httpUrl);
            userInfoVo = (UserInfoVo)JSONObject.toBean(JSONObject.fromObject(response), UserInfoVo.class);
            userInfoVo.setDescript(ResponseCodeEnum.getDescriptByCode(userInfoVo.getErrcode())==null?userInfoVo.getErrmsg():ResponseCodeEnum.getDescriptByCode(userInfoVo.getErrcode()));
        }
        catch (IOException ioe)
        {
            logger.error(message + "出现异常,异常：" + ioe.getMessage(), ioe);
            userInfoVo = new UserInfoVo();
            userInfoVo.setErrcode(ResponseCodeEnum.HTTP_TIME_OUT.getErrcode());
            userInfoVo.setDescript(ResponseCodeEnum.HTTP_TIME_OUT.getDescript());
            userInfoVo.setErrmsg(ioe.getMessage());
        }
        catch (Exception e)
        {
            logger.error(message + "出现异常,异常：" + e.getMessage(), e);
            userInfoVo = new UserInfoVo();
            userInfoVo.setErrcode(ResponseCodeEnum.UNKNOWN_EXCEPTION.getErrcode());
            userInfoVo.setDescript(ResponseCodeEnum.UNKNOWN_EXCEPTION.getDescript());
            userInfoVo.setErrmsg(e.getMessage());
        }
        logger.info(message + ControllerContants.MESSAGE_END);
        return userInfoVo;
    }
    
    /**获取token值
     * 
     * @param authRequestVo
     * @return
     * @author : chewneixian 陈惟鲜
     * @create_date 2016年10月20日 下午5:00:08
     */
    public String getAccessToken(AuthRequestVo authRequestVo){
    	AuthDataVo authDataVo = this.getPublicToken(authRequestVo);
    	if (authDataVo.getErrcode() == ResponseCodeEnum.SUCCESS.getErrcode()){
    		return authDataVo.getAccess_token();
    	}else{
    		throw new IafclubException("获取touken失败");
    	}
    }
    
    
    /**
     * @Description 获取微信公众号，用于批量拉取用户信息(直接从微信拿取token)
     * @author xguan
     * @param AuthRequestVo
     * @return AuthDataVo
     */
    public AuthDataVo getPublicTokenFromWechat(AuthRequestVo authRequestVo){
    	 String message = "【微信网关接口】：从微信获取微信公众号";
    	 AuthDataVo authDataVo = null;
    	 /*
         * 获取公众号accessToken用于批量拉取用户信息,必须hppts发送,http://api.weixin.qq.com/cgi- bin/token?
         * grant_type=client_credential&appid=appid&secret=SECRET
         */
    	try{
    		String tokenKey = RedisContants.GATEWAYWECHAT_WX_TOKEN+"_"+authRequestVo.getAppid();
            BoundValueOperations<String,Object> boundValueOperations = redisTemplate.boundValueOps(tokenKey);
	    	String httpUrl = CommonConstant.WECHAT_URL_GET_PUBLICTOKEN;
	    	httpUrl = httpUrl.replace("GRANT_TYPE_PARAMETER", authRequestVo.getGrant_type());
	    	httpUrl = httpUrl.replace("APPID_PARAMETER", authRequestVo.getAppid());
	    	httpUrl = httpUrl.replace("SECRET_PARAMETER", authRequestVo.getSecret());
	    	String response = httpClientUtil.sendHttpsGet(httpUrl);
	        //String response = HttpClientUtil.sendHttpsGet(CommonConstant.WECHAT_URL_GET_PUBLICTOKEN+ HttpClientUtil.transVoToParamforGet(authRequestVo));
	    	authDataVo = (AuthDataVo)JSONObject.toBean(JSONObject.fromObject(response), AuthDataVo.class);
	    	// 设置token对象2小时有效
            if(ResponseCodeEnum.SUCCESS.getErrcode()==authDataVo.getErrcode()){
            	//同步一份accessToken到php中
            	JSONObject json = new JSONObject();
            	json.put("expire_time", MyDateUtil.addSecond(MyDateUtil.getCurrentDateTime(),Integer.valueOf(authDataVo.getExpires_in())).getTime()/1000);
            	json.put("access_token", authDataVo.getAccess_token());
            	FileUtils.saveFile(CommonConstant.FILE_ACCESS_TOKEN_PATH,"access_token.json", ControllerContants.CHARSET_UTF8, String.valueOf(json));
            	boundValueOperations.set(JSONObject.fromObject(authDataVo), RedisContants.GATEWAYWECHAT_WX_TOKEN_TIME, TimeUnit.SECONDS);
            }
    	}catch (IOException ioe){
            logger.error(message + "出现异常,异常：" + ioe.getMessage(), ioe);
            authDataVo = new AuthDataVo();
            authDataVo.setErrcode(ResponseCodeEnum.HTTP_TIME_OUT.getErrcode());
            authDataVo.setDescript(ResponseCodeEnum.HTTP_TIME_OUT.getDescript());
            authDataVo.setErrmsg(ioe.getMessage());
        }catch (Exception e)
        {
            logger.error(message + "出现异常,异常：" + e.getMessage(), e);
            authDataVo = new AuthDataVo();
            authDataVo.setErrcode(ResponseCodeEnum.UNKNOWN_EXCEPTION.getErrcode());
            authDataVo.setDescript(ResponseCodeEnum.UNKNOWN_EXCEPTION.getDescript());
            authDataVo.setErrmsg(e.getMessage());
        }
    	return authDataVo;
    }
    
    
    
    /**
     * @Description 获取微信公众号，用于批量拉取用户信息
     * @author xguan
     * @param AuthRequestVo
     * @return AuthDataVo
     */
    @SuppressWarnings("unchecked")
    public AuthDataVo getPublicToken(AuthRequestVo authRequestVo)
    {
        AuthDataVo authDataVo = null;
        String message = "【微信网关接口】：获取微信公众号";

        logger.info(message + ControllerContants.MESSAGE_START);
        // 查询redis中appid对应的tokenkey是否已经存在
        String tokenKey = RedisContants.GATEWAYWECHAT_WX_TOKEN+"_"+authRequestVo.getAppid();
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(tokenKey);
        if (boundValueOperations.get()==null){
        	//从微信接口获取
        	authDataVo = getPublicTokenFromWechat(authRequestVo);
        }else{
        	//从redis中获取
        	authDataVo = (AuthDataVo)JSONObject.toBean(JSONObject.fromObject(boundValueOperations.get()),AuthDataVo.class);
        }
        logger.info(message + ControllerContants.MESSAGE_END);
        return authDataVo;
    }
    
    /**
     * @Description 批量拉取用户信息
     * @author xguan
     * @param BatchUserInfoListRequestVo
     * @return BatchUserInfoListVo
     */
    public BatchUserInfoListVo getUserInfoList(BatchUserInfoListRequestVo batchUserInfoListRequestVo){
        BatchUserInfoListVo batchUserInfoListVo = null;
        String message = "【微信网关接口】：批量拉取用户信息";
        try{
            logger.info(message + ControllerContants.MESSAGE_START);
            /*
             * 批量拉取用户信息，https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN POST
             */
            String httpUrl = CommonConstant.WECHAT_URL_POST_BATCHUSERINFOLIST;
            httpUrl = httpUrl.replace("ACCESS_TOKEN_PARAMETER", batchUserInfoListRequestVo.getPublic_token());
            String response = httpClientUtil.sendHttpPostJson(httpUrl,String.valueOf(JSONObject.fromObject(batchUserInfoListRequestVo)));
            //String response = HttpClientUtil.sendHttpPostJson(CommonConstant.WECHAT_URL_POST_BATCHUSERINFOLIST + "?access_token="+ batchUserInfoListRequestVo.getPublic_token(), String.valueOf(JSONObject.fromObject(batchUserInfoListRequestVo)));
            batchUserInfoListVo = (BatchUserInfoListVo)JSONObject.toBean(JSONObject.fromObject(response), BatchUserInfoListVo.class);
            batchUserInfoListVo.setDescript(ResponseCodeEnum.getDescriptByCode(batchUserInfoListVo.getErrcode())==null?batchUserInfoListVo.getErrmsg():ResponseCodeEnum.getDescriptByCode(batchUserInfoListVo.getErrcode()));
        }catch (IOException ioe){
            logger.error(message + "出现异常,异常：" + ioe.getMessage(), ioe);
            batchUserInfoListVo = new BatchUserInfoListVo();
            batchUserInfoListVo.setErrcode(ResponseCodeEnum.HTTP_TIME_OUT.getErrcode());
            batchUserInfoListVo.setDescript(ResponseCodeEnum.HTTP_TIME_OUT.getDescript());
            batchUserInfoListVo.setErrmsg(ioe.getMessage());
        }catch (Exception e){
            logger.error(message + "出现异常,异常：" + e.getMessage(), e);
            batchUserInfoListVo = new BatchUserInfoListVo();
            batchUserInfoListVo.setErrcode(ResponseCodeEnum.UNKNOWN_EXCEPTION.getErrcode());
            batchUserInfoListVo.setDescript(ResponseCodeEnum.UNKNOWN_EXCEPTION.getDescript());
            batchUserInfoListVo.setErrmsg(e.getMessage());
        }
        logger.info(message + ControllerContants.MESSAGE_END);
        return batchUserInfoListVo;
    }
    
    /**获取关注公众号用户列表
     * 
     * @param access_token token信息
     * @param next_openid 开始openID
     * @return
     * @author : chewneixian 陈惟鲜
     * @create_date 2016年10月24日 下午5:31:09
     */
    public UserListResponseVo getUserList(String access_token, String next_openid){
    	String message = "发送模板消息";
        logger.info(message + ControllerContants.MESSAGE_START);
        if (StringUtils.isEmpty(next_openid)){
        	next_openid = "";
        }
        
    	String httpUrl = CommonConstant.GET_USER_LIST;// 获得链接信息
    	// 更改替换链接信息
    	httpUrl = httpUrl.replace("ACCESS_TOKEN_PARAMETER", access_token);
    	// 更改替换链接信息
    	httpUrl = httpUrl.replace("NEXT_OPENID_PARAMETER", next_openid);
	
        try {
			String response = httpClientUtil.sendHttpGet(httpUrl);
			JSONObject json = JSONObject.fromObject(response);
			// 强转换
			UserListResponseVo userListResponseVo = new UserListResponseVo();
			JSONObject data = json.getJSONObject("data");
			if (data !=null ){
				JSONArray jArray = data.getJSONArray("openid");
				if (jArray != null && jArray.size() > 0){
					String[] openids = new String[jArray.size()];
					for (int i=0;i<jArray.size();i++ ){
						openids[i] = jArray.getString(i);
					}
					userListResponseVo.setOpenid(openids);
				}
				userListResponseVo.setTotal(json.getLong("total")); // 关注该公众账号的总用户数
				userListResponseVo.setCount(json.getLong("count")); // 拉取的OPENID个数，最大值为10000
				userListResponseVo.setNext_openid(json.getString("next_openid"));// 拉取列表的最后一个用户的OPENID
			}
			
			return userListResponseVo;
		} catch (IOException e) {
			logger.error(message+"异常", e);
			throw new IafclubException(message+"异常:"+MyStringUtils.getExceptionDetail(e));
		}finally{
	        logger.info(message + ControllerContants.MESSAGE_END);
		}
    }

	public MenuReponseVo getMenuVo(String access_token) {
		String message = "【微信网关接口】：获取微信自定义菜单列表";
		String httpUrl = CommonConstant.GET_MENU_LIST;// 获得链接信息
		httpUrl = httpUrl.replace("ACCESS_TOKEN_PARAMETER", access_token);
		 try{
			 String response = httpClientUtil.sendHttpGet(httpUrl);
			 JSONObject json = JSONObject.fromObject(response);
			 Map<String,Object> classMap = new HashMap<String,Object>();
			 classMap.put("button", MenuButtonVo.class);
			 classMap.put("sub_button", MenuButtonVo.class);
			 MenuReponseVo menu = (MenuReponseVo)JSONObject.toBean(json,MenuReponseVo.class,classMap);
			 
			 menu.setDescript(ResponseCodeEnum.getDescriptByCode(menu.getErrcode())==null?menu.getErrmsg():ResponseCodeEnum.getDescriptByCode(menu.getErrcode()));
			 return menu;
		 } catch (Exception e) {
				logger.error(message+"异常", e);
				throw new IafclubException(message+"异常:"+MyStringUtils.getExceptionDetail(e));
			}finally{
		        logger.info(message + ControllerContants.MESSAGE_END);
			}
	}
    
    
    public ResponseVo createMenuVo(String access_token,String menu){
    	String message = "【微信网关接口】：创建微信自定义菜单";
    	String httpUrl = CommonConstant.CREATE_MENU;// 获得链接信息
		httpUrl = httpUrl.replace("ACCESS_TOKEN_PARAMETER", access_token);
		 try{
			 //String data = String.valueOf(JSONObject.fromObject(menu));
			 String response = httpClientUtil.sendHttpPostJson(httpUrl, menu);
			 JSONObject json = JSONObject.fromObject(response);
			 ResponseVo resp = initResponseVo(json);
			 return resp;
		 } catch (Exception e) {
				logger.error(message+"异常", e);
				throw new IafclubException(message+"异常:"+MyStringUtils.getExceptionDetail(e));
			}finally{
		        logger.info(message + ControllerContants.MESSAGE_END);
			}
    }
    
    
    public ResponseVo deleteMenuVo(String access_token){
    	String message = "【微信网关接口】：删除微信自定义菜单";
    	String httpUrl = CommonConstant.DELETE_MENU;// 获得链接信息
		httpUrl = httpUrl.replace("ACCESS_TOKEN_PARAMETER", access_token);
		 try{
			 String response = httpClientUtil.sendHttpGet(httpUrl);
			 JSONObject json = JSONObject.fromObject(response);
			 ResponseVo resp = initResponseVo(json);
			 return resp;
		 } catch (Exception e) {
				logger.error(message+"异常", e);
				throw new IafclubException(message+"异常:"+MyStringUtils.getExceptionDetail(e));
			}finally{
		        logger.info(message + ControllerContants.MESSAGE_END);
			}
    }

    private ResponseVo initResponseVo(JSONObject responseJson){
    	ResponseVo response = new ResponseVo();
    	response.setErrcode(responseJson.getInt("errcode"));
    	response.setErrmsg(responseJson.getString("errmsg"));
    	return response;
    }
    
}
