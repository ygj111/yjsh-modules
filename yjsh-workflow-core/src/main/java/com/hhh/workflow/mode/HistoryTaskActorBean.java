package com.hhh.workflow.mode;

import java.io.Serializable;

public class HistoryTaskActorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1315999432762382158L;
	
	private Long id;
	/**
	 * 关联的任务ID
	 */
    /**
     * 关联的参与者ID（参与者可以为用户、部门、角色）
     */
    private String actorId;
	
	 /**
     * 关联的参与者ID（参与者可以为用户、部门、角色）
     */
    private String taskId;
	
	public HistoryTaskActorBean(){
		
	}

	
	public String getActorId() {
		return actorId;
	}
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
}
