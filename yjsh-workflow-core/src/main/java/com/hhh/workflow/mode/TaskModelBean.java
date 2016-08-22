package com.hhh.workflow.mode;

import java.io.Serializable;

public class TaskModelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 元素名称
	 */
	private String name;
	/**
	 * 显示名称
	 */
	private String displayName;
	
	/**
	 * form
	 */
	private String form;
	
	/**
	 * 参与者变量名称
	 */
	private String assignee;
	/**
	 * 参与方式
	 * any：任何一个参与者处理完即执行下一步
	 * all：所有参与者都完成，才可执行下一步
	 */
	private String performType ;
	/**
	 * 任务类型
	 * major：主办任务
	 * aidant：协办任务
	 */
	private String taskType;
	/**
	 * 期望完成时间
	 */
	private String expireTime;
	/**
	 * 提醒时间
	 */
	private String reminderTime;
	/**
	 * 提醒间隔(分钟)
	 */
	private String reminderRepeat;
	/**
	 * 是否自动执行
	 */
	private String autoExecute;
	/**
	 * 任务执行后回调类
	 */
	private String callback;
	/**
	 * 分配参与者处理类型
	 */
	private String assignmentHandler;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getPerformType() {
		return performType;
	}
	public void setPerformType(String performType) {
		this.performType = performType;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getReminderTime() {
		return reminderTime;
	}
	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}
	public String getReminderRepeat() {
		return reminderRepeat;
	}
	public void setReminderRepeat(String reminderRepeat) {
		this.reminderRepeat = reminderRepeat;
	}
	public String getAutoExecute() {
		return autoExecute;
	}
	public void setAutoExecute(String autoExecute) {
		this.autoExecute = autoExecute;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getAssignmentHandler() {
		return assignmentHandler;
	}
	public void setAssignmentHandler(String assignmentHandler) {
		this.assignmentHandler = assignmentHandler;
	}
	
}
