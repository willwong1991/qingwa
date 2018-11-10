package com.ls.mamb.common.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 类描述：页面请求参数验证工具类
 * 
 * @author qinwa
 * @version $Revision:1.0 $Date: 2018年8月10日 上午10:31:40 $
 *
 *  History: 2018年8月10日 上午10:31:40 qingwa Created.
 */
public class ValidatorUtils {

	private static Logger logger = LoggerFactory.getLogger(ValidatorUtils.class);

	/** 验证非负正整数*/
	private static final String NUMBER_REG = "^(0|[1-9][0-9]*)$";
	/** 16 位长度*/
	private static final int LENGTH16 = 16;
	/** 验证不超过16位数字，首位非0 */
	private static final String LONG16_REG = "^(0|[1-9][0-9]{0,15})$";
	/** 验证不超过16位数字，首位可以0 */
	private static final String NUMBER16_REG = "^\\d{1,16}$";

	/**
	 * 
	 * 方法说明:验证字符串能不能转换成long型
	 * 
	 * Create Date： 2018年8月9日 下午2:45:12  By qingwa
	 *
	 * @param str
	 * @return
	 *
	 */
	public static boolean isNumber(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		Pattern p = Pattern.compile(NUMBER_REG);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 
	 * 方法说明:校验字符串长度
	 * 
	 * Create Date： 2018年8月9日 下午2:48:27  By qingwa
	 *
	 * @param str
	 * @param maxLen 最大长度
	 * @param minLen 最小长度
	 *
	 */
	public static boolean checkLengthScale(String str, int maxLen, int minLen) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		if (str.length() <= maxLen && str.length() >= minLen) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 方法说明:只校验字符串最大长度
	 * 
	 * Create Date： 2018年8月9日 下午2:50:30  By qingwa
	 *
	 * @param str 需要验证的字符串
	 * @param maxLen 最大长度
	 * @return
	 *
	 */
	public static boolean checkMaxLength(String str, int maxLen) {
		return checkLengthScale(str, maxLen, 0);
	}

	/**
	 * 
	 * 方法说明:校验日期格式
	 * 
	 * Create Date： 2018年8月9日 下午3:36:22  By qingwa
	 *
	 * @param str 需要验证的字符串
	 * @param format 验证的格式
	 * @return
	 *
	 */
	public static boolean checkDateFormat(String str, String format) {
		if (StringUtils.isEmpty(str) || str.length() != format.length()) {
			return false;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.parse(str);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("日期转换出错| " + str + "转换成" + format + "格式出错");
		}
		return false;
	}

	/**
	 * 
	 * 方法说明:验证double类型参数
	 * 
	 * Create Date： 2018年8月14日 上午11:12:29  By qingwa
	 *
	 * @param str
	 * @param beforePoint --小数点前保留几位
	 * @param afterPoint--小数点后保留几位
	 * @return
	 *
	 */
	public static boolean checkDouble(String str, int beforePoint, int afterPoint) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		String regExp = "^\\d{1," + beforePoint + "}(.\\d{0," + afterPoint + "})?$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}


	/**
	 * 
	 * 方法说明:验证主键，长度16位
	 * 
	 * Create Date： 2018年8月10日 下午5:10:49  By qingwa
	 *
	 * @param primaryKey
	 * @return
	 *
	 */
	public static String validatePrimaryKey(String primaryKey) {
		if (StringUtils.isEmpty(primaryKey)) {
			return "ID必须为数字且不能超过16位";
		}
		Pattern p = Pattern.compile(LONG16_REG);
		Matcher m = p.matcher(primaryKey);
		if (!m.matches()) {
			return "ID必须为数字且不能超过16位";
		}
		return null;
	}

	public static void main(String[] args) {
		// Pattern p = Pattern.compile(NUMBER16_REG);
		// Matcher m = p.matcher("12345678901234569");
		// System.out.println(m.matches());
		// String a = "2018-8-300";
		// /System.out.println(checkDateFormat(a, "yyyy-MM-dd"));
		System.out.println(checkDouble("123335", 5, 0));
	}
}
