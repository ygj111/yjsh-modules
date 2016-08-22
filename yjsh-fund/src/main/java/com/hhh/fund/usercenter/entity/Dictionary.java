package com.hhh.fund.usercenter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="sys_ucenter_dict")
public class Dictionary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5496497900756856057L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	
	
	private String code;
	
	/**
	 * 企业ID
	 */
	@Column(length=32)
	private String customerId;

	/**
	 * 字典分类
	 */
	private String category;

	
	private String name;

	/**
	 * 
	 */
	private String parent;

	private int state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
