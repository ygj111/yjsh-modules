package com.hhh.fund.web.model;


import com.hhh.fund.usercenter.entity.Company;

public class CompanyBean {
	
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
	
	private byte[] icon;
	
	//企业id
	private String customerId;
	//单位类型
	private String type;
	//类型备注
	private String typeDesc;
	//一级行政区域
	private String firstRegion;
	//二级行政区域
	private String secondRegion;
	//三级行政区域
	private String thirdRegion;
	//单位电话
	private String phone;
	//单位负责人
	private String header;
	//单位负责人手机
	private String headerPhone;
	//单位负责人邮箱
	private String headerEmail;
	//电子委托模式
	private String delegationModel;
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
	public void convert(Company company){
		this.setId(company.getId());
		this.setAddress(company.getAddress());
		this.setCode(company.getCode());
		this.setDelegationModel(company.getDelegationModel());
		this.setFirstRegion(company.getFirstRegion());
		this.setHeader(company.getHeader());
		this.setHeaderEmail(company.getHeaderEmail());
		this.setHeaderPhone(company.getHeaderPhone());
		this.setIcon(company.getIcon());
		this.setName(company.getName());
		this.setPhone(company.getPhone());
		this.setSecondRegion(company.getSecondRegion());
		this.setThirdRegion(company.getThirdRegion());
		this.setType(company.getType());
		this.setTypeDesc(company.getTypeDesc());
		this.setCustomerId(company.getCustomerId());
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
