package com.hhh.security.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.hhh.fund.web.model.UserBean;
import com.hhh.security.ShiroPrincipal;

public class ShiroUtils {
	/**
	 * 返回当前登录的认证实体ID
	 * @return
	 */
	public static String getUserId() {
		ShiroPrincipal principal = getPrincipal();
		if(principal != null) return principal.getId();
		return "";
	}
	
	public static UserBean getUser() {
		ShiroPrincipal principal = getPrincipal();
		if(principal != null) return principal.getUser();
		return null;
	}
	
	
	/**
	 * 获取当前登录的认证实体
	 * @return
	 */
	public static ShiroPrincipal getPrincipal() {
		Subject subject = SecurityUtils.getSubject();
		return (ShiroPrincipal)subject.getPrincipal();
	}
	
	/**
	 * 获取当前认证实体的中文名称
	 * @return
	 */
	public static String getFullname() {
		ShiroPrincipal principal = getPrincipal();
		if(principal != null) return principal.getUser().getDisplayName();
		return "";
	}
	
	/**
	 * 获取当前认证实体的登录名称
	 * @return
	 */
	public static String getUsername() {
		ShiroPrincipal principal = getPrincipal();
		if(principal != null) return principal.getUsername();
		throw new RuntimeException("user's name is null.");
	}
	
	public static List<String> getRoles(){
		ShiroPrincipal principal = getPrincipal();
		List<String> list = new ArrayList<>();
		if(principal != null)
			list.addAll(principal.getRoles());
		return list;
	}
}
