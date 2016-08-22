package com.hhh.fund.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.util.StringUtils;

public final class StringUtil {

	public final static String CUSTOMER_ID = "customerId";

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String dateFormat(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 字符串转日期对象
	 * 
	 * @param str
	 *            字符串日期格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parstDate(String str) {
		if (str == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return formatter.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCustomerId(HttpSession session) {
		Object obj = session.getAttribute(StringUtil.CUSTOMER_ID);
		if (obj == null)
			return null;
		else {
			return obj.toString();
		}
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
