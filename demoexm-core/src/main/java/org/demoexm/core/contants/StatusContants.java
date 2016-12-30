package org.demoexm.core.contants;

import org.apache.commons.lang.StringUtils;

public enum StatusContants {
	
	
	
    /** 统一系统表状态：0未生效；1已生效 999已删除 */
    SYSTEM_TABLE_STATUS_0(StatusTypeContants.SYSTEM_TABLE_STATUS, "未生效", "0"),
    /** 统一系统表状态：0未生效；1已生效  999已删除 */
    SYSTEM_TABLE_STATUS_1(StatusTypeContants.SYSTEM_TABLE_STATUS, "已生效", "1"),
    /** 统一系统表状态：0未生效；1已生效  999已删除 */
    SYSTEM_TABLE_STATUS_DELETE(StatusTypeContants.SYSTEM_TABLE_STATUS, "已删除", "999"),
    
    /**微信素材类型   图片（image）、视频（video）、语音 （voice）、图文（news）*/
    WECHAT_MATERIAL_TYPE_TYPE_IMAGE(StatusTypeContants.WECHAT_MATERIAL_TYPE,"图片","image"),    
    WECHAT_MATERIAL_TYPE_TYPE_VIDEO(StatusTypeContants.WECHAT_MATERIAL_TYPE,"视频","video"),    
    WECHAT_MATERIAL_TYPE_TYPE_VOICE(StatusTypeContants.WECHAT_MATERIAL_TYPE,"语音","voice"),    
    WECHAT_MATERIAL_TYPE_TYPE_NEWS(StatusTypeContants.WECHAT_MATERIAL_TYPE,"图文","news")  ;
	
	
	 // 成员变量
    private String name;
    
    private String index;
    
    private String type;
    
    // 构造方法
    private StatusContants(String type, String name, String index)
    {
        this.name = name;
        this.index = index;
        this.type = type;
    }
    
    // get set 方法
    public String getName()
    {
        return name;
    }
    
    public String getIndex()
    {
        return index;
    }
    
    public String getType()
    {
        return type;
    }
    
    // 普通方法
    public static String getName(String type, String index)
    {
        if (StringUtils.isEmpty(index))
        {
            return null;
        }
        // 删除状态
        if (SYSTEM_TABLE_STATUS_DELETE.getIndex().equals(index))
        {
            return SYSTEM_TABLE_STATUS_DELETE.getName();
        }
        for (StatusContants c : StatusContants.values())
        {
            if (c.type.equals(type) && c.index.equals(index))
            {
                return c.name;
            }
        }
        return null;
    }
    
    // 普通方法
    public static String getIndex(String type, String name)
    {
        if (StringUtils.isEmpty(name))
        {
            return null;
        }
        // 删除状态
        if (SYSTEM_TABLE_STATUS_DELETE.getName().equals(name))
        {
            return SYSTEM_TABLE_STATUS_DELETE.getName();
        }
        for (StatusContants c : StatusContants.values())
        {
            if (c.type.equals(type) && c.name.equals(name))
            {
                return c.index;
            }
        }
        return null;
    }

}
