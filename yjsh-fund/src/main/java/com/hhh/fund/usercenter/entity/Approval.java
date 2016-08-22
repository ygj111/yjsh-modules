package com.hhh.fund.usercenter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_ucenter_approval")
public class Approval implements Serializable{

	/**
	 * 流程公用审批表
	 */
	private static final long serialVersionUID = -2549967324319315608L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;

	//流程实例ID
	@Column(length=32)
	protected String orderId;
	//任务ID
	@Column(length=32)
	protected String taskId;
	
	/**
	 * 操作人
	 */
	@Column(length=50)
	private String operator;
	
	/**
	 * 操作时间
	 */
	@Column(length=50)
	private String operateTime;
	
	@Column(length=50)
    private String result;
	
    private String content;
    
    private String taskName;
	
    private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
