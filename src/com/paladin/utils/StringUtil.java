package com.paladin.utils;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author palad 字符串工具类
 */
public class StringUtil {

	public enum IP_TYPE {
		IPV4, IPV6
	};

	public enum SPLIT_DIRECTOR {
		SPLIT_DIRECTOR_FORWARD,SPLIT_DIRECTOR_REVERSE
	};

	public enum SPLIT_TYPE {
		BEFORE, AFTER
	};
	
	public StringUtil() {
		throw new IllegalAccessError();
	}

	public static boolean isEmpty(String str) {
		return null == str || "".equals(str);
	}

	/**
	 * IP地址有效性校验
	 * 
	 * @param ip
	 * @param type
	 *            有IPv4以及IPV6的区别
	 * @return
	 */
	public static boolean checkIp(String ip, IP_TYPE type) {
		Pattern pattern = Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){" + (type == IP_TYPE.IPV4 ? 3 : 5) + "}" + "(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$");
		return pattern.matcher(ip).matches();
	}

	/**
	 * 随机生成一个字符，字符集为 0~9, a~z, A~Z
	 * 
	 * @return
	 */
	public static char genRandomChar() {
		char r = '0';
		Random random = new Random();
		int n = random.nextInt(58);
		if (n < 10) {
			return (char) (48 + n);
		}
		n -= 10;
		if (n < 26) {
			return (char) (65 + n);
		}
		n -= 26;
		if (n < 26) {
			return (char) (97 + n);
		}
		return r;
	}

	/**
	 * 分割字符串
	 * 
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String[] split(String str, String reg) {
		if (isEmpty(str))
			return null;
		if (isEmpty(reg))
			return new String[] { str };
		return str.split(reg);
	}

	/**
	 * 字符串截取
	 * @param str
	 * @param reg
	 * @param splitType
	 * @return
	 */
	private static String subString(String str, String reg, SPLIT_TYPE splitType, SPLIT_DIRECTOR splitDir) {
		if (isEmpty(str))
			return null;
		if (isEmpty(reg))
			return str;
		int index = 0;
		if(splitDir == SPLIT_DIRECTOR.SPLIT_DIRECTOR_FORWARD) index = str.indexOf(reg);
		if(splitDir == SPLIT_DIRECTOR.SPLIT_DIRECTOR_REVERSE) index = str.lastIndexOf(reg);
		String subString = null;
		if (index < 0)
			subString = str;
		if (index >= 0) {
			if (splitType == SPLIT_TYPE.BEFORE)
				subString = str.substring(0, index);
			if (splitType == SPLIT_TYPE.AFTER)
				subString = str.substring(index + 1);
		}
		return subString;
	}

	/**
	 * 截取字符串，取指定截取字符之前部分
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String subStringBefore(String str, String reg) {
		return subString(str, reg, SPLIT_TYPE.BEFORE,SPLIT_DIRECTOR.SPLIT_DIRECTOR_FORWARD);
	}

	/**
	 * 截取字符串，取指定截取字符之后部分
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String subStringAfter(String str, String reg) {
		return subString(str, reg, SPLIT_TYPE.AFTER,SPLIT_DIRECTOR.SPLIT_DIRECTOR_FORWARD);
	}
	
	/**
	 * 反向截取字符串，取指定截取字符之前部分
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String subStringLastBefore(String str, String reg) {
		return subString(str, reg, SPLIT_TYPE.BEFORE,SPLIT_DIRECTOR.SPLIT_DIRECTOR_REVERSE);
	}

	/**
	 * 反向截取字符串，取指定截取字符之后部分
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String subStringLastAfter(String str, String reg) {
		return subString(str, reg, SPLIT_TYPE.AFTER,SPLIT_DIRECTOR.SPLIT_DIRECTOR_REVERSE);
	}
}
