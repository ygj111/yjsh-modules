package com.hhh.fund.web.model;

import java.io.Serializable;

import com.hhh.fund.usercenter.Whether;
import com.hhh.fund.usercenter.entity.Department;

public class DepartmentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7071744146835990537L;

	private String id;
	
	/**
	 * 企业ID
	 */
	private String customerId;
	
	/**
	 * 部门名称
	 */
	private String name;
	
	/**
	 * 上级部站ID
	 */
	private String parentId;
	
	/**
	 * 部门路径
	 */
	private String path;
	
	/**
	 * 是否有子节点
	 */
	private boolean child;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isChild() {
		return child;
	}

	public void setChild(Whether w) {
		this.child = w == Whether.Yes? true : false;
	}
	
	public void Converter(Department depart){
		this.setId(depart.getId());
		this.setChild(depart.getChild());
		this.setCustomerId(depart.getCustomerId());
		this.setName(depart.getName());
		this.setParentId(depart.getParentId());
		this.setPath(depart.getPath());
	}
}
