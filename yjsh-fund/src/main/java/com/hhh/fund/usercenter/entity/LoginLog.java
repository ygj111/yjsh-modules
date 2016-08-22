package com.hhh.fund.usercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户登录日志
 * @author 3hhjj
 *
 */
@Entity
@Table(name="sys_ucenter_loginlog")
public class LoginLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7652611211922813780L;

	/**
	 * id 主键
	 */
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;
	
	/**
	 * 公司编号
	 */
	@Column(length=32)
	private String customerId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTime;
	
	@Column(length=50)
	private String ip;
	
	@Column(length=50)
	private String loginName;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
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
}
