package org.snaker.engine.access.jpa.dao;

import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.WorkItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkItemDao {

	/**
	 * 根据查询的参数，分页对象，返回分页后的活动工作项
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<WorkItem> 活动工作项
	 */
	public Page<WorkItem> findWorkItemsAll(QueryFilter filter, Pageable page);
	
	/**
	 * 根据查询的参数，分页对象，返回分页后的抄送任务项
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<WorkItem> 活动工作项
	 */
	public Page<HistoryOrder> findCCWorksAll(QueryFilter filter, Pageable page);
	
	/**
	 * 根据流程定义ID、参与者分页查询已完成的历史任务项
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<WorkItem> 历史工作项
	 */
	public Page<WorkItem> findHistoryWorkItemsAll(QueryFilter filter, Pageable page);
}
