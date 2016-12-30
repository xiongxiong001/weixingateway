package org.demoexm.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassWordUtil {

	/**
	 * 校验密码
	 * <li>以字母开头，长度在6~18之间，只能包含字符、数字和下划线  不能全是数字 不能全是字母 
	 * @param passwrod
	 * @return
	 * @author zhangfeng
	 * @date 2016年8月9日 下午4:57:27
	 */
	public static boolean checkPassWord(String passwrod){
		Pattern pattern = Pattern.compile("(?!^\\d+$)(?!^[a-zA-Z]+$)^[a-zA-Z]\\w{5,17}$");
		Matcher matcher = pattern.matcher(passwrod);
		return matcher.matches() ;
	}
}
