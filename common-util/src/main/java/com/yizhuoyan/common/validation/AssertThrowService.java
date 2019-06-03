package com.yizhuoyan.common.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据验证工具类
 *
 * @author root@yizhuoyan.com
 */

public interface AssertThrowService {

	void throwException(String message, Object... args);

	/**
	 * 
	 * 
	 * @param label
	 * @param s
	 * @return
	 */
	public default String $(String label, String s, Object... args) {
		return assertNotBlank(label + "不能为空", s, args);
	}

	default public void assertNull(String message, Object obj, Object... args) {
		if (obj != null)
			throwException(message, args);
	}

	default public void assertNotNull(String message, Object obj, Object... args) {
		if (obj == null)
			throwException(message, args);
	}

	/**
	 * 断言字符串是null或者空字符串或空白字符串
	 *
	 * @param message
	 * @param s
	 * @param args
	 */
	default public void assertBlank(String message, String s, Object... args) {
		if (s != null && s.trim().length() != 0) {
			throwException(message, args);
		}
	}

	default public String assertNotBlank(String message, String s, Object... args) {
		if (s == null || (s = s.trim()).length() == 0) {
			throwException(message, args);
		}
		return s;
	}

	default public int assertInteger(String message, String intStr, Object... args) {
		try {
			return Integer.parseInt(intStr);
		} catch (Exception e) {
			throwException(message+"必须是整数", args);
		}
		return 0;
	}

	default public float assertFloat(String message, String intStr, Object... args) {
		try {
			return Float.parseFloat(intStr);
		} catch (Exception e) {
			throwException(message+"必须是小数", args);
		}
		return 0;
	}
	default public double assertDouble(String message, String intStr, Object... args) {
		try {
			return Float.parseFloat(intStr);
		} catch (Exception e) {
			throwException(message+"必须是小数", args);
		}
		return 0;
	}

	default public void assertNotEquals(String message, Object a, Object b, Object... args) {
		if (a == null ? null == b : a.equals(b)) {
			throwException(message, args);
		}
	}

	default public void assertEquals(String message, Object a, Object b, Object... args) {
		if (a == null ? null != b : !a.equals(b)) {
			throwException(message, args);
		}
	}

	default public void assertEmpty(String message, Collection b, Object... args) {
		if (b == null || b.size() != 0) {
			throwException(message, args);
		}
	}

	default public void assertNotEmpty(String message, Collection b, Object... args) {
		if (b != null && b.size() == 0) {
			throwException(message, args);
		}
	}

	default public void assertTrue(String message, boolean b, Object... args) {
		if (!b) {
			throwException(message, args);
		}
	}

	default public void assertFalse(String message, boolean b, Object... args) {
		if (b) {
			throwException(message, args);
		}
	}

	default public void assertLessThan(String label, int len, int target, Object... args) {
		if (len >= target) {
			throwException(label + "必须小于" + target, args);
		}
	}
	

	default public void assertGreatThan(String label, int n, int target, Object... args) {
		if (n <= target) {
			throwException(label + "必须大于" + target, args);
		}
	}
	default public void assertGreatThan(String label, long n, int target, Object... args) {
		if (n <= target) {
			throwException(label + "必须大于" + target, args);
		}
	}
	default public void assertGreatThan(String label, double n, int target, Object... args) {
		if (n <= target) {
			throwException(label + "必须大于" + target, args);
		}
	}
	default public void assertGreatThan(String label, float n, int target, Object... args) {
		if (n <= target) {
			throwException(label + "必须大于" + target, args);
		}
	}

	default public void assertLessThan(String label, String s, int target, Object... args) {
		int len = s == null ? 0 : s.length();
		if (len >= target) {
			throwException(label + "长度不可超过" + target, args);
		}
	}

	default public void assertGreatThan(String label, String s, int target, Object... args) {
		int len = s == null ? 0 : s.length();
		if (len <= target) {
			throwException(label + "长度不可小于" + target, args);
		}
	}

	default public void assertIn(String label, Object target, Object[] chioces, Object... args) {
		boolean contains = false;
		for (Object choice : chioces) {
			if (Objects.equals(target, choice)) {
				contains = true;
				break;
			}
		}
		if (!contains) {
			throwException(label + "的值必须从" + Arrays.toString(chioces) + "中选取", args);
		}
	}

	Pattern PATTERN_EMAIL = Pattern.compile("\\w+@[\\w.]+");

	public default String assertEmail(String label, String target) {
		Matcher matcher = PATTERN_EMAIL.matcher(target);
		if (!matcher.matches()) {
			throwException(label + "不是合格的邮箱形式");
		}
		return target;
	}

	public default LocalDate assertDate(String label, String target, String pattern) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
			return dtf.parse(target, LocalDate::from);
		} catch (Exception e) {
			throwException(label + "不是合格的日期格式(" + pattern + ")");
		}
		return null;
	}

	public default void assertAllWordCharacter(String label, String target) {
		char c = 0;
		for (int i = target.length(); i-- > 0;) {
			c = target.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '-') {
			} else {
				throwException(label + "仅能是字母数字或下划线");
			}
		}

	}

}
