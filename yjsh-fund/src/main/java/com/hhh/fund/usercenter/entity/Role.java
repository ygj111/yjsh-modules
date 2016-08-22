package com.hhh.fund.usercenter.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 角色
 * @author 3hhjj
 *
 */
@Entity
@Table(name="sys_ucenter_role")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1294085004338551067L;
	
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
	 * 角色名称
	 */
	private String name;
	
	/**
	 * 角色描述
	 */
	private String desp;
	
	/**
	 * 角色所属的用户
	 */
	@ManyToMany(mappedBy="roles", fetch=FetchType.LAZY)
	private Set<Account> users;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public Set<Account> getUsers() {
		return users;
	}

	public void setUsers(Set<Account> users) {
		this.users = users;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
