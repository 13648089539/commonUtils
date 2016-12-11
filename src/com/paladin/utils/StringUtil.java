package com.paladin.utils;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author palad �ַ���������
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
	 * IP��ַ��Ч��У��
	 * 
	 * @param ip
	 * @param type
	 *            ��IPv4�Լ�IPV6������
	 * @return
	 */
	public static boolean checkIp(String ip, IP_TYPE type) {
		Pattern pattern = Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){" + (type == IP_TYPE.IPV4 ? 3 : 5) + "}" + "(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$");
		return pattern.matcher(ip).matches();
	}

	/**
	 * �������һ���ַ����ַ���Ϊ 0~9, a~z, A~Z
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
	 * �ָ��ַ���
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
	 * �ַ�����ȡ
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
	 * ��ȡ�ַ�����ȡָ����ȡ�ַ�֮ǰ����
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String subStringBefore(String str, String reg) {
		return subString(str, reg, SPLIT_TYPE.BEFORE,SPLIT_DIRECTOR.SPLIT_DIRECTOR_FORWARD);
	}

	/**
	 * ��ȡ�ַ�����ȡָ����ȡ�ַ�֮�󲿷�
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String subStringAfter(String str, String reg) {
		return subString(str, reg, SPLIT_TYPE.AFTER,SPLIT_DIRECTOR.SPLIT_DIRECTOR_FORWARD);
	}
	
	/**
	 * �����ȡ�ַ�����ȡָ����ȡ�ַ�֮ǰ����
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String subStringLastBefore(String str, String reg) {
		return subString(str, reg, SPLIT_TYPE.BEFORE,SPLIT_DIRECTOR.SPLIT_DIRECTOR_REVERSE);
	}

	/**
	 * �����ȡ�ַ�����ȡָ����ȡ�ַ�֮�󲿷�
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String subStringLastAfter(String str, String reg) {
		return subString(str, reg, SPLIT_TYPE.AFTER,SPLIT_DIRECTOR.SPLIT_DIRECTOR_REVERSE);
	}
}
