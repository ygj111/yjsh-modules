package org.snaker.engine;

import java.util.List;

import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.CCOrder;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.HistoryTaskActor;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Surrogate;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.TaskActor;
import org.snaker.engine.entity.WorkItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SnakerDBService {

	/**
	 * 保存任务对象
	 * @param task 任务对象
	 */
	public void saveTask(Task task);
	
	/**
	 * 保存流程实例对象
	 * @param order 流程实例对象
	 */
	public Order saveOrder(Order order);
	
	/**
	 * 保存抄送实例
	 * @param ccorder 抄送实体
	 * @since 1.5
	 */
	public void saveCCOrder(CCOrder ccorder);
	
	/**
	 * 保存流程定义对象
	 * @param process 流程定义对象
	 */
	public void saveProcess(Process process);
	
	/**
	 * 保存任务参与者对象
	 * @param taskActor 任务参与者对象
	 */
	public void saveTaskActor(TaskActor taskActor);
	
	/**
	 * 更新任务对象
	 * @param task 任务对象
	 */
	public void updateTask(Task task);
	
	/**
	 * 更新流程实例对象
	 * @param order 流程实例对象
	 */
	public void updateOrder(Order order);
	
	/**
	 * 更新抄送状态
	 * @param ccorder 抄送实体对象
	 */
	public void updateCCOrder(CCOrder ccorder);
	
	/**
	 * 更新流程定义对象
	 * @param process 流程定义对象
	 */
	public void updateProcess(Process process);

	/**
	 * 删除流程定义对象
	 * @param process 流程定义对象
	 */
	public void deleteProcess(Process process);
	
	/**
	 * 更新流程定义类别
	 * @param type 类别
	 * @since 1.5
	 */
	public void updateProcessType(String id, String type);
	
	/**
	 * 删除任务、任务参与者对象
	 * @param task 任务对象
	 */
	public void deleteTask(Task task);
	
	/**
	 * 删除流程实例对象
	 * @param order 流程实例对象
	 */
	public void deleteOrder(Order order);
	
	/**
	 * 删除抄送记录
	 * @param ccorder 抄送实体对象
	 */
	public void deleteCCOrder(CCOrder ccorder);
	
	/**
	 * 删除参与者
	 * @param taskId 任务id
	 * @param actors 参与者集合
	 */
	public void removeTaskActor(String taskId, String... actors);
	
	/**
	 * 迁移活动实例
	 * @param order 历史流程实例对象
	 */
	public void saveHistory(HistoryOrder order);
	
	/**
	 * 更新历史流程实例状态
	 * @param order 历史流程实例对象
	 */
	public void updateHistory(HistoryOrder order);
	
	/**
	 * 迁移活动任务
	 * @param task 历史任务对象
	 */
	public void saveHistory(HistoryTask task);

	/**
	 * 删除历史实例记录
	 * @param historyOrder 历史实例
	 */
	public void deleteHistoryOrder(HistoryOrder historyOrder);

	/**
	 * 删除历史任务记录
	 * @param historyTask 历史任务
	 */
	public void deleteHistoryTask(HistoryTask historyTask);

    /**
     * 更新实例变量（包括历史实例表）
     * @param order 实例对象
     */
    public void updateOrderVariable(Order order);
	
	/**
	 * 保存委托代理对象
	 * @param surrogate 委托代理对象
	 */
	public void saveSurrogate(Surrogate surrogate);
	
	/**
	 * 更新委托代理对象
	 * @param surrogate 委托代理对象
	 */
	public void updateSurrogate(Surrogate surrogate);
	
	/**
	 * 删除委托代理对象
	 * @param surrogate 委托代理对象
	 */
	public void deleteSurrogate(Surrogate surrogate);
	
	/**
	 * 根据主键id查询委托代理对象
	 * @param id 主键id
	 * @return surrogate 委托代理对象
	 */
	public Surrogate getSurrogate(String id);
	
	/**
	 * 根据授权人、流程名称查询委托代理对象
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<Surrogate> 委托代理对象集合
	 */
	public Page<Surrogate> getSurrogate(QueryFilter filter, Pageable page);
	
	public List<Surrogate> getSurrogate(QueryFilter filter);
	
	/**
	 * 根据进程ID查历史实例
	 * @param processId
	 * @return
	 */
	public List<HistoryOrder> findByProcessId(String processId);
	
	/**
	 * 根据任务id查询任务对象
	 * @param taskId 任务id
	 * @return Task 任务对象
	 */
	public Task getTask(String taskId);
	
	/**
	 * 根据任务ID获取历史任务对象
	 * @param taskId 历史任务id
	 * @return 历史任务对象
	 */
	HistoryTask getHistTask(String taskId);
	
	/**
	 * 根据父任务id查询所有子任务
	 * @param parentTaskId 父任务id
	 * @return List<Task> 活动任务集合
	 */
	public List<Task> getNextActiveTasks(String parentTaskId);
	
	/**
	 * 根据流程实例id、任务名称获取
	 * @param orderId 流程实例id
	 * @param taskName 任务名称
	 * @param parentTaskId 父任务id
	 * @return List<Task> 活动任务集合
	 */
	public List<Task> getNextActiveTasks(String orderId, String taskName, String parentTaskId);
	
	/**
	 * 根据任务id查询所有活动任务参与者集合
	 * @param taskId 活动任务id
	 * @return List<TaskActor> 活动任务参与者集合
	 */
	public List<TaskActor> getTaskActorsByTaskId(String taskId);
	
	/**
	 * 根据任务id查询所有历史任务参与者集合
	 * @param taskId 历史任务id
	 * @return List<HistoryTaskActor> 历史任务参与者集合
	 */
	public List<HistoryTaskActor> getHistTaskActorsByTaskId(String taskId);
	
	/**
	 * 根据流程实例id查询实例对象
	 * @param orderId 活动流程实例id
	 * @return Order 活动流程实例对象
	 */
	public Order getOrder(String orderId);
	
	/**
	 * 根据流程实例id、参与者id获取抄送记录
	 * @param orderId 活动流程实例id
	 * @param actorIds 参与者id
	 * @return 传送记录列表
	 */
	public List<CCOrder> getCCOrder(String orderId, String... actorIds);
	
	/**
	 * 根据流程实例id获取抄送记录
	 * @param orderId 活动流程实例id
	 * @return 传送记录列表
	 */
	public List<CCOrder> getCCOrder(String orderId);
	
	/**
	 * 根据流程实例ID获取历史流程实例对象
	 * @param orderId 历史流程实例id
	 * @return HistoryOrder 历史流程实例对象
	 */
	HistoryOrder getHistOrder(String orderId);
	
	/**
	 * 根据流程定义id查询流程定义对象
	 * @param id 流程定义id
	 * @return Process 流程定义对象
	 */
	public Process getProcess(String id);
	
	/**
	 * 根据流程名称查询最近的版本号
	 * @param name 流程名称
	 * @return Integer 流程定义版本号
	 */
	public Integer getLatestProcessVersion(String name);
	
	/**
	 * 根据查询的参数，分页对象，返回分页后的查询结果
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<Process> 流程定义集合
	 */
	public Page<Process> getProcesss(QueryFilter filter, Pageable page);
	
	public List<Process> getProcesss(QueryFilter filter);
	
	/**
	 * 分页查询流程实例
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<Order> 活动流程实例集合
	 */
	public Page<Order> getActiveOrders(QueryFilter filter, Pageable page);
	
	public List<Order> getActiveOrders(QueryFilter filter);
	
	/**
	 * 分页查询活动任务列表
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<Task> 活动任务集合
	 */
	public Page<Task> getActiveTasks(QueryFilter filter, Pageable page);
	
	public List<Task> getActiveTasks(QueryFilter filter);
	
	/**
	 * 分页查询历史流程实例
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<HistoryOrder> 历史流程实例集合
	 */
	public Page<HistoryOrder> getHistoryOrders(QueryFilter filter, Pageable page);
	
	public List<HistoryOrder> getHistoryOrders(QueryFilter filter);
	
	/**
	 * 根据参与者分页查询已完成的历史任务
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<HistoryTask> 历史任务集合
	 */
	public Page<HistoryTask> getHistoryTasks(QueryFilter filter, Pageable page);
	
	public List<HistoryTask> getHistoryTasks(QueryFilter filter);
	
	/**
	 * 根据查询的参数，分页对象，返回分页后的活动工作项
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<WorkItem> 活动工作项
	 */
	public Page<WorkItem> getWorkItems(QueryFilter filter, Pageable page);
	
	/**
	 * 根据查询的参数，分页对象，返回分页后的抄送任务项
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<WorkItem> 活动工作项
	 */
	public Page<HistoryOrder> getCCWorks(QueryFilter filter, Pageable page);
	
	/**
	 * 根据流程定义ID、参与者分页查询已完成的历史任务项
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<WorkItem> 历史工作项
	 */
	public Page<WorkItem> getHistoryWorkItems(QueryFilter filter, Pageable page);
}
