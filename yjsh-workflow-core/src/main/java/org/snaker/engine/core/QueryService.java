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
package org.snaker.engine.core;

import java.util.ArrayList;
import java.util.List;

import org.snaker.engine.IQueryService;
import org.snaker.engine.SnakerDBService;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.HistoryTaskActor;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.TaskActor;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.helper.AssertHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhh.fund.util.FundPage;
import com.hhh.workflow.mode.HistoryOrderBean;
import com.hhh.workflow.mode.ProcessBean;

/**
 * 查询服务实现类
 * 
 * @author yuqs
 * @since 1.0
 */

@Service("queryService")
@Transactional(readOnly=true)
public class QueryService extends AccessService implements IQueryService {

	@Autowired
	private SnakerDBService snakerDb;

	public Order getOrder(String orderId) {
		return snakerDb.getOrder(orderId);// access().getOrder(orderId);
	}

	public Task getTask(String taskId) {
		return snakerDb.getTask(taskId);// access().getTask(taskId);
	}

	public String[] getTaskActorsByTaskId(String taskId) {

		List<TaskActor> actors = snakerDb.getTaskActorsByTaskId(taskId);// access().getTaskActorsByTaskId(taskId);
		if (actors == null || actors.isEmpty())
			return null;
		String[] actorIds = new String[actors.size()];
		for (int i = 0; i < actors.size(); i++) {
			TaskActor ta = actors.get(i);
			actorIds[i] = ta.getActorId();
		}
		return actorIds;
	}

	public String[] getHistoryTaskActorsByTaskId(String taskId) {
		List<HistoryTaskActor> actors = snakerDb.getHistTaskActorsByTaskId(taskId); // access().getHistTaskActorsByTaskId(taskId);
		if (actors == null || actors.isEmpty())
			return null;
		String[] actorIds = new String[actors.size()];
		for (int i = 0; i < actors.size(); i++) {
			HistoryTaskActor ta = actors.get(i);
			actorIds[i] = ta.getActorId();
		}
		return actorIds;
	}

	public HistoryOrder getHistOrder(String orderId) {
		return snakerDb.getHistOrder(orderId);// access().getHistOrder(orderId);
	}

	public HistoryTask getHistTask(String taskId) {
		return snakerDb.getHistTask(taskId);// access().getHistTask(taskId);
	}

	public List<Task> getActiveTasks(QueryFilter filter) {
		AssertHelper.notNull(filter);
		return snakerDb.getActiveTasks(filter);// access().getActiveTasks(null, filter);
	}

	public Page<Task> getActiveTasks(QueryFilter filter, Pageable page) {
		AssertHelper.notNull(filter);
		return snakerDb.getActiveTasks(filter, page);// access().getActiveTasks(page, filter);
	}

	public List<Order> getActiveOrders(QueryFilter filter) {
		AssertHelper.notNull(filter);
		return snakerDb.getActiveOrders(filter);// access().getActiveOrders(null, filter);
	}
	

	public Page<Order> getActiveOrders(QueryFilter filter, Pageable page) {
		AssertHelper.notNull(filter);
		return snakerDb.getActiveOrders(filter, page);// access().getActiveOrders(page, filter);
	}

	public List<HistoryOrder> getHistoryOrders(QueryFilter filter) {
		AssertHelper.notNull(filter);
		return snakerDb.getHistoryOrders(filter);// access().getHistoryOrders(null, filter);
	}
	
	public FundPage<HistoryOrderBean> getHistoryOrders(QueryFilter filter, Pageable page) {
		AssertHelper.notNull(filter);
		Page<HistoryOrder> pages = snakerDb.getHistoryOrders(filter, page);
		List<HistoryOrderBean> list = new ArrayList<>();
		if(pages.getTotalElements() > 0){
			for(HistoryOrder hist : pages.getContent()){
				Process p = hist.getProcess();
				ProcessBean pbean = new ProcessBean();
				BeanUtils.copyProperties(p, pbean);
				HistoryOrderBean bean = new HistoryOrderBean();
				BeanUtils.copyProperties(hist, bean);
				bean.setProcess(pbean);
				list.add(bean);
			}
		}
		return new FundPage<>(pages.getTotalPages(), pages.getTotalElements(), list);// access().getHistoryOrders(page,
												// filter);
	}

	public List<HistoryTask> getHistoryTasks(QueryFilter filter) {
		AssertHelper.notNull(filter);
		return snakerDb.getHistoryTasks(filter);//access().getHistoryTasks(null, filter);
	}

	
	public Page<HistoryTask> getHistoryTasks(QueryFilter filter, Pageable page) {
		AssertHelper.notNull(filter);
		return snakerDb.getHistoryTasks(filter, page);//access().getHistoryTasks(page, filter);
	}

	public Page<WorkItem> getWorkItems(QueryFilter filter, Pageable page) {
		AssertHelper.notNull(filter);
		return snakerDb.getWorkItems(filter, page);//getWorkItems(page, filter);
	}

	public Page<HistoryOrder> getCCWorks(QueryFilter filter, Pageable page) {
		AssertHelper.notNull(filter);
		return snakerDb.getCCWorks(filter, page);//access().getCCWorks(page, filter);
	}

	public Page<WorkItem> getHistoryWorkItems(QueryFilter filter, Pageable page) {
		AssertHelper.notNull(filter);
		return snakerDb.getHistoryWorkItems(filter, page);//access().getHistoryWorkItems(page, filter);
	}

//	public <T> T nativeQueryObject(Class<T> T, String sql, Object... args) {
//		AssertHelper.notEmpty(sql);
//		AssertHelper.notNull(T);
//		return access().queryObject(T, sql, args);
//	}
//
//	public <T> List<T> nativeQueryList(Class<T> T, String sql, Object... args) {
//		AssertHelper.notEmpty(sql);
//		AssertHelper.notNull(T);
//		return access().queryList(T, sql, args);
//	}
//
//	public <T> List<T> nativeQueryList(Page<T> page, Class<T> T, String sql, Object... args) {
//		AssertHelper.notEmpty(sql);
//		AssertHelper.notNull(T);
//		return access().queryList(page, new QueryFilter(), T, sql, args);
//	}
}
