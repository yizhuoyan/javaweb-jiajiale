package com.yizhuoyan.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public interface StringUtil {
	/**
	 * 前缀填充
	 * 
	 * @param value      需填充值
	 * @param fillLength 最后长度
	 * @param fillchar   填充字符
	 * @return
	 */
	public default String prefixFill(Object value, int fillLength, char fillchar) {
		String orignString = String.valueOf(value);
		if (orignString.length() >= fillLength) {
			return orignString.substring(0, fillLength);
		}
		char[] resultChars = new char[fillLength];
		for (int i = resultChars.length; i-- > 0; resultChars[i] = fillchar)
			;
		for (int i = orignString.length(); i-- > 0; resultChars[i] = orignString.charAt(i))
			;
		return new String(resultChars);
	}

	public default boolean isBlank(String s) {
		return s == null || s.trim().length() == 0;
	}

	public default String uuid() {
		String uuid = UUID.randomUUID().toString();
		char[] cs = new char[32];
		char c;
		for (int i = uuid.length(), j = 0; i-- > 0;) {
			if ((c = uuid.charAt(i)) != '-') {
				cs[j++] = c;
			}
		}
		return new String(cs);
	}
	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString();
		char[] cs = new char[32];
		char c;
		for (int i = uuid.length(), j = 0; i-- > 0;) {
			if ((c = uuid.charAt(i)) != '-') {
				cs[j++] = c;
			}
		}
		System.out.println(new String(cs));
	}

	/**
	 * 清理字符串前后空格,如果字符串为空白字符串，则返回null
	 * 
	 * @param s
	 * @return
	 */
	public default String trim(String s) {
		if (s != null) {
			if ((s = s.trim()).length() == 0)
				return null;
			else
				return s;
		}
		return null;
	}

	/**
	 * 解析字符串为整数
	 * 
	 * @param s
	 * @param defaultInt
	 * @return
	 */
	public default int parseInt(String s, int defaultInt) {
		try {
			if (s == null)
				return defaultInt;
			return Integer.parseInt(s);
		} catch (Exception e) {
			return defaultInt;
		}
	}

	public default int parseInt(String s) {
		return Integer.parseInt(s);
	}

	public default String escapeForSqlLike(String s) {
		StringBuilder sb = new StringBuilder();
		char c = 0;
		for (int i = 0; i < s.length(); i++) {
			switch ((c = s.charAt(i))) {
			case '%':
				sb.append("\\%");
				break;
			case '_':
				sb.append("\\_");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 解析字符串为日期对象，如果失败，则抛出异常
	 * 
	 * @param s       要解析的字符创
	 * @param pattern 要使用的模式(yyyy-mm-dd)
	 * @return
	 */
	default LocalDate parseLocalDate(String s, String pattern) {
		if ((s = trim(s)) == null) {
			return null;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		return dtf.parse(s, LocalDate::from);
	}

	default LocalDate parseLocalDate(String s, String pattern, LocalDate defaultDate) {
		if ((s = trim(s)) == null) {
			return null;
		}
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
			return dtf.parse(s, LocalDate::from);
		} catch (Exception e) {
			return defaultDate;
		}
	}
}
