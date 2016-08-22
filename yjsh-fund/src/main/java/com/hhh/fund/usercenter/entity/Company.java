package com.hhh.fund.usercenter.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sys_ucenter_company")
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6339697122944621839L;
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;
	
	/**
	 * 公司名
	 */
	private String name;

	/**
	 * 组织机构代码
	 */
	private String code;
	
	/**
	 * 地址
	 */
	private String address;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] icon;
	
	//企业id
	private String customerId;
	//单位类型
	private String type;
	//类型备注
	@Column(name="type_desc")
	private String typeDesc;
	//一级行政区域
	@Column(name="first_region")
	private String firstRegion;
	//二级行政区域
	@Column(name="second_region")
	private String secondRegion;
	//三级行政区域
	@Column(name="third_region")
	private String thirdRegion;
	//单位电话
	private String phone;
	//单位负责人
	private String header;
	//单位负责人手机
	@Column(name="header_phone")
	private String headerPhone;
	//单位负责人邮箱
	@Column(name="header_email")
	private String headerEmail;
	//电子委托模式
	@Column(name="delegation_model")
	private String delegationModel;
	
	@OneToMany(mappedBy="company")
	private Set<Account> Account;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getFirstRegion() {
		return firstRegion;
	}

	public void setFirstRegion(String firstRegion) {
		this.firstRegion = firstRegion;
	}

	public String getSecondRegion() {
		return secondRegion;
	}

	public void setSecondRegion(String secondRegion) {
		this.secondRegion = secondRegion;
	}

	public String getThirdRegion() {
		return thirdRegion;
	}

	public void setThirdRegion(String thirdRegion) {
		this.thirdRegion = thirdRegion;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getHeaderPhone() {
		return headerPhone;
	}

	public void setHeaderPhone(String headerPhone) {
		this.headerPhone = headerPhone;
	}

	public String getHeaderEmail() {
		return headerEmail;
	}

	public void setHeaderEmail(String headerEmail) {
		this.headerEmail = headerEmail;
	}
	public String getDelegationModel() {
		return delegationModel;
	}

	public void setDelegationModel(String delegationModel) {
		this.delegationModel = delegationModel;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Set<Account> getAccount() {
		return Account;
	}

	public void setAccount(Set<Account> account) {
		Account = account;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
