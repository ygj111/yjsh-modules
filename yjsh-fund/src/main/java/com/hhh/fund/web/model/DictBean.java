package com.hhh.fund.web.model;

import com.hhh.fund.usercenter.entity.Dictionary;

public class DictBean {
	private Integer id;
	private String customerId;
	private String code;
	private String name;
	private int status;
	private String parent;
	private String category;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void Converter(Dictionary bean){
		if(bean == null)
			return;
		this.setId(bean.getId());
		this.setCode(bean.getCode());
		this.setName(bean.getName());
		this.setParent(bean.getParent());
		this.setStatus(bean.getState());
		this.setCategory(bean.getCategory());
		this.setCustomerId(bean.getCustomerId());
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}