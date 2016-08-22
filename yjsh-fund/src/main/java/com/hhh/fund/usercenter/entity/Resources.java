package com.hhh.fund.usercenter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hhh.fund.usercenter.State;

/**
 * 资源表，用于列出需要进行权限控的资源
 * @author 3hhjj
 *
 */

@Entity
@Table(name="sys_ucenter_resources")
public class Resources implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1502337402662910405L;
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
	
	/**
	 * 资源编号
	 */
	private String code;
	
	/**
	 * 资源描述
	 */
	private String name;
	
	/**
	 * 资源对象
	 */
	private String resobj;
	
	
	private String permission;

	@Enumerated(EnumType.ORDINAL)
	private State enable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResobj() {
		return resobj;
	}

	public void setResobj(String resobj) {
		this.resobj = resobj;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public State getEnable() {
		return enable;
	}

	public void setEnable(State enable) {
		this.enable = enable;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
