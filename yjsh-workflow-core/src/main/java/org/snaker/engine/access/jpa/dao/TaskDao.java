package org.snaker.engine.access.jpa.dao;

import java.util.List;

import org.snaker.engine.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hhh.fund.util.SpecificationsRepository;

/**
 * 任务实例类
 * @author 3hhjj
 *
 */
public interface TaskDao extends SpecificationsRepository<Task, String> {
	
	@Query("select t from Task t where t.orderId = :orderId")
	public List<Task> findByOrderId(@Param("orderId")String orderId);
	
	@Query("select t from Task t where t.parentTaskId = ?1")
	public List<Task> findNextActiveTasks(String parentTaskId);
	@Query("select t from Task t where t.parentTaskId in (select h.id from HistoryTask h where h.orderId= ?1 and h.taskName= ?2 and h.parentTaskId= ?3 )")
	public List<Task> findNextActiveTasks(String orderId, String taskName, String parentTaskId);
}
