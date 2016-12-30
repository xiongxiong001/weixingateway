/**
 * @Description 用于请求用户信息的VO
 * @author xguan 2016年6月25日
 */
package org.demoexm.core.vo;

/**
 * @author gxlly
 *
 */
public class UserInfoRequestVo extends BaseVo
{
    /* 用户的唯一标识 */
    private String openid;
    
    /* 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语 */
    private String lang;
    
    public String getOpenid()
    {
        return openid;
    }
    
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }
    
    public String getLang()
    {
        return lang;
    }
    
    public void setLang(String lang)
    {
        this.lang = lang;
    }
    
}
