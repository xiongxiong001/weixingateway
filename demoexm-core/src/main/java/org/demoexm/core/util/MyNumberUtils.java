package org.demoexm.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;

/**
 * BigDecimal 对应的加减乘除、格式化等方法<br />
 * 陈惟鲜
 * 
 * @author chenwx 2012-11-8
 */
public class MyNumberUtils
{
    
    /**
     * 默认返回小数2位
     */
    public static final int DEFAULT_SCALE = 2;
    
    /**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    
    /**
     * 汉语中货币单位大写，这样的设计类似于占位符
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT = {"分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟"};
    
    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "整";
    
    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";
    
    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;
    
    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;
    
    /**空处理*/
    
    public static long nvlFormat(BigDecimal value){
    	return value==null?0:value.longValue();
    }
    
    /**空处理*/
    public static long nvlFormat(Long value){
    	return value==null?0:value.longValue();
    }
    
    /**空处理*/
    public static long nvlFormat(String value){
    	return StringUtils.isEmpty(value)?0:Long.valueOf(value);
    }
    
    public static String nvlFormatString(String value){
    	return StringUtils.isEmpty(value)?"0":value;
    }
    
    /**
     * 提供精确的加法运算。
     * 
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static BigDecimal add(BigDecimal b1, BigDecimal b2)
    {
    	if(b1==null) b1 = BigDecimal.ZERO;
    	if(b2==null) b2 = BigDecimal.ZERO;
        return b1.add(b2);
    }
    
    /**
     * 提供精确的减法运算。
     * 
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(BigDecimal b1, BigDecimal b2)
    {
    	if(b1==null) b1 = BigDecimal.ZERO;
    	if(b2==null) b2 = BigDecimal.ZERO;
        return b1.subtract(b2);
    }
    
    /**
     * 提供精确的乘法运算。
     * 
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(BigDecimal b1, BigDecimal b2)
    {
    	if(b1==null) b1 = BigDecimal.ZERO;
    	if(b2==null) b2 = BigDecimal.ZERO;
        return b1.multiply(b2);
    }
    
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal b1, BigDecimal b2)
    {
    	if(b1==null) b1 = BigDecimal.ZERO;
    	if(b2==null) throw new IllegalArgumentException("分母为空！");
        return div(b1, b2, DEFAULT_SCALE);
    }
    
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal b1, BigDecimal b2, int scale)
    {
    	if(b1==null) b1 = BigDecimal.ZERO;
    	if(b2==null)  throw new IllegalArgumentException("分母为空！");
        if (scale < 0)
        {
            throw new IllegalArgumentException("默认小数位必须大于或者0！");
        }
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static BigDecimal round(BigDecimal b, int scale)
    {
    	if(b==null) b = BigDecimal.ZERO;
        if (scale < 0)
        {
            throw new IllegalArgumentException("默认小数位必须大于或者0！");
        }
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 格式化信息内容
     * 
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 格式化后的结果
     */
    public static String formatToStr(BigDecimal b, int scale)
    {
    	if(b==null) b = BigDecimal.ZERO;
        String result = b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
        
        return result;
    }
    
    /**
     * 格式化信息内容
     * 
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 格式化后的结果
     */
    public static String format(BigDecimal b, int scale)
    {
    	if(b==null) b = BigDecimal.ZERO;
        if (scale < 0)
        {
            throw new IllegalArgumentException("默认小数位必须大于或者0！");
        }
        StringBuffer formatString = new StringBuffer("###,##0");
        if (scale > 0)
        {
            formatString.append(".");
            for (int i = 0; i < scale; i++)
            {
                formatString.append("0");
            }
        }
        NumberFormat numberFormat = new DecimalFormat(formatString.toString());
        
        return numberFormat.format(b);
    }
    
    /**
     * 字符串转换为数据类型
     * 
     * @param str
     * @return
     */
    public static BigDecimal stringToBigDecimal(String str)
    {
        if (!StringUtils.isEmpty(str))
        {
            return new BigDecimal(str);
        }
        return null;
    }
    
    /**
     * 元转换为分
     * 
     * @param value
     * @return
     */
    public static Long yuan2Fen(String value){
    	
    	return yuan2FenBd(new BigDecimal(nvlFormatString(value))).longValue();
    }
    public static Long yuan2Fen(Long value)
    {
    	return yuan2FenBd(new BigDecimal(value)).longValue();
    }
    
