package com.cms.scaffold.common.util;

import java.math.BigDecimal;


/**
 * 工具类-BigDecimal算法
 *
 * @author zyz
 * @version 2.0
 * @since 2014年1月28日
 */
public class BigDecimalUtil {

	private final static int DEF_DIV_SCALE = 10; // 默认精确的小数位

	/**
	 * 提供精确的加法运算。
	 * @param params 参数数组
	 * @return 和
	 */
	public static double add(double... params) {
		BigDecimal b1 = new BigDecimal(0);
		for (double param : params) {
			BigDecimal b2 = new BigDecimal(Double.toString(param));
			b1 = b1.add(b2);
		}
		return b1.doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 *
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 *
	 * @param params 参数数组
	 * @return 动态参数的积
	 */
	public static double mul(double... params) {
		BigDecimal b1 = new BigDecimal(1);
		for (double param : params) {
			BigDecimal b2 = new BigDecimal(Double.toString(param));
			b1 = b1.multiply(b2);
		}
		return b1.doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 *
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		if(v1 == 0|| v2 == 0){
			return 0.00;
		}else{
			return div(v1, v2, DEF_DIV_SCALE);
		}
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 *
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		//被除数必须大于0
		if (v2 == 0) {
			throw new IllegalArgumentException("The dividend must be greater than zero");
		}

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 *
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位(2位)四舍五入处理。
	 *
	 * @param v 需要四舍五入的数字
	 * @return 四舍五入后的结果
	 */
	public static double round(double v) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 *
	 * @param v 需要四舍五入的数字字符串
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(String v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位(2位)四舍五入处理。
	 *
	 * @param v 需要四舍五入的数字字符串
	 * @return 四舍五入后的结果
	 */
	public static double round(String v) {
		if (StringUtil.isBlank(v)) {
			return 0;
		}
		BigDecimal b = new BigDecimal(v);
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位处理，去掉保留位数后的数字
	 *
	 * @param v 需要处理的数字
	 * @param scale 小数点后保留几位
	 * @return 去掉保留位数后的结果
	 */
	public static double decimal(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 校验非负数
	 * @param amount 数额
	 * @return 非负数（true),反之
	 */
	public static  boolean validAmount(Double amount){
	      if(amount==null){
	    	  return false;
	      }
	      BigDecimal b1 = new BigDecimal(amount);
	      if(b1.compareTo(BigDecimal.ZERO) >0){
	    	  return true;
	      }

		return false;
	}

}
