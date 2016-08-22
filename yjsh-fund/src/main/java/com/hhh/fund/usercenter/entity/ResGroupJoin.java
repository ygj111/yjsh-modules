package com.hhh.fund.usercenter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 资源组与资源的关联关系
 * @author 3hhjj
 *
 */
@Entity
@Table(name="sys_uconter_resgroup_join")
public class ResGroupJoin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8557150557195217160L;
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;
	
	/**
	 * 资源组ID
	 */
	@Column(length=32)
	private String groupId;
	
	/**
	 * 资源ID
	 */
	private String resID;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getResID() {
		return resID;
	}

	public void setResID(String resID) {
		this.resID = resID;
	}

}
