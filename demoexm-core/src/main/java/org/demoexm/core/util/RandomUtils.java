package org.demoexm.core.util;

import java.util.Random;

public class RandomUtils {
	public static String cRandom(int m) { // 产生1个长度为m只含有字母的随机字符串        
		char[] chs = new char[m];        
		for (int i = 0; i < m; i++) {            
			chs[i] = cNumOrCharRandom();        
		}        
		return new String(chs);    
	}        
	public static char cNumOrCharRandom() {// 产生一个随机数字或字母        
		String temp = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";        
		return (char) temp.charAt(iRandom(0, 61));    
	}         
	public static int iRandom(int m, int n) { // 产生一个[m,n)之间的随机整数        
		Random random = new Random();        
		int small = m > n ? n : m;        
		int big = m <= n ? n : m;        
		return small + random.nextInt(big - small);    
	}
	/**
	 * 生成随机length长度的整数字符串
	 * @param length
	 * @return
	 * @author zhangfeng
	 * @date 2016年5月23日 下午4:25:02
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	/**
	 * 取得一个小写的字母
	 * @return
	 */
	public static String getOneChar(){
		String chars = "abcdefghijklmnopqrstuvwxyz";
		StringBuffer sb = new StringBuffer();
		char charAt = chars.charAt((int)(Math.random() * 26));		
		return sb.append(String.valueOf(charAt)).toString();
	}
	/**
	 * 产生一个随机数
	 * @return
	 */
	public static String getNumRandom(){
		 int i = new Random().nextInt();
		 return String.valueOf(i);
	}
	
	public static void main(String[] args) {
		System.out.println(cRandom(6));
//		 String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";   
//	        char[] c = s.toCharArray();   
//	        Random random = new Random();   
//	        for( int i = 0; i < 8; i ++) {   
//	            System.out.println(c[random.nextInt(c.length)]);   
//	        } 
//		String chars = "abcdefghijklmnopqrstuvwxyz";
//		System.out.println(chars.charAt((int)(Math.random() * 26)));		
		System.out.println(new Random().nextInt());

	}
}
