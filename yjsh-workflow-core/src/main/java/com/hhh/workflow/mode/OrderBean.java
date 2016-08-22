package com.hhh.workflow.mode;

import java.io.Serializable;

/**
 * 流程实例
 * @author 3hhjj
 *
 */
public class OrderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8193175802371493222L;

	private String id;
	/**
	 * 版本
	 */
	private Integer version = 0;
	/**
	 * 流程定义ID
	 */
	private Process process;
//	@Column(name="process_Id", length=32)
//    private String processId;
    /**
     * 流程实例创建者ID
     */
    private String creator;
    /**
     * 流程实例创建时间
     */
    private String createTime;
    /**
     * 流程实例为子流程时，该字段标识父流程实例ID
     */
    private String parentId;
    /**
     * 流程实例为子流程时，该字段标识父流程哪个节点模型启动的子流程
     */
    private String parentNodeName;
    /**
     * 流程实例期望完成时间
     */
    private String expireTime;
    /**
     * 流程实例上一次更新时间
     */
    private String lastUpdateTime;
    /**
     * 流程实例上一次更新人员ID
     */
    private String lastUpdator;
    /**
     * 流程实例优先级
     */
    private Integer priority;
    /**
     * 流程实例编号
     */
    private String orderNo;
	/**
     * 流程实例附属变量
     */
    private String variable;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentNodeName() {
		return parentNodeName;
	}
	public void setParentNodeName(String parentNodeName) {
		this.parentNodeName = parentNodeName;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getLastUpdator() {
		return lastUpdator;
	}
	public void setLastUpdator(String lastUpdator) {
		this.lastUpdator = lastUpdator;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
}
