package org.demoexm.core.contants;

import java.util.concurrent.TimeUnit;

/**
 * 缓存的key常量类 注：全大写
 * 
 * @author chenweixian
 */
public class RedisContants {
	/*** 会计日期表 */
	public final static String TABLE_PLOAN_SETUP = "Ploan_Setup";
	
	/*** task定时任务缓存前缀 */
	public final static String REDIS_ADMIN = "admin:";
	/**加载所有菜单在redis中*/
	public final static String REDIS_ALL_MENU = RedisContants.REDIS_ADMIN + "redis_all_menu";
	/**加载所有角色在redis中*/
	public final static String REDIS_ALL_ROLE = RedisContants.REDIS_ADMIN + "redis_all_role";
	/**加载所有用户组在redis中*/
	public final static String REDIS_ALL_GROUP = RedisContants.REDIS_ADMIN + "redis_all_group";
	/**系统用户*/
	public final static String REDIS_USER = RedisContants.REDIS_ADMIN + "systemUser_";
	/**系统用户组关系*/
	public final static String REDIS_USER_GROUP_REL = RedisContants.REDIS_ADMIN + "systemUserGroupRel_";
	/**系统组*/
	public final static String REDIS_USER_GROUP = RedisContants.REDIS_ADMIN + "systemUserGroup_";
	/**系统角色关系*/
	public final static String REDIS_ROLE_GROUP_REL = RedisContants.REDIS_ADMIN + "systemRoleGroupRel_";
	/**系统角色*/
	public final static String REDIS_ROLE = RedisContants.REDIS_ADMIN + "systemRole_";
	/**系统菜单关系*/
	public final static String REDIS_MENU_ROLE_REL = RedisContants.REDIS_ADMIN + "systemMenuRoleRel_";
	/**系统菜单*/
	public final static String REDIS_MENU = RedisContants.REDIS_ADMIN + "systemMenu_";

	/*** task定时任务缓存前缀 */
	public final static String REDIS_TASK = "task:";

	/** 剩余可投(该余额主要用于处理队列中投资列表)：（上架时放入）task:project_last_项目编号 **/
	public final static String TASK_PROJECT_LAST = REDIS_TASK + "project_last";
	/**剩余可投:债券转让*/
	public final static String TASK_ASSIGNMENT_LAST = REDIS_TASK + "assignment_last";
	/**短信黑名单*/
	public final static String REDIS_TASK_PHONE_BLACKLIST = REDIS_TASK + "phone_blacklist_";
	/*** task应用的tomcat执行了shutdown */
	public final static String TASK_TOMCAT_SHUTDOWN = REDIS_TASK + "tomcat_shutdown";

	/*** 标下架缓存前缀：task:project_info_soldout_项目编号 */
	public final static String TASK_PROJECT_INFO_SOLDOUT = REDIS_TASK + "project_info_soldout_";

	public final static TimeUnit REDISTIME = TimeUnit.SECONDS;

	/*** 用户申请缓存前缀 */
	public final static String TASK_USER_APPLY_PREPAYMENT = REDIS_TASK + "user_apply_prepayment_";

	/**
	 * 处理投资中的标的标识位 : task:do_investment_project_flag_标的编号 <li>
	 * 当里面的值不为空的时候代表有线程在处理
	 **/
	public final static String DO_INVESTMENT_PROJECT_FLAG = REDIS_TASK + "do_investment_project_flag";
	/**
	 * 处理转让中的标的标识位 : task:do_assignment_project_flag_标的编号 <li>
	 * 当里面的值不为空的时候代表有线程在处理
	 **/
	public final static String DO_ASSIGNMENT_PROJECT_FLAG = REDIS_TASK+"do_assignment_project_flag";
	
	/*** 文件服务时间（秒） */
	public final static int TIME_DOC_FILE = 3 * 60;

	/*** 网关服务时间（秒） */
	public final static int TIME_GATEWAY = 30 * 60;

	/*** 文件服务剩余处理时间（秒） */
	public final static int TIME_REMAINING_DOC_FILE = 1 * 60;

	/*** 网关服务剩余处理时间（秒） */
	public final static int TIME_REMAINING_GATEWAY = 2 * 20;

	/*** 绑卡短信验证时间（秒） */
	public final static int TIME_BIND_CARD = 5 * 60;

	/*** PC图片验证码时间（秒） */
	public final static int TIME_PC_IMAGECODE = 5 * 60;

	/** 手机验证时间（秒） */
	public final static int TIME_PHONE = 30 * 60;  
	
