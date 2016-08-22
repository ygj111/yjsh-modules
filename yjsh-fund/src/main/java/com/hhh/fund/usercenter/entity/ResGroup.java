package com.hhh.fund.usercenter.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 资源组，将相关资源分组，方便角色选择资源组，
 * @author 3hhjj
 *
 */

@Entity
@Table(name="sys_ucenter_resgroup")
public class ResGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1988649271806182321L;
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;

	/**
	 * 公司编号
	 */
	@Column(length=32)
	private String customerId;
	
	/**
	 * 资源组描述
	 */
	private String desp;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
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

	public Set<ResGroupJoin> getrGroupJoin() {
		return rGroupJoin;
	}

	public void setrGroupJoin(Set<ResGroupJoin> rGroupJoin) {
		this.rGroupJoin = rGroupJoin;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
