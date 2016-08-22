package com.hhh.workflow.mode;

import java.io.Serializable;


/**
 * 流程任务的参与者
 * @author 3hhjj
 *
 */
public class TaskActorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8718428606045975204L;

	private Long id;
	/**
	 * 关联的任务ID
	 */
    private String taskId;
    /**
     * 关联的参与者ID（参与者可以为用户、部门、角色）
     */
    private String actorId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getActorId() {
		return actorId;
	}
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}
}