	/** 交易密码验证时间（秒） */
	public final static int TIME_TRANSPWD = 1 * 60;

	/** 用户信息存储时间（秒） */
	public final static int TIME_USERINFO = 30 * 60;

	/***
	 * 放款文件key：map（当调用文件服务器生成借款文件接口，往redis中加入key+放款流水号
	 * ，时间2分钟，如果还剩下20秒没有返回，则调用查询接口查询文件生成结果）
	 */
	public final static String LOAN_DOC_FILE = REDIS_TASK + "loan_doc_file";

	/***
	 * 放款网关返回：key：map（当调用网关接口，往redis中加入key+放款流水号
	 * ，时间30分钟，如果还剩下1分钟没有返回，则调用查询接口查询结果）
	 */
	public final static String LOAN_GATEWAY = REDIS_TASK + "loan_gateway";

	/***
	 * 正常还款文件key：map（当调用文件服务器生成正常还款文件接口，往redis中加入key+正常还款流水号
	 * ，时间2分钟，如果还剩下20秒没有返回，则调用查询接口查询文件生成结果）
	 */
	public final static String REPAYMENT_DOC_FILE = REDIS_TASK + "repayment_doc_file";

	/***
	 * 正常还款网关返回：key：map（当调用网关接口，往redis中加入key+正常还款流水号
	 * ，时间30分钟，如果还剩下1分钟没有返回，则调用查询接口查询结果）
	 */
	public final static String REPAYMENT_GATEWAY = REDIS_TASK + "repayment_gateway";

	/***
	 * 代偿还款文件key：map（当调用文件服务器生成借款文件接口，往redis中加入key+代偿还款流水号
	 * ，时间2分钟，如果还剩下20秒没有返回，则调用查询接口查询文件生成结果）
	 */
	public final static String REPAYMENT_COMMUTING_DOC_FILE = REDIS_TASK + "repayment_commuting_doc_file";

	/***
	 * 代偿还款网关返回：key：map（当调用网关接口，往redis中加入key+代偿还款流水号
	 * ，时间30分钟，如果还剩下1分钟没有返回，则调用查询接口查询结果）
	 */
	public final static String REPAYMENT_COMMUTING_GATEWAY = REDIS_TASK + "repayment_commuting_gateway";

	/** 前端rest使用redis **/
	public final static String BATCHJOB_REST = "rest:";

	/** 登录用户信息 **/
	public final static String REST_USER = BATCHJOB_REST + "user";

	/** 图片信息 **/
	public final static String REST_IMAGE = BATCHJOB_REST + "image";

	/** 短信信息 **/
	public final static String REST_PHONE_SMS = BATCHJOB_REST + "phone_sms";

	/** 手机号校验信息 **/
	public final static String REST_PHONE_CHECK_TOKEN = BATCHJOB_REST + "phone_check_token";

	/** 绑定银行卡短信推送校验 **/
	public final static String REST_BIND_CARD_TICKET = BATCHJOB_REST + "bind_card_ticket";
	
	/** 解绑银行卡短信推送校验 **/
	public final static String REST_UNBIND_CARD_TICKET = BATCHJOB_REST + "unbind_card_ticket";

	/** 银行列表 **/
	public final static String REST_BANKS = BATCHJOB_REST + "banks";

	/*** trade使用redis ****/
	public final static String BATCHJOB_TRADE = "trade:";

	/** 绑定银行卡短信推送校验 **/
	public final static String TRADE_BIND_CARD_TICKET = BATCHJOB_TRADE + "bind_card_ticket";
	/** 解绑银行卡短信推送校验 **/
	public final static String TRADE_UNBIND_CARD_TICKET = BATCHJOB_TRADE + "unbind_card_ticket";

	/** 快捷充值推进参数 **/
	public final static String TRADE_CHARGE_TICKET = BATCHJOB_TRADE + "trade_charge_ticket";

	/** 剩余可投：（上架时放入）trade:project_last_项目编号 **/
	public final static String TRADE_PROJECT_LAST = BATCHJOB_TRADE + "project_last";	
	/** 投资队列：trade:project_invest_项目编号 **/
	public final static String TRADE_PROJECT_INVEST = BATCHJOB_TRADE+ "project_invest";
	/** 投资结果：trade:invest_result_userId_项目编号 **/
	public final static String TRADE_INVEST_RESULT = BATCHJOB_TRADE+ "invest_result";
	/**剩余可投：债券转让*/
	public final static String TRADE_ASSIGNMENT_LAST = BATCHJOB_TRADE +"assignment_last";
	/** 转让队列 ：trade:assignment_invest_转让编号*/
	public final static String TRADE_ASSIGNMENT_INVEST = BATCHJOB_TRADE+ "assignment_invest";
	/** 查询列表缓存*/
	public final static String TRADE_PROJECT_MYBATIS = BATCHJOB_TRADE+ "mybatis:";
	
