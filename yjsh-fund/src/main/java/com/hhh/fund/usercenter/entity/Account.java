package com.hhh.fund.usercenter.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.hhh.fund.usercenter.State;
import com.hhh.fund.usercenter.Whether;

/**
 * 账户表
 * @author 3hhjj
 *
 */
@Entity
@Table(name="sys_ucenter_account")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4544194419364523103L;
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;
	
	/**
	 * 登录帐户
	 */
	@Column(length=50)
	private String loginName;
	
	/**
	 * usbKey 登录
	 */
	@Column(length=20)
	private String keyId;
	
	/**
	 * 密码
	 */
	@Column
	private String password;
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	
	
	private String salt;

	/**
	 * 显示的名称
	 */
	private String name;
	
	/**
	 * 邮箱（可以用于验证和找回密码等功能）
	 */
	private String email;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 是否是管理员
	 */
	@Enumerated(EnumType.ORDINAL)
	private Whether isAdmin;
	
	/**
	 * 是否有效
	 */
	@Enumerated(EnumType.ORDINAL)
	private State state;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="userinfo_id")
	private UserInfo uinfo;
	
	@ManyToOne(optional = true,cascade=CascadeType.ALL)
    @JoinColumn(name="company_id")
	private Company company;
	
	 @ManyToMany
	 @JoinTable(name="sys_ucenter_userrole",joinColumns=@JoinColumn(name="accountid"),
	                    inverseJoinColumns=@JoinColumn(name="roleid"))
	private Set<Role> roles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public UserInfo getUinfo() {
		return uinfo;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setUinfo(UserInfo uinfo) {
		this.uinfo = uinfo;
	}

	public Whether getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Whether isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
}
