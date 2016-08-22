package com.hhh.fund.web.model;

import java.io.Serializable;

import com.hhh.fund.usercenter.State;
import com.hhh.fund.usercenter.entity.Resources;

public class ResourcesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8704765290668523002L;

	private String id;

	private String customerId;
	
	private String code;
	
	private String name;

	private String resobj;

	private String permission;

	private boolean enable;

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

	public boolean getEnable() {
		return enable;
	}

	public void setEnable(State enable) {
		this.enable = enable == State.Enable ? true : false;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void Converter(Resources bean){
		this.setCode(bean.getCode());
		this.setCustomerId(bean.getCustomerId());
		this.setId(bean.getId());
		this.setEnable(bean.getEnable());
		this.setName(bean.getName());
		this.setPermission(bean.getPermission());
		this.setResobj(bean.getResobj());
	}
}
