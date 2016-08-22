package com.hhh.workflow.mode;

import java.io.Serializable;
import java.util.Set;

/**
 * 工作流引擎任务
 * @author 3hhjj
 *
 */
public class TaskBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1656376168063838367L;

	private String id;
    /**
     * 流程实例ID
     */
    private String orderId;
    /**
     * 任务名称
     */
	private String taskName;
	/**
	 * 任务显示名称
	 */
	private String displayName;
	/**
	 * 参与方式（0：普通任务；1：参与者fork任务[即：如果10个参与者，需要每个人都要完成，才继续流转]）
	 */
	private Integer performType;
	/**
	 * 任务类型
	 */
    private Integer taskType;
    /**
     * 任务状态（0：结束；1：活动）
     */
    private Integer taskState;
    /**
     * 任务处理者ID
     */
    private String operator;
    /**
     * 任务创建时间
     */
    private String createTime;
    /**
     * 任务完成时间
     */
    private String finishTime;
    /**
     * 期望任务完成时间
     */
    private String expireTime;
    /**
     * 任务关联的表单url
     */
    private String actionUrl;
    
    /**
     * 任务参与者列表
     */
	private Set<TaskActorBean>histActors;

	/**
	 * 任务参与者列表
	 */
    private String[] actorIds;
    /**
     * 父任务Id
     */
    private String parentTaskId;
	/**
     * 任务附属变量
     */
    private String variable;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Integer getPerformType() {
		return performType;
	}
	public void setPerformType(Integer performType) {
		this.performType = performType;
	}
	public Integer getTaskType() {
		return taskType;
	}
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	public Integer getTaskState() {
		return taskState;
	}
	public void setTaskState(Integer taskState) {
		this.taskState = taskState;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public Set<TaskActorBean> getHistActors() {
		return histActors;
	}
	public void setHistActors(Set<TaskActorBean> histActors) {
		this.histActors = histActors;
	}
	public String[] getActorIds() {
		return actorIds;
	}
	public void setActorIds(String[] actorIds) {
		this.actorIds = actorIds;
	}
	public String getParentTaskId() {
		return parentTaskId;
	}
	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
}
