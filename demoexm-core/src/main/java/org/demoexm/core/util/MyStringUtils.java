package org.demoexm.core.util;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.demoexm.core.contants.ControllerContants;

/**
 * 字符串工具类
 */
public class MyStringUtils
{
    
    /**
     * 集合转换为字符串
     * 
     * @param storeIds
     * @return
     */
    public static String list2Str(List<String> storeIds)
    {
        if (storeIds != null && storeIds.size() > 0)
        {
            StringBuffer sb = new StringBuffer(50);
            for (String storeId : storeIds)
            {
                if (storeId != null && storeId.length() > 0)
                {
                    sb.append("'").append(storeId).append("',");
                }
            }
            String result = sb.toString();
            if (result.length() > 0)
            {
                result = result.substring(0, result.length() - 1);
            }
            return result;
        }
        return null;
    }
    
    /**
     * ID字符串数组转换为List
     * 
     * @param storeIds
     * @return
     */
    public static List<String> strArr2List(String[] ids)
    {
        List<String> list = new ArrayList<String>();
        if (ids != null && ids.length > 0)
        {
            for (String id : ids)
            {
                if (id != null && id.length() > 0)
                {
                    list.add(id);
                }
            }
            return list;
        }
        return null;
    }
    
    /**
     * 加密字符串
     * 
     * @param str 字符串
     * @param head 前N位明文
     * @param end 后N位明文
     * @return
     * @author : chewneixian 陈惟鲜
     * @create_date 2016年12月8日 下午3:50:55
     */
    public static String encodeString(String str, int start, int end, String mask)
    {
        String encodeStr = mask;
        if (StringUtils.isEmpty(encodeStr))
        {
            encodeStr = "***";
        }
        
        String result = "";
        if (StringUtils.isEmpty(str))
        {
            return encodeStr;
        }
        String startStr = "";
        String endStr = "";
        if (str.length() > 0)
        {
            if (str.length() < start)
            {
                startStr = str;
            }
            else
            {
                startStr = str.substring(0, start);
            }
            if (str.length() > end)
            {
                endStr = str.substring(str.length() - end, str.length());
            }
        }
        result = startStr + encodeStr + endStr;
        return result;
    }
    
    public static String encodeString(String str, int start, int end)
    {
        return encodeString(str, start, end, "***");
    }
    
