package com.hhh.fund.web.model;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.hhh.fund.usercenter.Gender;
import com.hhh.fund.usercenter.State;
import com.hhh.fund.usercenter.Whether;
import com.hhh.fund.usercenter.entity.Account;
import com.hhh.fund.usercenter.entity.Company;
import com.hhh.fund.util.StringUtil;

/**
 * 用户对象
 * @author mars.zhong
 *
 */
public class UserBean {
	private String userId;
	private String username;
	private String password;
	private String keyId;
	private String salt;
	private String email;
	private String phone;
	private String displayName;
	private String position;
	private String customerId;
	private String createtime;
	private String address;
	private String birthday;
	private Gender gender;
	private boolean isLocked;
	private boolean isAdmin;
	private boolean rememberMe;
	
	private CompanyBean company;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public boolean isLocked() {
		return isLocked;
	}
	public void setLocked(State isLocked) {
		this.isLocked = isLocked.equals(State.Locked) ? true : false;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public boolean getIsAdmin(){
		return isAdmin;
	}
	public void setAdmin(Whether isAdmin) {
		if(isAdmin.ordinal() == Whether.Yes.ordinal())
			this.isAdmin =true;
		else
			this.isAdmin = false;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public void Converter(Account account, boolean filter){
		this.setUserId(account.getId());
		this.setUsername(account.getLoginName());
		if(!filter){
			this.setPassword(account.getPassword());
			this.setSalt(account.getSalt());
			this.setKeyId(account.getKeyId());
		}
		this.setDisplayName(account.getName());
		this.setEmail(account.getEmail());
		this.setPhone(account.getPhone());
		
		this.setLocked(account.getState());
		this.setAdmin(account.getIsAdmin());
		this.setPosition(account.getUinfo().getPosition());
		this.setAddress(account.getUinfo().getAddress());
		this.setBirthday(StringUtil.dateFormat(account.getUinfo().getBirthday()));
		this.setGender(account.getUinfo().getGender());
		this.setCreatetime(StringUtil.dateFormat(account.getCreatetime()));
		if(account.getCompany() != null){
			CompanyBean companyBean = new CompanyBean();
			BeanUtils.copyProperties(account.getCompany(), companyBean);
			this.setCompany(companyBean);
		}
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public CompanyBean getCompany() {
		return company;
	}
	public void setCompany(CompanyBean company) {
		this.company = company;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
}
