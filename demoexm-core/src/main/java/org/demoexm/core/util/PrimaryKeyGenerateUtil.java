package org.demoexm.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 主键生成工具
 * 
 * @author weichengpei
 * @description
 */
public class PrimaryKeyGenerateUtil {
	/** 主键-流水号长度 */
	private static final int SERIAL_NUMBER_LENGTH = 32;

	private static Random randGen = null;

	private static char[] numbersAndLetters = null;

	/**
	 * 生成指定位数流水号字符串
	 * @author weichengpei 
	 * @param prefixNo 流水号前缀
	 * @param length 流水号长度, 不传或小于于(12+流水号前缀长度)默认为32位
	 * @return
	 * @description 
	 */
	public static final String generateStringPrimaryKey(String prefixNo, Integer length) {
		if (null == prefixNo) {
			return null;
		}
		Date data = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		String dataString = df.format(data);
		int lessLength = dataString.length() + prefixNo.length(); //最小流水号长度
		if (null == length || length < lessLength) {
			length = SERIAL_NUMBER_LENGTH;
		}
		String randomString = randomString(length - lessLength);
		return prefixNo + dataString + randomString;
	}

	/**
	 * 生成随机的指定长度字符串
	 * 
	 * @author weichengpei
	 * @param length
	 * @return
	 */
	private static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
					+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static void main(String[] args) {
		System.out.println(generateStringPrimaryKey("HY", 64));
	}
}
