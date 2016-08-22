/* Copyright 2012-2013 the original author or authors.
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
package com.hhh.workflow.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Surrogate;
import org.snaker.engine.entity.Task;
import org.snaker.engine.model.TaskModel.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流程服务接口
 * @since 0.1
 */
@Service
@Transactional
public class EngineService {
	@Autowired
	private SnakerEngine engine;
	
	public SnakerEngine getEngine() {
		return engine;
	}
	
	/**
	 * 获取所有流程的名称
	 * @return
	 */
	public List<String> getAllProcessNames() {
		List<Process> list = engine.process().getProcesss(new QueryFilter());
		List<String> names = new ArrayList<String>();
		for(Process entity : list) {
			if(names.contains(entity.getName())) {
				continue;
			} else {
				names.add(entity.getName());
			}
		}
		return names;
	}
	
	/**
	 * 根据流程定义ID，操作人ID，参数列表启动流程实例
	 * @param processId 流程ID
	 * @param operator 操作人ID
	 * @param args 参数表
	 * @return
	 */
	public Order startInstanceById(String processId, String operator, Map<String, Object> args) {
		return engine.startInstanceById(processId, operator, args);
	}
	
	/**
	 * 根据流程名称、版本号、操作人、参数列表启动流程实例
	 * @param name 流程名称
	 * @param version 版本号
	 * @param operator 操作人ID
	 * @param args 参数表
	 * @return
	 */
	public Order startInstanceByName(String name, Integer version, String operator, Map<String, Object> args) {
		return engine.startInstanceByName(name, version, operator, args);
	}
	
	/**
	 * 启动流程并执行任务
	 * @param name 流程名
	 * @param version 版本号
	 * @param operator 操作人
	 * @param args 参数
	 * @return
	 */
	public Order startAndExecute(String name, Integer version, String operator, Map<String, Object> args) {
		Order order = engine.startInstanceByName(name, version, operator, args);
		List<Task> tasks = engine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
		List<Task> newTasks = new ArrayList<Task>();
		if(tasks != null && tasks.size() > 0) {
			Task task = tasks.get(0);
			newTasks.addAll(engine.executeTask(task.getId(), operator, args));
		}
		return order;
	}
	
	
	/**
	 * 启动流程并执行任务
	 * @param processId 流程ID
	 * @param operator 操作人
	 * @param args 参数
	 * @return
	 */
	public Order startAndExecute(String processId, String operator, Map<String, Object> args) {
		Order order = engine.startInstanceById(processId, operator, args);
		List<Task> tasks = engine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
		List<Task> newTasks = new ArrayList<Task>();
		if(tasks != null && tasks.size() > 0) {
			Task task = tasks.get(0);
			newTasks.addAll(engine.executeTask(task.getId(), operator, args));
		}
		return order;
	}
	
	/**
	 * 根据任务主键ID，操作人ID，参数列表执行任务
	 * @param taskId
	 * @param operator
	 * @param args
	 * @return
	 */
	public List<Task> execute(String taskId, String operator, Map<String, Object> args) {
		return engine.executeTask(taskId, operator, args);
	}
	
	/**
	 * 根据任务主键ID，操作人ID，参数列表执行任务，并且根据nodeName跳转到任意节点 
	 * 1、nodeName为null时，则跳转至上一步处理 
	 * 2、nodeName不为null时，则任意跳转，即动态创建转移
	 * @param taskId
	 * @param operator
	 * @param args
	 * @param nodeName
	 * @return
	 */
	public List<Task> executeAndJump(String taskId, String operator, Map<String, Object> args, String nodeName) {
		return engine.executeAndJumpTask(taskId, operator, args, nodeName);
	}

	/**
	 * 转办任务
	 * @param taskId
	 * @param operator
	 * @param actors
	 * @return
	 */
    public List<Task> transferMajor(String taskId, String operator, String... actors) {
        List<Task> tasks = engine.task().createNewTask(taskId, TaskType.Major.ordinal(), actors);
        engine.task().complete(taskId, operator);
        return tasks;
    }

    /**
     * 协办任务
     * @param taskId
     * @param operator
     * @param actors
     * @return
     */
    public List<Task> transferAidant(String taskId, String operator, String... actors) {
        List<Task> tasks = engine.task().createNewTask(taskId, TaskType.Aidant.ordinal(), actors);
        engine.task().complete(taskId, operator);
        return tasks;
    }
    
    /**
     * 取流程实例的数据
     * @param orderId 实例ID
     * @param taskName 流程名称
     * @return
     */
    public Map<String, Object> flowData(String orderId, String taskName) {
    	Map<String, Object> data = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(orderId) && StringUtils.isNotEmpty(taskName)) {
			List<HistoryTask> histTasks = engine.query()
					.getHistoryTasks(
							new QueryFilter().setOrderId(orderId).setName(
									taskName));
			List<Map<String, Object>> vars = new ArrayList<Map<String,Object>>();
			for(HistoryTask hist : histTasks) {
				vars.add(hist.getVariableMap());
			}
			data.put("vars", vars);
			data.put("histTasks", histTasks);
		}
		return data;
	}
	
    /**
     * 新增委托授权
     * @param entity
     */
	public void addSurrogate(Surrogate entity) {
		if(entity.getState() == null) {
			entity.setState(1);
		}
		engine.manager().saveOrUpdate(entity);
	}
	
	/**
	 * 删除委托授权
	 * @param id
	 */
	public void deleteSurrogate(String id) {
		engine.manager().deleteSurrogate(id);
	}
	
	/**
	 * 取委托授权
	 * @param id
	 * @return
	 */
	public Surrogate getSurrogate(String id) {
		return engine.manager().getSurrogate(id);
	}
	
	/**
	 * 委托授权查询
	 * @param page
	 * @param filter
	 * @return
	 */
	public Page<Surrogate> searchSurrogate(Pageable page, QueryFilter filter) {
		return engine.manager().getSurrogate(filter, page);
	}
}