	/*** busServer使用redis ****/
	public final static String BUSSERVER = "busServer:";
	/**
	 * 字典参数缓存前缀 最终redis中的key如： busServer:dictionary_001_deductDate
	 * */
	public final static String BUSSERVER_DICIONARY = BUSSERVER + "dictionary_";

	/**
	 * 序列号前缀 最终redis中的key如： busServer:sequence_no_号
	 * */
	public final static String BUSSERVER_SEQUENCE_NO = BUSSERVER+ "sequence_no_";

	/**
	 * 银行卡标识 最终redis中的key如： busServer:bank_belong_03050000_卡号前六位
	 * */
	public final static String BUSSERVER_BANK_BELONG = BUSSERVER + "bank_belong_";
	/**
	 * 字典银行卡标识最终redis中的key如： busServer:bank_dictionary_03050000
	 * */
	public final static String BUSSERVER_BANK_DICTIONARY = BUSSERVER + "bank_dictionary_";

	/*** instalment使用redis ****/
	public final static String INSTALMENT = "instalment:";

	/**
	 * 保单号 最终redis中的key如： instalment:ply
	 * */
	public final static String INSTALMENT_PLY = INSTALMENT + "ply_";
	
    /** 無效
     * 交易密码验证通过标识
     */
//    public final static String VALIDATE_TRANSPWD_PASS="trans_pwd_pass_";

	/*** middleware使用redis ****/
	public final static String MIDDLEWARE = "middleware:";
	
	/*** 各种交易查询前缀 ****/
	public final static String MIDDLEWARE_QUERY = MIDDLEWARE + "query_";
	
	/**app的版本数据*/
	public final static String REST_APP_VERSION = BATCHJOB_REST+"app_version";
	
	/**投资确认token*/
	public final static String REST_SUBMIT_TOKEN_INVEST = BATCHJOB_REST+"submitToken:invest";
	
	/**债券转让投资确认token*/
	public final static String REST_SUBMIT_TOKEN_ASSIGNMENT_INVEST = BATCHJOB_REST+"submitToken:assignment_invest";
	
	/**旧系统合同号后台掩码映射*/
	public final static String REST_SUBCONTRACTNO = BATCHJOB_REST+"subContractno:";
	/**自动投标前缀*/
	public final static String TASK_PROJECT_LAST_AUTOINVEST = REDIS_TASK+"project_last_autoInvest";
	/**下架*/
	public final static String TASK_PROJECT_ASSIGNMENT_SOLDOUT = REDIS_TASK+"project_assignment_soldout_";
	
	/** 登录用户信息 **/
	public final static String REST_REQUEST_TIME = BATCHJOB_REST + "request_time_";
	

	/*** gatewayWeChat使用redis ****/
	public final static String GATEWAYWECHAT = "gatewayWeChat:";
	/*** 微信token信息，存储2小时有效 ****/
	public final static String GATEWAYWECHAT_WX_TOKEN = GATEWAYWECHAT+"wx_token";
	/**微信token信息，存储2小时有效单位秒*/
	public final static long GATEWAYWECHAT_WX_TOKEN_TIME = 2*60*60;
	/**微信回调url信息*/
	public final static String GATEWAYWECHAT_WX_CALLBACK_URL = GATEWAYWECHAT+"callback_url";
	/**微信回调token信息*/
	public final static String GATEWAYWECHAT_WX_CALLBACK_TOKEN = GATEWAYWECHAT+"callback_token";
	/**可投资投资列表KEY*/
	public final static String TRADE_AVAILABLE_PROJECT = BATCHJOB_TRADE+"available_project";
	/**已满标投资列表KEY*/
	public final static String TRADE_FULL_PROJECT = BATCHJOB_TRADE+"full_project";
	/**已放款投资列表KEY*/
	public final static String TRADE_PUTOUT_PROJECT  = BATCHJOB_TRADE+"putout_project";
	/**实名成功，获取绑卡红包机会*/
	public final static String TRADE_COUPON_REALNAME_SUCCESS = BATCHJOB_TRADE+"COUPON_REALNAME_SUCCESS_";
}
