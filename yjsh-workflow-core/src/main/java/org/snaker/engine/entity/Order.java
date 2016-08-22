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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.snaker.engine.helper.JsonHelper;

/**
 * 流程工作单实体类（一般称为流程实例）
 * @author yuqs
 * @since 1.0
 */
@Entity
@Table(name="wf_order")
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8335779448165343933L;
	/**
	 * 主键ID
	 */
	@Id
	@Column(name="id",length=32)
	@GeneratedValue(generator="idGenerator") 
	@GenericGenerator(name="idGenerator", strategy="uuid")
	private String id;
	/**
	 * 版本
	 */
	@Version
	@Column(name="version")
	private Integer version = 0;
	/**
	 * 流程定义ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "process_Id")
	private Process process;
//	@Column(name="process_Id", length=32)
//    private String processId;
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
     * 流程实例为子流程时，该字段标识父流程实例ID
     */
	@Column(name="parent_Id", length=32)
    private String parentId;
    /**
     * 流程实例为子流程时，该字段标识父流程哪个节点模型启动的子流程
     */
	@Column(name="parent_Node_Name", length=50)
    private String parentNodeName;
    /**
     * 流程实例期望完成时间
     */
	@Column(name="expire_Time", length=50)
    private String expireTime;
    /**
     * 流程实例上一次更新时间
     */
	@Column(name="last_Update_Time", length=50)
    private String lastUpdateTime;
    /**
     * 流程实例上一次更新人员ID
     */
	@Column(name="last_Updator", length=50)
    private String lastUpdator;
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

//	public String getProcessId() {
//		return processId;
//	}
//
//	public void setProcessId(String processId) {
//		this.processId = processId;
//	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentNodeName() {
		return parentNodeName;
	}

	public void setParentNodeName(String parentNodeName) {
		this.parentNodeName = parentNodeName;
	}

	public String getVariable() {
		return variable;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getVariableMap() {
        Map<String, Object> map = JsonHelper.fromJson(this.variable, Map.class);
        if(map == null) return Collections.emptyMap();
        return map;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}
	
    public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order(id=").append(this.id);
		sb.append(",processId=").append(getProcess().getId());
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
