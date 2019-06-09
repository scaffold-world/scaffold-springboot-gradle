package com.jiaheng.scaffold.common.util;

import java.util.UUID;

/**
 * JAVA版本的自动生成有规则的订单号(或编号) 生成的格式是: 200908010001 前面几位为当前的日期,后面五位为系统自增长类型的编号 原理:
 * 1.获取当前日期格式化值; 2.读取文件,上次编号的值+1最为当前此次编号的值 (新的一天会重新从1开始编号)
 */

public class OrderNoUtil {

	static OrderNoUtil orderNoUtil = new OrderNoUtil();
	//平台id 
	static String WorkerId = "1";

	public static OrderNoUtil getInstance() {
		if (orderNoUtil == null) {
			orderNoUtil = new OrderNoUtil();
			return orderNoUtil;
		} else {
			return orderNoUtil;
		}
	}

	/**
	 * 产生唯一 的序列号。
	 * 增加一位区分平台id，防止多个java虚拟机生成相同的id
	 * 
	 * @return
	 */
	public static String getSerialNumber() {
		int hashCode = UUID.randomUUID().toString().hashCode();
		if (hashCode < 0) {
			hashCode = -hashCode;
		}
		return DateUtil.dateStr3(DateUtil.getNow()).substring(2, 10) + WorkerId + String.format("%010d", hashCode);
	}
	/**
	 * 根据商户id产生唯一 的序列号。
	 * 增加一位区分平台id，防止多个java虚拟机生成相同的id
	 *
	 * @return
	 */
	public static String getPartnerIdSerialNumber(Long partnerId) {
		int hashCode = UUID.randomUUID().toString().hashCode();
		if (hashCode < 0) {
			hashCode = -hashCode;
		}
		return partnerId+ DateUtil.dateStr3(DateUtil.getNow()).substring(4, 10) + WorkerId + String.format("%010d", hashCode);
	}

	/**
	 * 产生唯一 的订单号。
	 *
	 * @return
	 */
	public static String getOrderNo(Long partnerId) {
		int hashCode = UUID.randomUUID().toString().hashCode();
		if (hashCode < 0) {
			hashCode = -hashCode;
		}
		return partnerId + "ODR" + DateUtil.dateStr3(DateUtil.getNow()).substring(2, 10) + String.format("%010d", hashCode);
	}
	

}
