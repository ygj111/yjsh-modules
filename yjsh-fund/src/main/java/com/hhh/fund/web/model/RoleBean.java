package com.hhh.fund.web.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.hhh.fund.usercenter.entity.Account;
import com.hhh.fund.usercenter.entity.Role;

public class RoleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3400245181482213817L;

	private String id;

	/**
	 * 企业ID
	 */
	private String customerId;
	
	/**
	 * 角色名称
	 */
	private String name;
	
	/**
	 * 角色描述
	 */
	private String desp;

	private String[] userIds;
	
	private Set<UserBean> users;
	
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

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public Set<UserBean> getUsers() {
		return users;
	}

	public void setUsers(Set<UserBean> users) {
		this.users = users;
	}
	
	public void Converter(Role role, boolean lazy){
		this.setId(role.getId());
		this.setCustomerId(role.getCustomerId());
		this.setDesp(role.getDesp());
		this.setName(role.getName());
		if(lazy){
			if(role.getUsers() != null && !role.getUsers().isEmpty()){
				Set<UserBean> beans = new HashSet<>();
				for(Account a : role.getUsers()){
					UserBean bean = new UserBean();
					bean.setUserId(a.getId());
					bean.setUsername(a.getLoginName());
					bean.setDisplayName(a.getName());
					beans.add(bean);
				}
				this.setUsers(beans);
			}
		}
	}
}