    /**
     * 元转换为分
     * 
     * @param value
     * @return
     */
    public static Long yuan2Fen(BigDecimal value)
    {
    	return yuan2FenBd(value).longValue();
    }
    
    /**
     * 元转换为分
     * 
     * @param value
     * @return
     */
    public static BigDecimal yuan2FenBd(BigDecimal value)
    {
        if (value == null)
        {
            return BigDecimal.ZERO;
        }
        BigDecimal fen = mul(value, new BigDecimal(100));
        return new BigDecimal(fen.intValue());
    }
    
    /**
     * 元转换为分
     * 
     * @param strAmt
     * @return
     */
    public static Long yuan2FenLong(String value)
    {
    	return yuan2FenBd(new BigDecimal(nvlFormatString(value))).longValue();
    }
    
    /**
     * 元转换为分
     * 
     * @param value
     * @return
     */
    public static Long yuan2FenLong(BigDecimal value)
    {
    	return yuan2FenBd(value).longValue();
    }
    
    /**
     * 分转换为元
     * 
     * @param value
     * @return
     */
    public static BigDecimal fen2Yuan(Integer value)
    {
    	return div100(value);
    }
    
    /**
     * 分转换为元
     * 
     * @param value
     * @return
     */
    public static BigDecimal fen2Yuan(String value)
    {
    	return div100(value);
    }
    
    /**
     * 分转换为元
     * 
     * @param value
     * @return
     */
    public static BigDecimal fen2Yuan(Long value)
    {
    	return div100(value);
    }
    
    /**
     * 分转换为元,返回String类型结果
     * 
     * @param value
     * @return
     */
    public static String fen2YuanStr(String value)
    {
    	return div100(value).toString();
    }
    
    /**
     * 分转换为元,返回String类型结果
     * 
     * @param value
     * @return
     */
    public static String fen2YuanStr(Long value)
    {
    	return div100(value).toString();
    }
    
    
    /**
     * String转换BigDecimal
     * 
     * @param value
     * @return
     */
    public static BigDecimal str2BigDecimal(String value, int scale)
    {
        return StringUtils.isNotEmpty(value) ? new BigDecimal(value).setScale(scale, BigDecimal.ROUND_DOWN) : null;
    }
    
    
    
    /** 除以100，保留2位小数 */
    public static BigDecimal div100(Object number)
    {
        if (number == null)
        {
            number = "0";
        }
        if (StringUtils.isEmpty(number.toString()))
        {
            number = "0";
        }
        return div(new BigDecimal(number.toString()), new BigDecimal(100), 2);
    }
    
    /** 乘以100，保留2位小数 */
    public static BigDecimal mul100(Object number)
    {
        if (number == null)
        {
            number = "0";
        }
        if (StringUtils.isEmpty(number.toString()))
        {
            number = "0";
        }
        return mul(new BigDecimal(number.toString()), new BigDecimal(100));
    }
    
    /** 乘以x */
    public static BigDecimal mulx(Object number, int x)
    {
        if (number == null)
        {
            number = "0";
        }
        if (StringUtils.isEmpty(number.toString()))
        {
            number = "0";
        }
        return mul(new BigDecimal(number.toString()), new BigDecimal(x));
    }
    
    /**
     * 转换数字为字符串
     * 
     * @param number
     * @return
     * @author 陈惟鲜
     * @date 2016年2月3日 上午11:39:28
     */
    public static String strValue(BigDecimal number)
    {
        return number.intValue() + "";
    }
    
    /**
     * 加
     * 
     * @param a 加数
     * @param b 被加数
     * @return 和
     * @author 陈惟鲜
     * @date 2016年3月19日 下午2:11:19
     */
    public static Long add(Long a, Long b)
    {
        if (a == null)
        {
            a = 0L;
        }
        if (b == null)
        {
            b = 0L;
        }
        return a + b;
    }
    
    /**
     * 减
     * 
     * @param a 减数
     * @param b 被减数
     * @return 余
     * @author 陈惟鲜
     * @date 2016年3月19日 下午2:11:19
     */
    public static Long sub(Long a, Long b)
    {
        if (a == null)
        {
            a = 0L;
        }
        if (b == null)
        {
            b = 0L;
        }
        return a - b;
    }
    
    /**
     * 乘
     * 
     * @param a 乘数
     * @param b 被乘数
     * @return 积
     * @author 陈惟鲜
     * @date 2016年3月19日 下午2:11:19
     */
    public static Long mul(Long a, Long b)
    {
        if (a == null)
        {
            a = 0L;
        }
        if (b == null)
        {
            b = 0L;
        }
        return a * b;
    }
    
