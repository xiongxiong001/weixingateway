/**
 * @Description
 * @author xguan 2016年6月25日
 * @param 
 * @return
 */
package org.demoexm.core.vo;

/**
 * @author gxlly
 *
 */
public class BatchUserInfoVo extends UserInfoVo
{
    
    /*
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息，只有openid和UnionID（ 在该公众号绑定到了微信开放平台账号时才有）。
     */
    private String subscribe;
    
    /* 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 */
    private String subscribe_time;
    
    /* 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 */
    private String remark;
    
    /* 用户所在的分组ID */
    private String groupid;
    
    /* 用户所在的分组ID */
    private String language;
    
    public String getSubscribe()
    {
        return subscribe;
    }
    
    public void setSubscribe(String subscribe)
    {
        this.subscribe = subscribe;
    }
    
    public String getSubscribe_time()
    {
        return subscribe_time;
    }
    
    public void setSubscribe_time(String subscribe_time)
    {
        this.subscribe_time = subscribe_time;
    }
    
    public String getRemark()
    {
        return remark;
    }
    
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
    public String getGroupid()
    {
        return groupid;
    }
    
    public void setGroupid(String groupid)
    {
        this.groupid = groupid;
    }
    
    public String getLanguage()
    {
        return language;
    }
    
    public void setLanguage(String language)
    {
        this.language = language;
    }
    
}
