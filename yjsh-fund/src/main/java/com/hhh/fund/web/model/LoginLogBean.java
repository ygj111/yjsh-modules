package com.hhh.fund.web.model;

import java.io.Serializable;
import java.util.Date;

import com.hhh.fund.usercenter.entity.LoginLog;
import com.hhh.fund.util.StringUtil;


public class LoginLogBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8949998077539947126L;

	private String id;

	private String loginTime;

	private String customerId;
	
	private String ip;

	private String loginName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public void Converter(LoginLog log){
		this.setCustomerId(log.getCustomerId());
		this.setId(log.getId());
		this.setIp(log.getIp());
		this.setLoginName(log.getLoginName());
		this.setLoginTime(StringUtil.dateFormat(log.getLoginTime()));
	}
}