    /**
     * 乘
     * 
     * @param a 乘数
     * @param b 被乘数
     * @return 积
     * @author 陈惟鲜
     * @date 2016年3月19日 下午2:11:19
     */
    public static Long mulLong100(Long a)
    {
        return mul(a, 100L) * 100;
    }
    
    public static Long mulInt100(String a)
    {
        return mul(new BigDecimal(nvlFormatString(a)).longValue(), 100L) * 100;
    }
    
    /**
     * 除
     * 
     * @param a 除数
     * @param b 被除数
     * @return 商
     * @author 陈惟鲜
     * @date 2016年3月19日 下午2:11:19
     */
    public static BigDecimal div(Long a, Long b, int scale)
    {
        if (b == 0)
        {
            throw new RuntimeException("被除数不能为0");
        }
        return div(new BigDecimal(a), new BigDecimal(b), scale);
    }
    
    public static Long div(Long a, Long b)
    {
        return div(a, b, 0).longValue();
    }
    
    public static BigDecimal divInt100(Long a, int scale)
    {
        return div(a, 100L, scale);
    }
    
    /**
     * 把输入的金额转换为汉语中人民币的大写
     * 
     * @param numberOfMoney 输入的金额
     * @return 对应的汉语大写
     */
    public static String number2CNMontrayUnit(String money)
    {
        BigDecimal numberOfMoney = new BigDecimal(nvlFormatString(money));
        StringBuffer sb = new StringBuffer();
        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
        // positive.
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0)
        {
            return CN_ZEOR_FULL;
        }
        // 这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0))
        {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0)))
        {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true)
        {
            if (number <= 0)
            {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int)(number % 10);
            if (numUnit > 0)
            {
                if ((numIndex == 9) && (zeroSize >= 3))
                {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3))
                {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            }
            else
            {
                ++zeroSize;
                if (!(getZero))
                {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2)
                {
                    if (number > 0)
                    {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                }
                else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0))
                {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1)
        {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0))
        {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }
    
    /**
     * 测试
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        // BigDecimal v1 = new BigDecimal("10.58");
        // BigDecimal v2 = new BigDecimal("10");
        // BigDecimal v3 = new BigDecimal("5555555555.2722222226");
        // System.out.println("(MyNumberUtils.add(v1, v2)="+MyNumberUtils.add(v1, v2));
        // System.out.println("MyNumberUtils.sub(v1, v2)="+MyNumberUtils.sub(v1, v2));
        // System.out.println("MyNumberUtils.mul(v1, v2)="+MyNumberUtils.mul(v1, v2));
        // System.out.println("MyNumberUtils.div(v1, v2)="+MyNumberUtils.div(v1, v2));
        // System.out.println("MyNumberUtils.div(v1, v2,3)="+MyNumberUtils.div(v1, v2,3));
        // System.out.println(MyNumberUtils.round(v3, 1));
        // System.out.println(MyNumberUtils.formatToStr(v3, 5));
        // System.out.println(new BigDecimal(1/100).setScale(2));
        // BigDecimal d = fen2Yuan(2);
        // System.out.println(yuan2Fen(2));
        // System.out.println(formatToStr(new BigDecimal("523.3694554"), 3));
    	Long a = 5L;
    	Long b = 7L;
        
        System.out.println(MyNumberUtils.add(a, b));
        System.out.println(MyNumberUtils.sub(a, b));
        System.out.println(MyNumberUtils.mul(a, b));
        System.out.println(MyNumberUtils.add(a, MyNumberUtils.sub(a, b)));
        
        System.out.println(str2BigDecimal("111.2223677",0));

        System.out.println(fen2Yuan(5036L));
        System.out.println(fen2Yuan(5036));
        System.out.println(fen2Yuan("5036"));
        System.out.println(fen2YuanStr(5036L));
        System.out.println(fen2YuanStr("5036"));
        

        System.out.println(yuan2Fen(new BigDecimal(50.36)));
        System.out.println(yuan2Fen(50L));
        System.out.println(yuan2Fen("50.36"));
        System.out.println(yuan2FenBd(new BigDecimal("50.36")));
        System.out.println(yuan2FenLong(new BigDecimal("50.36")));
        System.out.println(yuan2FenLong("50.36"));
    }
}

