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

import com.hhh.fund.usercenter.ResourcesType;
import com.hhh.fund.usercenter.State;

@Entity
@Table(name="sys_ucenter_permission")
public class Permission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;

	/**
	 * 用户ID（用户可以直接控制资源，不需要通过角色）
	 * 与角色ID，选其一
	 */
	@Column(length=32)
	private String userId;
	
	/**
	 * 角色ID
	 */
	@Column(length=32)
	private String roleId;
	
	@Column(name="resources_id", length = 32)
	private String resId;
	
	@Enumerated(EnumType.ORDINAL)
	private ResourcesType resType;
	
	/**
	 * 是否有效，State.ENABLE为有效，其他为无效
	 */
	@Enumerated(EnumType.ORDINAL)
	private State enable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public ResourcesType getResType() {
		return resType;
	}

	public void setResType(ResourcesType resType) {
		this.resType = resType;
	}

	public State getEnable() {
		return enable;
	}

	public void setEnable(State enable) {
		this.enable = enable;
	}
}
