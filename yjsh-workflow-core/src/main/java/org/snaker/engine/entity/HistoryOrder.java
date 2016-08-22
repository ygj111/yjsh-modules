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
import java.util.Collections;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.snaker.engine.SnakerEngine;
import org.snaker.engine.core.ServiceContext;
import org.snaker.engine.helper.JsonHelper;

/**
 * 历史流程实例实体类
 * @author yuqs
 * @since 1.0
 */
@Entity
@Table(name="wf_hist_order")
public class HistoryOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5853727929104539328L;
	/**
	 * 主键ID
	 */
	@Id
	@Column(length=32)
	private String id;
	/**
	 * 流程定义ID
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name = "process_Id")
	private Process process;
	
//	@Column(name="process_Id", length=32)
//    private String processId;
    /**
     * 流程实例状态（0：结束；1：活动）
     */
	@Column(name="order_State", length=1)
    private Integer orderState;
    /**
     * 流程实例创建者ID
     */
	@Column(name="creator", length=50)
    private String creator;
    /**
     * 流程实例创建时间
     */
	@Column(name="create_Time", length=50)
    private String createTime;
    /**
     * 流程实例结束时间
     */
	@Column(name="end_Time", length=50)
    private String endTime;
    /**
     * 流程实例为子流程时，该字段标识父流程实例ID
     */
	@Column(name="parent_Id", length=32)
    private String parentId;
    /**
     * 流程实例期望完成时间
     */
    @Column(name="expire_Time", length=50)
    private String expireTime;
    /**
     * 流程实例优先级
     */
    @Column(name="priority", length=1)
    private Integer priority;
    /**
     * 流程实例编号
     */
    @Column(name="order_No", length=50)
    private String orderNo;
	/**
     * 流程实例附属变量
     */
    @Column(name="variable", length=2000)
    private String variable;

	public HistoryOrder() {
    	
    }
    
    public HistoryOrder(Order order) {
    	this.id = order.getId();
    	this.process = order.getProcess();
    	this.createTime = order.getCreateTime();
    	this.expireTime = order.getExpireTime();
    	this.creator = order.getCreator();
    	this.parentId = order.getParentId();
    	this.priority = order.getPriority();
    	this.orderNo = order.getOrderNo();
    	this.variable = order.getVariable();
    	
    }

    /**
     * 根据历史实例撤回活动实例
     * @return 活动实例对象
     */
    public Order undo() {
        Order order = new Order();
        order.setId(this.id);
        order.setProcess(this.process);
        order.setParentId(this.parentId);
        order.setCreator(this.creator);
        order.setCreateTime(this.createTime);
        order.setLastUpdator(this.creator);
        order.setLastUpdateTime(this.endTime);
        order.setExpireTime(this.expireTime);
        order.setOrderNo(this.orderNo);
        order.setPriority(this.priority);
        order.setVariable(this.variable);
        order.setVersion(0);
        return order;
    }

//	public String getProcessId() {
//		return processId;
//	}
//
//	public void setProcessId(String processId) {
//		this.processId = processId;
//	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
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

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

    @SuppressWarnings("unchecked")
    public Map<String, Object> getVariableMap() {
        Map<String, Object> map = JsonHelper.fromJson(this.variable, Map.class);
        if(map == null) return Collections.emptyMap();
        return map;
    }
	
	public String getProcessName() {
		SnakerEngine engine = ServiceContext.getEngine();
		Process process = engine.process().getProcessById(this.process.getId());
		if(process == null) return this.process.getId();
		return process.getDisplayName();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("HistoryOrder(id=").append(this.id);
		sb.append(",processId=").append(this.process.getId());
		sb.append(",creator=").append(this.creator);
		sb.append(",createTime").append(this.createTime);
		sb.append(",orderNo=").append(this.orderNo).append(")");
		return sb.toString();
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}
}
