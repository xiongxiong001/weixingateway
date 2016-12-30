/**
 * @Description
 * @author xguan 2016年6月25日
 * @param 
 * @return
 */
package org.demoexm.core.vo;

import java.util.List;

/**
 * @author gxlly
 *
 */
public class BatchUserInfoListRequestVo extends BaseVo
{
    
    /**
     * 
     */
    /* 公众号access_token */
    private String public_token;
    
    /* 用户openid列表 */
    private List<UserInfoRequestVo> user_list;
    
    public String getPublic_token()
    {
        return public_token;
    }
    
    public void setPublic_token(String public_token)
    {
        this.public_token = public_token;
    }
    
    public List<UserInfoRequestVo> getUser_list()
    {
        return user_list;
    }
    
    public void setUser_list(List<UserInfoRequestVo> user_list)
    {
        this.user_list = user_list;
    }
    
}
