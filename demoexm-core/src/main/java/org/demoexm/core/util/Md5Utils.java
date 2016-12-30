//package com.iafclub.baseTools.util;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
///**
// * Created by Carmel on 2014/11/13.
// */
//public class Md5Utils {
//	public final static String encode(String s) {
//		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//		byte[] btInput = s.getBytes();
//		// 获得MD5摘要算法的 MessageDigest 对象
//		try {
//			MessageDigest mdInst = MessageDigest.getInstance("MD5");
//			// 使用指定的字节更新摘要
//			mdInst.update(btInput);
//			// 获得密文
//			byte[] md = mdInst.digest();
//			// 把密文转换成十六进制的字符串形式
//			int j = md.length;
//			char str[] = new char[j * 2];
//			int k = 0;
//			for (int i = 0; i < j; i++) {
//				byte byte0 = md[i];
//				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
//				str[k++] = hexDigits[byte0 & 0xf];
//			}
//			return new String(str).toLowerCase();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
//
//	public static void main(String[] args) {
//		System.out.println(encode("111111"));
//		System.out.println(encode("111111").length());
//	}
//}
