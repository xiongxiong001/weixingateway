package org.demoexm.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 正则表达式工具类
 */
public class RegexUtils {

	/**
	 * 链接host 
	 */
	public static final String HOST_REG = "(\\w*\\.)*\\w+\\.(com|net|cn|edu)";
	/**
	 * 获取链接地址
	 */
	public static final String HREF_REG="<a(\\.*?)href=\"(\\.*?)\"(\\.*?)>(\\.*?)</a>";
	
	/**
	 * 取中文正则表达式
	 */
	public static final String REGEX_CHINESE = "[\\u4e00-\\u9fa5]+";
	
    /**
     * 是否匹配
     * 
     * @param str
     * @param regex
     * @return
     */
    public static boolean isMatch(String str, String regex) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(regex)) {
            return false;
        }
        return StringUtils.isNotEmpty(match(str, regex)) ? true : false;
    }

    /**
     * 过滤原字符
     * 
     * @param str
     * @param regex
     * @return
     */
    public static String filter(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        if (StringUtils.isEmpty(regex)) {
            return str;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        str = m.replaceAll("");
        return str;
    }

    /**
     * 匹配原字符
     * 
     * @param str
     * @param regex
     * @return
     */
    public static String match(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        if (StringUtils.isEmpty(regex)) {
            return str;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.find() ? m.group() : "";
    }

    /**
     * 匹配原字符
     *
     * @param str
     * @param groupIndex
     * @since 2015/05/19
     * @return
     */
    public static String match(String str, Pattern pattern, int groupIndex) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        if(pattern == null){
            return "";
        }
        Matcher m = pattern.matcher(str);
        return m.find() ? m.group(groupIndex) : "";
    }
    
    /**
     * 匹配个数
     * 
     * @param str
     * @param regex
     * @return
     */
    public static int getMatchSize(String str, String regex) {
    	int size = 0;
        if (StringUtils.isEmpty(str)) {
            return size;
        }
        if (StringUtils.isEmpty(regex)) {
            return size;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        boolean result = m.find();
        while(result){
        	size ++;
        	result = m.find();
        }
        return size;
    }
    
    /**
     * 返回多个匹配结果
     * 
     * @param str
     * @param regex
     * @return
     */
    public static List<String> matchs(String str, String regex) {
    	List<String> list = new ArrayList<String>();
        if (StringUtils.isEmpty(str)) {
            return list;
        }
        if (StringUtils.isEmpty(regex)) {
            return list;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        int start = 0;
        boolean result = m.find(start);
        while(result){
        	list.add(m.group());
        	start = m.end();
        	result = m.find(start);
        }
        return list;
    }
    
    /**
     * 返回多个匹配结果
     * 
     * @param str
     * @param regex
     * @return
     */
    public static Map<String, String> matchs(String str, String regex, String separator) {
    	Map<String,String> map = new HashMap<String, String>();
        if (StringUtils.isEmpty(str)) {
            return map;
        }
        if (StringUtils.isEmpty(regex)) {
            return map;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        int start = 0;
        boolean result = m.find(start);
        while(result){
        	String [] arr = StringUtils.split(m.group(),separator);
        	map.put(StringUtils.trim(arr[0]),StringUtils.trim(arr[1]));
        	start = m.end();
        	result = m.find(start);
        }
        return map;
    }
    

    /**
     * 过滤敏感数据,关键部分用星号代替
     * 如手机号：13424425799 替换后 134****5799
     * 
     * @param sourceStr 
     * @param frontSize 前保留位数
     * @param backSize 后保留位数
     * @return
     */
    public static String filterSensitiveData(String sourceStr, int frontSize,
            int backSize) {
        String result = "";
        if (StringUtils.isNotEmpty(sourceStr)) {
            int len = sourceStr.length();
            if (len >= frontSize + backSize) {
                result = sourceStr.substring(0, frontSize)
                        + (sourceStr.substring(frontSize, sourceStr.length()
                                - backSize)).replaceAll(".", "*")
                        + sourceStr.substring(sourceStr.length() - backSize);
            }else {
                result = sourceStr.replaceAll(".", "*");
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("aaaaaaaaaaaa");
        System.out.println(RegexUtils.filter("(我爱你)bbb", "\\(\\)"));
        //过滤前一个括号里的 中文 
        System.out.println(RegexUtils.filter("(我爱你)bb我b", "\\([\\u4e00-\\u9fa5]+\\)"));
        System.out.println(RegexUtils.filter("17030496(红日在线信息科技有限公司)(15.0元15.0%)<br>17030496(红日在线信息科技有限公司)(15.0元15.0%)", "\\([\\u4e00-\\u9fa5]+\\)"));
        System.out.println(RegexUtils.isMatch("12/12/2012", "\\d{2}/\\d{2}/\\d{4}"));
        System.out.println(RegexUtils.isMatch("20121212", "\\d{8}"));
        System.out.println(RegexUtils.isMatch("2012-12-12", "\\d{4}-\\d{2}-\\d{2}"));
        System.out.println(RegexUtils.isMatch("2012/12/12", "\\d{4}/\\d{2}/\\d{2}"));
        
        String bankCardNo = "6227007200120897790";
        System.out.println(filterSensitiveData(bankCardNo,3,4));
        String mobilePhone = "13424425799";
        System.out.println(filterSensitiveData(mobilePhone,3,4));
        String realName = "文境恋";
        System.out.println(filterSensitiveData(realName,1,0));
        
        System.out.println("IP:" + RegexUtils.isMatch("192.168.7.134", "192\\.168\\.7\\.*"));
        System.out.println("IP:" + RegexUtils.isMatch("192.168.7.134", "192\\.168\\.7\\.[1-9]"));
        
        String reg = "\\n|\\s*|\\r";
        String str = "           aaaa            \n \n     ";
        System.out.println(RegexUtils.filter(str, reg));
        
        System.out.println(RegexUtils.match("2014年06月30日 星期一 (11：00--次日11：00)", "\\d{4}年\\d{2}月\\d{2}日"));
        
        System.out.println(RegexUtils.match("http://data.310win.com/changedetail/handicap.aspx?id=958505&companyid=3",HOST_REG));
        System.out.println(RegexUtils.getMatchSize("11111111111;","\\d"));
        System.out.println(RegexUtils.getMatchSize("2014年06月30日 星期一 (11：00--次日11：00)", "\\d{4}年\\d{2}月\\d{2}日"));
        
        String url = "http://tool.oschina.net/regex?shopId=asdfas343434dfsf-123123-fffffff";
        String shopId = "[\\?|&]?shopId=([\\w|-]*&?)";
        System.out.println(RegexUtils.match(url, shopId).replace("?","").replace("&",""));

        String lvkShopId = "[\\?&]shopId=([^&]*)";
        Pattern pattern = Pattern.compile(lvkShopId);
        System.out.println("##shopId=" + RegexUtils.match(url,pattern,1));
        
        System.out.println(RegexUtils.match("com.hyxt.weixin.WeiXinException: 签名错误", "[\\u4e00-\\u9fa5]+"));
    }
}
