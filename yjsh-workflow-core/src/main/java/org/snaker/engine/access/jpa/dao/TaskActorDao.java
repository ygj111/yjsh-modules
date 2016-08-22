package org.snaker.engine.access.jpa.dao;

import java.util.List;

import org.snaker.engine.entity.HistoryTaskActor;
import org.snaker.engine.entity.TaskActor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.hhh.fund.util.SpecificationsRepository;

/**
 * 任务参与者实体类Dao
 * @author 3hhjj
 *
 */
public interface TaskActorDao extends SpecificationsRepository<TaskActor, Long> {

	
	@Query("select t from TaskActor t where t.taskId=:id")
	public List<TaskActor> findByTaskId(@Param("id")String taskId);
	
	@Modifying
	@Query("delete from TaskActor t where t.taskId=?1 and t.actorId=?2")
	public void deleteByTaskIdActorId(String taskId, String actor);
}
