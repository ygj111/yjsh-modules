package com.hhh.fund.usercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.hhh.fund.usercenter.Gender;

/**
 * 用户详细信息
 * @author 3hhjj
 *
 */
@Entity
@Table(name="sys_ucenter_userInfo")
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5710757322013564424L;

	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;
	
	/**
	 * 用户头像
	 */
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] icon;
	
	/**
	 * 职位
	 */
	private String position;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 性别
	 */
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
