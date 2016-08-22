/* Copyright 2013-2015 www.snakerflow.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.snaker.engine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 历史任务参与者实体类
 * @author yuqs
 * @since 1.0
 */
@Entity
@Table(name="wf_hist_task_actor")
public class HistoryTaskActor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -998098931519373599L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	/**
	 * 关联的任务ID
	 */
    /**
     * 关联的参与者ID（参与者可以为用户、部门、角色）
     */
	@Column(name="actor_Id", length=50)
    private String actorId;
	
	 /**
     * 关联的参与者ID（参与者可以为用户、部门、角色）
     */
	@Column(name = "task_Id",length=32) 
    private String taskId;
	
	public HistoryTaskActor(){
		
	}
	
	public HistoryTaskActor(String actorId, String taskId) {
		this.actorId = actorId;
		this.taskId = taskId;
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
}