    /**
     * 获取指定位数随机字符串
     * 
     * @author 陈惟鲜
     * @create 2015年5月13日 下午21:02:43
     * @param length
     * @return
     * @description 目前原格式返回
     */
    public static String getRandomNum(int length)
    {
        StringBuffer sb = new StringBuffer(10);
        while (true)
        {
            Random rd = new Random();
            sb.append(rd.nextInt(9));
            if (sb.length() >= length)
            {
                break;
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 去除空字符串，包括 空格、回车、换行符、制表符
     * 
     * @param str
     * @return
     */
    public static String trimEmpty(String str)
    {
        if (null == str)
        {
            return null;
        }
        return str.replaceAll("[\uFEFF//s*\t\n\r]", "");
    }
    
    /**
     * 获取字符的编码格式
     * 
     * @param str
     * @return
     */
    public static String getEncoding(String str)
    {
        String[] encodes = {ControllerContants.CHARSET_UTF8, "ISO-8859-1", "GB2312", ControllerContants.CHARSET_GBK};
        for (String encode : encodes)
        {
            try
            {
                if (str.equals(new String(str.getBytes(encode), encode)))
                {
                    String s2 = encode;
                    return s2;
                }
            }
            catch (Exception exception)
            {
            }
        }
        return "";
    }
    
    /**
     * 填充0 创建人：陈惟鲜 author：chenweixian 日期时间：2015年12月4日下午2:15:33
     */
    public static String fillZero(String seq, int length)
    {
        StringBuffer sb = new StringBuffer();
        int mun = length - seq.length();
        if (mun > 0)
        {
            for (int i = 0; i < mun; i++)
            {
                sb.append("0");
            }
        }
        sb.append(seq);
        return sb.toString();
    }
    
    /**
     * 获取exception详情信息
     * 
     * @param e 异常对象
     * @return
     * @author 陈惟鲜
     * @date 2016年3月1日 上午10:21:53
     */
    public static String getExceptionDetail(Exception e)
    {
        StringBuffer msg = new StringBuffer("null");
        if (e != null)
        {
            msg = new StringBuffer("");
            String message = e.toString();
            int length = e.getStackTrace().length;
            if (length > 0)
            {
                msg.append(message + "\n");
                for (int i = 0; i < length; i++)
                {
                    msg.append("\t" + e.getStackTrace()[i] + "\n");
                }
            }
            else
            {
                msg.append(message);
            }
        }
        return msg.toString();
    }
    
    /**
     * 去掉头部截取字符串
     * 
     * @param head 头信息
     * @param str 要截取信息的字符串
     * @return
     * @author 陈惟鲜
     * @date 2016年3月10日 下午4:44:08
     */
    public static String subStringByHead(String head, String str)
    {
        return str.substring(head.length(), str.length());
    }
    
    /**
     * 数字对象转换为转换为字符串
     * 
     * @param head 头信息
     * @param str 要截取信息的字符串
     * @return
     * @author 陈惟鲜
     * @date 2016年3月10日 下午4:44:08
     */
    public static String strValue(Object IntegerStr)
    {
        if (IntegerStr == null)
        {
            IntegerStr = 0;
        }
        return IntegerStr + "";
    }
    
    /**
     * 数字对象转换为转换为字符串
     * 
     * @param head 头信息
     * @param str 要截取信息的字符串
     * @return
     * @author 陈惟鲜
     * @date 2016年3月10日 下午4:44:08
     */
    public static int strInt2Value(Object IntegerStr)
    {
        int result = 0;
        if (IntegerStr == null)
        {
            IntegerStr = 0;
        }
        try
        {
            result = Integer.parseInt(IntegerStr.toString());
        }
        catch (Exception e)
        {
        }
        return result;
    }
    
    /**
     * 字符串判空处理，并去空格等
     * 
     * @param value
     * @return
     */
    public static String strValue(String value)
    {
        String tmpValue = "";
        if (value != null)
        {
            tmpValue = trimEmpty(value);
        }
        
        return tmpValue;
    }
    
    /**
     * 字符串判空处理，并去空格等
     * 
     * @param value
     * @return
     */
    public static String getString(Object value)
    {
        String tmpValue = "";
        if (value != null)
        {
            tmpValue = value.toString();
        }
        return tmpValue;
    }
    
    /**
     * 获取当前服务器的IP
     * 
     * @return
     * @author 陈惟鲜
     * @date 2016年5月27日 上午11:47:29
     */
    public static String getIpString()
    {
        String tmpValue = "";
        InetAddress ia = null;
        try
        {
            ia = ia.getLocalHost();
            tmpValue = ia.getHostAddress();
        }
        catch (Exception e)
        {
        }
        
        return tmpValue;
    }
    
    /**
     * 根据身份证号码获取性别 身份证倒数第二位奇数男，偶数女
     * 
     * @param centNo
     * @return N男；W女；空字符串：身份证错误
     * @author 陈惟鲜
     * @date 2016年6月17日 上午11:37:23
     */
    public static String getSexByCentNo(String centNo)
    {
        String result = "";
        if (flagCentNo(centNo))
        {
            String last2 = centNo.substring(16, 17);
            int sex = Integer.parseInt(last2);
            if (sex % 2 == 1)
            {
                result = "M";// 男
            }
            else
            {
                result = "W";// 女
            }
        }
        return result;
    }
    
    /**
     * 根据身份证号码获取生日 身份证倒数第二位奇数男，偶数女
     * 
     * @param centNo
     * @return N男；W女；空字符串：身份证错误
     * @author 陈惟鲜
     * @date 2016年6月17日 上午11:37:23
     */
    public static String getBirthdayByCentNo(String centNo)
    {
        String result = "";
        if (flagCentNo(centNo))
        {
            String birthday7 = centNo.substring(6, 14);
            Date date = MyDateUtil.dateStrToDate(MyDateUtil.DATE_FMT_EN, birthday7);
            result = MyDateUtil.formatDate(date);
        }
        return result;
    }
    
    /**
     * 根据身份证号码获取年龄
     * 
     * @param centNo
     * @return N男；W女；空字符串：身份证错误
     * @author 陈惟鲜
     * @date 2016年6月17日 上午11:37:23
     */
    public static int getAgeByCentNo(String centNo)
    {
        int result = 0;
        if (flagCentNo(centNo))
        {
            String birthday7 = centNo.substring(6, 14);
            Date date = MyDateUtil.dateStrToDate(MyDateUtil.DATE_FMT_EN, birthday7);
            result = MyDateUtil.getYear(MyDateUtil.getCurrentDate()) - MyDateUtil.getYear(date);
        }
        return result;
    }
    
    /**
     * 身份证信息验证
     * 
     * @return N男；W女；空字符串：身份证错误
     * @author 陈惟鲜
     * @date 2016年6月17日 上午11:37:23
     */
    public static boolean flagCentNo(String centNo)
    {
        boolean result = false;
        if (!StringUtils.isEmpty(centNo))
        {
            if (centNo.length() == 18)
            {// 身份证18位
                result = true;
            }
        }
        return result;
    }
    
    /**
     * 转换字符串数组为,分隔
     * 
     * @param arr
     * @return
     * @author 陈惟鲜
     * @date 2016年7月13日 下午2:07:31
     */
    public static String arrToStr(String[] arr)
    {
        String result = "";
        if (arr != null)
        {
            StringBuffer sb = new StringBuffer("");
            for (String str : arr)
            {
                sb.append(str).append(",");
            }
            result = sb.substring(0, sb.length() - 1);
        }
        return result;
    }
    
    /**
     * 获取字符串中开始到第一个逗号出现位置的字符串
     * 
     * @param str
     * @return
     * @author : chewneixian 陈惟鲜
     * @create_date 2016年8月8日 下午5:03:29
     */
    public static String getFirstString(String str)
    {
        String result = str;
        if (StringUtils.isEmpty(result))
        {
            return "";
        }
        if (result.indexOf(",") != -1)
        {// 因为通过url传参数，会有
            result = result.substring(0, result.indexOf(","));
        }
        return result;
    }
    
    /**
     * 获取字符串的字节数，中文算2字节
     * 
     * @param str
     * @return
     * @author yanglei
     * @date 2016年9月5日 下午6:07:52
     */
    public static int getStrLengthRegex(String str)
    {
        str = str.replaceAll("[^\\x00-\\xff]", "**");
        return str.length();
    }
    
    /**
     * 判断字符串是否仅数字
     * 
     * @author 陈惟鲜
     * @date 2016年12月5日 上午9:52:26
     * @param str
     * @return true仅数字;false否
     */
    public static boolean flagOnlyNumber(String str)
    {
        boolean result = false;
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher((CharSequence)str);
        result = matcher.matches();
        return result;
    }
    
    public static void main(String[] args)
    {
        // System.out.println(getEncoding("你好"));
        System.out.println(MyStringUtils.encodeString("a393060727@163.com", 3, 1,""));
        System.out.println(MyStringUtils.encodeString("15013550424", 3, 0));
        System.out.println(MyStringUtils.encodeString("123", 3, 3));
        // System.out.println(MyStringUtils.encodeUserId("a8"));
        // System.out.println(MyStringUtils.encodeUserId("12345674859123416496"));
        // System.out.println(MyStringUtils.getRandomNum(4));
        // String str = "﻿交易时间";
        // System.out.println(MyStringUtils.trimEmpty(str).length());
        // System.out.println(MyStringUtils.subStringByHead("HEAD_", "HEAD_chenweixian"));
        // String centNo = "452224198512143018";
        // System.out.println(MyStringUtils.getBirthdayByCentNo(centNo));
        // System.out.println(MyStringUtils.getSexByCentNo(centNo));
        // System.out.println(MyDateUtil.getYear(new Date()));
        // System.out.println(MyDateUtil.getYear(MyDateUtil.getDateByStr("1985-12-14")));
        // System.out.println(MyStringUtils.getAgeByCentNo(centNo));
        //
        // System.out.println(MyStringUtils.encodeString("15013550424"));
        // System.out.println(flagOnlyNumber("20160908"));
        // System.out.println(flagOnlyNumber("bb20160"));
        // System.out.println(flagOnlyNumber("陈20160"));
        // System.out.println(flagOnlyNumber("_20160"));
    }
    
    
    /** 
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**> 
     *  
     * @param name 
     * @return 
     */  
    public static String chineseName(String fullName) {  
        if (StringUtils.isBlank(fullName)) {  
            return "";  
        }  
        String name = StringUtils.left(fullName, 1);  
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");  
    }  
  
    /** 
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**> 
     *  
     * @param familyName 
     * @param givenName 
     * @return 
     */  
    public static String chineseName(String familyName, String givenName) {  
        if (StringUtils.isBlank(familyName) || StringUtils.isBlank(givenName)) {  
            return "";  
        }  
        return chineseName(familyName + givenName);  
    }  
  
    /** 
     * [身份证号] 显示最后四位，其他隐藏。共计18位或者15位。<例子：*************5762> 
     *  
     * @param id 
     * @return 
     */  
    public static String idCardNum(String id) {  
        if (StringUtils.isBlank(id)) {  
            return "";  
        }  
        String num = StringUtils.right(id, 4);  
        return StringUtils.leftPad(num, StringUtils.length(id), "*");  
    }  
  
    /** 
     * [固定电话] 后四位，其他隐藏<例子：****1234> 
     *  
     * @param num 
     * @return 
     */  
    public static String fixedPhone(String num) {  
        if (StringUtils.isBlank(num)) {  
            return "";  
        }  
        return StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*");  
    }  
  
    /** 
     * [手机号码] 前三位，后四位，其他隐藏<例子:138******1234> 
     *  
     * @param num 
     * @return 
     */  
    public static String mobilePhone(String num) {  
        if (StringUtils.isBlank(num)) {  
            return "";  
        }  
        return StringUtils.left(num, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"), "***"));  
    }  
  
    /** 
     * [地址] 只显示到地区，不显示详细地址；我们要对个人信息增强保护<例子：北京市海淀区****> 
     *  
     * @param address 
     * @param sensitiveSize 
     *            敏感信息长度 
     * @return 
     */  
    public static String address(String address, int sensitiveSize) {  
        if (StringUtils.isBlank(address)) {  
            return "";  
        }  
        int length = StringUtils.length(address);  
        return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");  
    }  
  
    /** 
     * [电子邮箱] 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示<例子:g**@163.com> 
     *  
     * @param email 
     * @return 
     */  
    public static String email(String email) {  
        if (StringUtils.isBlank(email)) {  
            return "";  
        }  
        int index = StringUtils.indexOf(email, "@");  
        if (index <= 1)  
            return email;  
        else  
            return StringUtils.rightPad(StringUtils.left(email, 1), index, "*").concat(StringUtils.mid(email, index, StringUtils.length(email)));  
    }  
  
    /** 
     * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:6222600**********1234> 
     *  
     * @param cardNum 
     * @return 
     */  
    public static String bankCard(String cardNum) {  
        if (StringUtils.isBlank(cardNum)) {  
            return "";  
        }  
        return StringUtils.left(cardNum, 6).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));  
    }  
  
    /** 
     * [公司开户银行联号] 公司开户银行联行号,显示前两位，其他用星号隐藏，每位1个星号<例子:12********> 
     *  
     * @param code 
     * @return 
     */  
    public static String cnapsCode(String code) {  
        if (StringUtils.isBlank(code)) {  
            return "";  
        }  
        return StringUtils.rightPad(StringUtils.left(code, 2), StringUtils.length(code), "*");  
    }  
}
