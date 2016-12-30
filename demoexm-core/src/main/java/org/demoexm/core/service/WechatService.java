package org.demoexm.core.service;

import org.demoexm.core.vo.AuthDataVo;
import org.demoexm.core.vo.AuthRequestVo;
import org.demoexm.core.vo.BatchUserInfoListRequestVo;
import org.demoexm.core.vo.BatchUserInfoListVo;
import org.demoexm.core.vo.MenuReponseVo;
import org.demoexm.core.vo.ResponseVo;
import org.demoexm.core.vo.UserInfoRequestVo;
import org.demoexm.core.vo.UserInfoVo;
import org.demoexm.core.vo.UserListResponseVo;

public interface WechatService {

	/**
	  * @Description 获取授权信息包含openid和access_token
	  * @author xguan 
	  * @param AuthRequestVo
	  * @return AuthDataVo
	  */
	public AuthDataVo getAuthInfo(AuthRequestVo authRequestVo);
	
	/**
	  * @Description 获取微信用户信息
	  * @author xguan 
	  * @param openid
	  * @param access_token
	  * @return UserInfoVo
	  */
	public UserInfoVo getUserInfo(UserInfoRequestVo userInfoRequestVo);
	
	/**
	  * @Description 获取公众号access_Token
	  * @author xguan 
	  * @param AuthRequestVo
	  * @return AuthDataVo
	  */
	public AuthDataVo getPublicToken(AuthRequestVo authRequestVo);
	 /**获取token值
     * 
     * @param authRequestVo
     * @return
     * @author : chewneixian 陈惟鲜
     * @create_date 2016年10月20日 下午5:00:08
     */
    public String getAccessToken(AuthRequestVo authRequestVo);
	/**
	  * @Description 批量拉取用户信息,一次拉取100条用户
	  * @author xguan 
	  * @param AuthRequestVo
	  * @return AuthDataVo
	  */
	public BatchUserInfoListVo getUserInfoList(BatchUserInfoListRequestVo batchUserInfoListRequestVo);

    
    /**获取关注公众号用户列表
     * 
     * @param access_token信息
     * @param next_openid 开始openID
     * @return
     * @author : chewneixian 陈惟鲜
     * @create_date 2016年10月24日 下午5:31:09
     */
    public UserListResponseVo getUserList(String access_token, String next_openid);
    
    /**获取自定义菜单列表
     * 
     * @param access_token信息
     * @return
     * @author xguan
     * @create_date 2016年10月24日 下午5:31:09
     */
    public MenuReponseVo getMenuVo(String access_token);
    
    public ResponseVo createMenuVo(String access_token,String menu);
    
    public ResponseVo deleteMenuVo(String access_token);
    
    public AuthDataVo getPublicTokenFromWechat(AuthRequestVo authRequestVo);
	
}
