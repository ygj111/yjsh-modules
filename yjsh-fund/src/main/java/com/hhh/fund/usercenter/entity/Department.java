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

import com.hhh.fund.usercenter.Whether;
/**
 * 公司部门
 * @author 3hhjj
 *
 */
@Entity
@Table(name="sys_ucenter_department")
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2793953442320696621L;
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;
	
	/**
	 * 企业ID
	 */
	@Column(length=32)
	private String customerId;
	
	/**
	 * 部门名称
	 */
	private String name;
	
	/**
	 * 上级部站ID
	 */
	@Column(length=32)
	private String parentId;
	
	/**
	 * 部门路径
	 */
	private String path;
	
	/**
	 * 是否有子节点
	 */
	@Enumerated(EnumType.ORDINAL)
	private Whether child;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String orgId) {
		this.customerId = orgId;
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

	public Whether getChild() {
		return child;
	}

	public void setChild(Whether child) {
		this.child = child;
	}

}
