package org.demoexm.core.contants;

/**
 * 公用常量类 注：全大写
 * 
 * @author chenweixian
 */
public class ControllerContants
{
	/**字符编码gbk*/
    public static final String CHARSET_GBK = "gbk";
	/**字符编码gbk*/
    public static final String CHARSET_UTF8 = "utf-8";
    
    /** 返回码标识 **/
    public static final String CODE_RESPONSE = "code";
    
    /** 返回结果标识 **/
    public static final String MESSAGE_RESPONSE = "message";
    
    /** 返回结果标识 **/
    public static final String MESSAGE_EXCEPTION_DETAIL = "exceptionDetail";
    
    /** 返回成功标识码 **/
    public static final String CODE_SUCCESS = "00";
    
    /** 标记位 **/
    public static final String FLAG = "flag";
    
    /** 校验结果不通过(例：越权类) */
    public static final String CODE_NOPASS = "ERROR";
    /**返回失败*/
    public static final String CODE_FAIL = "01";
    /**步骤*/
    public static final String PROCEDURE = "procedure";
    /**步骤一  (一般为代收)*/
    public static final String PROCEDURE_ONE = "1";
    /**步骤二  (一般为代付)*/
    public static final String PROCEDURE_TWO = "2";
    /** 返回成功标识码 **/
    public static final String GO_URL = "goUrl";
    
    /** 用于log记录日志，执行开始标识 **/
    public static final String MESSAGE_START = "..........start..........";
    
    /** 用于log记录日志，执行结束标识 **/
    public static final String MESSAGE_END = "..........end..........";
    
    /** 商户账户的key 800 */
    public static final String MERCHANT_ACCTS_KEY_800 = "800";
    
    /** 商户账户的key 810 */
    public static final String MERCHANT_ACCTS_KEY_810 = "810";
    
    /** 商户账户的key 820 */
    public static final String MERCHANT_ACCTS_KEY_820 = "820";
    
    /** 商户账户的key 830 */
    public static final String MERCHANT_ACCTS_KEY_830 = "830";
    
    /** 每页显示记录条数 */
    public static final String PAGE_SIZE = "20";
    
    /** 每页最多显示记录条数 */
    public static final String MAX_PAGE_SIZE = "2000";
    
    /** 用户交易记账表-收取 */
    public static final String TRANSFER_LOG_USER_DIRECTION_RECEIVE = "R";
    
    /** 用户交易记账表-发放 */
    public static final String TRANSFER_LOG_USER_DIRECTION_PAY = "P";
    
    /** 开发 **/
    public static final String TYPE_DEV = "develop";
    
    /** 测试 **/
    public static final String TYPE_SIT = "sit";
    
    /** 准生产 **/
    public static final String TYPE_UAT = "uat";
    
    /** 生产 **/
    public static final String TYPE_PROD = "prod";
    
    /** 默认 **/
    public static final String TYPE_DEFAULT = "default";
    
    /* 登录类型：pc登录接口 */
    public static final String DEVICE_TYPE_PC = "pc";
    
    /* 登录类型：mobile登录接口 */
    public static final String DEVICE_TYPE_MOBILE = "mobile";
    
    /* 登录类型：APP登录接口 */
    public static final String DEVICE_TYPE_APP = "app";
    
    /* 登录类型：wechat登录接口 */
    public static final String DEVICE_TYPE_WECHAT = "wechat";
    
    /* DES加密盐 */
    public static final String DES_SALT="xXssdeFdsdsAa%&(s%dfsdfas123234asS!$^JGas";  
    
    /* 精融汇渠道类型 */
    public static final int IAFCLUB_CHANNEL=0;  
    /* 第三方渠道类型 */
    public static final int THIRD_CHANNEL=1;  
    
}
