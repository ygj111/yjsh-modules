package org.snaker.engine.access.jpa.dao;

import java.util.List;

import org.snaker.engine.entity.HistoryTaskActor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface HistoryTaskActorDao extends Repository<HistoryTaskActor, Long> {

	public HistoryTaskActor save(HistoryTaskActor entity);
	
	@Query("select h from HistoryTaskActor h where h.taskId=:taskId")
	public List<HistoryTaskActor> findHistTaskActorsByTaskId(@Param("taskId") String taskId);
}
