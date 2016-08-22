package com.hhh.fund.web.model;

import java.io.Serializable;

import com.hhh.fund.usercenter.ResourcesType;
import com.hhh.fund.usercenter.State;
import com.hhh.fund.usercenter.entity.Permission;

public class PermissionBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9199115450618707051L;

	private String id;

	/**
	 * 用户ID（用户可以直接控制资源，不需要通过角色）
	 * 与角色ID，选其一
	 */
	private String userId;
	
	/**
	 * 角色ID
	 */
	private String roleId;
	
	private String resId;
	
	private ResourcesType resType;
	
	/**
	 * 是否有效，State.ENABLE为有效，其他为无效
	 */
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
	public void Converter(Permission p){
		this.setEnable(p.getEnable());
		this.setId(p.getId());
		this.setResId(p.getResId());
		this.setResType(p.getResType());
		this.setRoleId(p.getRoleId());
		this.setUserId(p.getUserId());
	}

}
