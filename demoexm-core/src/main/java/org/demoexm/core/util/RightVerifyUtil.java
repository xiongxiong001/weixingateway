package org.demoexm.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 各类数据正确性校验
 * 
 * @author zhangfeng
 * @date 2016年5月13日 下午6:15:29
 *
 */
public class RightVerifyUtil {

	public static void main(String[] args) {
	//	System.out.println(checkEmail("14_8@qw.df"));
	//	System.out.println(checkMobileNumber("(+86)18620390306"));
	//	System.out.println(checkTel("0376-88888888"));
		System.out.println(checkIdent("430723880701541"));
	}

	/**验证邮箱 * 
	 * @param email 
	 * @return */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static boolean checkTel(String tel) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("([0-9]{3,4}-)?[0-9]{7,8}");
			Matcher matcher = regex.matcher(tel);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	/**
	 * 身份证号码
	 * @param tel
	 * @return
	 * @author zhangfeng
	 * @date 2016年5月17日 下午5:28:40
	 */
	public static boolean checkIdent(String tel) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +  
            "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +  
            "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))");
			Matcher matcher = regex.matcher(tel);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	/**验证手机号码 
	  *@param mobiles  
	  *验证是否手机号(原)：Pattern.compile(?:(\\(\\+?86\\))((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|" +     
                						"(?:86-?((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|" +
                						"(?:((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})
	  * @return */
	
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("[1][0-9]{10}");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
