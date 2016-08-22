package com.hhh.fund.web.model;

import java.io.Serializable;
import java.util.Set;

import com.hhh.fund.usercenter.entity.ResGroup;
import com.hhh.fund.usercenter.entity.ResGroupJoin;


public class ResGroupBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7342591778576205002L;
	
	private String id;
	
	private String customerId;
	
	/**
	 * 资源组描述
	 */
	private String desp;

	/**
	 * 资源ID列表
	 */
	private String[] resIds; 
	
	private Set<ResGroupJoin> rGroupJoin;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public String[] getResIds() {
		return resIds;
	}

	public void setResIds(String[] resIds) {
		this.resIds = resIds;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Set<ResGroupJoin> getrGroupJoin() {
		return rGroupJoin;
	}

	public void setrGroupJoin(Set<ResGroupJoin> rGroupJoin) {
		this.rGroupJoin = rGroupJoin;
	}
	public void Converter(ResGroup resg){
		this.setCustomerId(resg.getCustomerId());
		this.setDesp(resg.getDesp());
		this.setId(resg.getId());
		this.setrGroupJoin(resg.getrGroupJoin());
	}
}
