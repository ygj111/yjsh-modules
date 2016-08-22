package com.hhh.fund.usercenter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 部门所属用户
 * @author 3hhjj
 *
 */
@Entity
@Table(name="sys_ucenter_user_department")
public class UserOfDepartment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1441486708405579911L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(length=32)
	private String accountId;
	
	@Column(length=32)
	private String departId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}
	
}
