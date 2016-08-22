package org.snaker.engine.core;

import java.util.List;

import org.snaker.engine.SnakerDBService;
import org.snaker.engine.access.CustomerSpecs;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.access.jpa.dao.CCOrderDao;
import org.snaker.engine.access.jpa.dao.HistoryOrderDao;
import org.snaker.engine.access.jpa.dao.HistoryTaskActorDao;
import org.snaker.engine.access.jpa.dao.HistoryTaskDao;
import org.snaker.engine.access.jpa.dao.OrderDao;
import org.snaker.engine.access.jpa.dao.ProcessDao;
import org.snaker.engine.access.jpa.dao.SurrogateDao;
import org.snaker.engine.access.jpa.dao.TaskActorDao;
import org.snaker.engine.access.jpa.dao.TaskDao;
import org.snaker.engine.access.jpa.dao.WorkItemDao;
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
import org.snaker.engine.helper.AssertHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SnakerDBServiceImpl implements SnakerDBService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private HistoryOrderDao historyOrderDao;
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private TaskActorDao taskActorDao;
	@Autowired
	private HistoryTaskActorDao historyTaskActorDao;
	@Autowired
	private HistoryTaskDao historyTaskDao;
	@Autowired
	private WorkItemDao workItemDao;
	@Autowired
	private CCOrderDao ccOrderDao;
	@Autowired
	private ProcessDao processDao;
	@Autowired
	private SurrogateDao surrogateDao;
	
	public void saveTask(Task task) {
		taskDao.save(task);
	}

	public Order saveOrder(Order order) {
		return orderDao.save(order);
	}

	public void saveCCOrder(CCOrder ccorder) {
		ccOrderDao.save(ccorder);
	}

	public void saveProcess(Process process) {
		processDao.save(process);
	}

	public void saveTaskActor(TaskActor taskActor) {
		taskActorDao.save(taskActor);
	}

	public void updateTask(Task task) {
		task = taskDao.save(task);

	}

	public void updateOrder(Order order) {
		orderDao.save(order);
	}

	public void updateCCOrder(CCOrder ccorder) {
		ccOrderDao.save(ccorder);
	}

	public void updateProcess(Process process) {
		processDao.save(process);
	}

	public void deleteProcess(Process process) {
		processDao.delete(process);
	}

	public void updateProcessType(String id, String type) {
		processDao.setProcessType(id, type);
	}

	public void deleteTask(Task task) {
		taskDao.delete(task);
	}

	public void deleteOrder(Order order) {
		orderDao.delete(order);
	}

	public void deleteCCOrder(CCOrder ccorder) {
		ccOrderDao.delete(ccorder);
	}

	public void removeTaskActor(String taskId, String... actors) {
		if(null == taskId || actors == null || actors.length == 0){
			return;
		}
		for(String aid : actors){
			taskActorDao.deleteByTaskIdActorId(taskId, aid);
		}
	}

	public void saveHistory(HistoryOrder order) {
		historyOrderDao.save(order);
	}

	public void updateHistory(HistoryOrder order) {
		historyOrderDao.save(order);
	}

	public void saveHistory(HistoryTask task) {
		historyTaskDao.save(task);
		for(HistoryTaskActor ta : task.getHistActors()){
			historyTaskActorDao.save(ta);
		}
	}

	public void deleteHistoryOrder(HistoryOrder historyOrder) {
		historyOrderDao.delete(historyOrder);
	}

	public void deleteHistoryTask(HistoryTask historyTask) {
		historyTaskDao.delete(historyTask);
	}

	public void updateOrderVariable(Order order) {
		updateOrder(order);
		HistoryOrder hist = getHistOrder(order.getId());
        hist.setVariable(order.getVariable());
        updateHistory(hist);
	}

	public void saveSurrogate(Surrogate surrogate) {
		surrogateDao.save(surrogate);

	}

	public void updateSurrogate(Surrogate surrogate) {
		surrogateDao.save(surrogate);
	}

	public void deleteSurrogate(Surrogate surrogate) {
		surrogateDao.delete(surrogate);
	}

	public Surrogate getSurrogate(String id) {
		return surrogateDao.findOne(id);
	}

	public Page<Surrogate> getSurrogate(QueryFilter filter, Pageable page) {
		Specification<Surrogate> spec = CustomerSpecs.getSurrogateQueryField(filter);
		return surrogateDao.findAll(spec, page);
	}
	
	public List<Surrogate> getSurrogate(QueryFilter filter) {
		Specification<Surrogate> spec = CustomerSpecs.getSurrogateQueryField(filter);
		return surrogateDao.findAll(spec);
	}

	public Task getTask(String taskId) {
		Task task =  taskDao.findOne(taskId);
		task.setTaskActors(taskActorDao.findByTaskId(taskId));
		return task;
	}

	public HistoryTask getHistTask(String taskId) {
		HistoryTask task = historyTaskDao.findOne(taskId);
		task.setHistActors(historyTaskActorDao.findHistTaskActorsByTaskId(taskId));
		return task;
	}

	public List<Task> getNextActiveTasks(String parentTaskId) {
		return taskDao.findNextActiveTasks(parentTaskId);
	}

	public List<Task> getNextActiveTasks(String orderId, String taskName, String parentTaskId) {
		return taskDao.findNextActiveTasks(orderId, taskName, parentTaskId);
	}

	public List<TaskActor> getTaskActorsByTaskId(String taskId) {
		return taskActorDao.findByTaskId(taskId);
	}

	public List<HistoryTaskActor> getHistTaskActorsByTaskId(String taskId) {
		return historyTaskActorDao.findHistTaskActorsByTaskId(taskId);
	}

	public Order getOrder(String orderId) {
		return orderDao.findOne(orderId);
	}

	public List<CCOrder> getCCOrder(String orderId, String... actorIds) {
		Specification<CCOrder> spec = CustomerSpecs.getCCOrderQueryField(orderId, actorIds);
		return ccOrderDao.findAll(spec);
	}

	public HistoryOrder getHistOrder(String orderId) {
		return historyOrderDao.findOne(orderId);
	}

	public Process getProcess(String id) {
		return processDao.findOne(id);
	}

	public Integer getLatestProcessVersion(String name) {
		return processDao.findByName(name);
	}

	public Page<Process> getProcesss(QueryFilter filter, Pageable page) {
		Specification<Process> spec = CustomerSpecs.getProcessQueryField(filter);
		return processDao.findAll(spec, page);
	}

	public List<Process> getProcesss(QueryFilter filter) {
		Specification<Process> spec = CustomerSpecs.getProcessQueryField(filter);
		return processDao.findAll(spec);
	}
	
	public Page<Order> getActiveOrders(QueryFilter filter, Pageable page) {
		Specification<Order> spec =CustomerSpecs.getOrderQueryField(filter);
		return orderDao.findAll(spec, page);
	}
	
	public List<Order> getActiveOrders(QueryFilter filter) {
		Specification<Order> spec =CustomerSpecs.getOrderQueryField(filter);
		return orderDao.findAll(spec);
	}

	public Page<Task> getActiveTasks(QueryFilter filter, Pageable page) {
		Specification<Task> spec = CustomerSpecs.getTaskQueryField(filter);
		return taskDao.findAll(spec, page);
	}

	public List<Task> getActiveTasks(QueryFilter filter) {
		Specification<Task> spec = CustomerSpecs.getTaskQueryField(filter);
		return taskDao.findAll(spec);
	}
	
	public Page<HistoryOrder> getHistoryOrders(QueryFilter filter, Pageable page) {
		Specification<HistoryOrder> spec = CustomerSpecs.getHistoryOrderQueryField(filter);
		return historyOrderDao.findAll(spec, page);
	}
	
	public List<HistoryOrder> getHistoryOrders(QueryFilter filter) {
		Specification<HistoryOrder> spec = CustomerSpecs.getHistoryOrderQueryField(filter);
		return historyOrderDao.findAll(spec);
	}

	public Page<HistoryTask> getHistoryTasks(QueryFilter filter, Pageable page) {
		Specification<HistoryTask> spec =CustomerSpecs.getHistTaskQueryField(filter);
		return historyTaskDao.findAll(spec, page);
	}

	public List<HistoryTask> getHistoryTasks(QueryFilter filter) {
		Specification<HistoryTask> spec =CustomerSpecs.getHistTaskQueryField(filter);
		return historyTaskDao.findAll(spec);
	}
	
	public Page<WorkItem> getWorkItems(QueryFilter filter, Pageable page) {
		return workItemDao.findWorkItemsAll(filter, page);
	}

	public Page<HistoryOrder> getCCWorks(QueryFilter filter, Pageable page) {
		return workItemDao.findCCWorksAll(filter, page);
	}

	public Page<WorkItem> getHistoryWorkItems(QueryFilter filter, Pageable page) {
		AssertHelper.notNull(filter);
		return workItemDao.findHistoryWorkItemsAll(filter, page);
	}

	public List<HistoryOrder> findByProcessId(String processId) {
		return historyOrderDao.findByProcessId(processId);
	}

	public List<CCOrder> getCCOrder(String orderId) {
		return ccOrderDao.findByOrderId(orderId);
	}

}
