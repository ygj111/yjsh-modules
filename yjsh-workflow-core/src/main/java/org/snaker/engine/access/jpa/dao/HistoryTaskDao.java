package org.snaker.engine.access.jpa.dao;

import java.util.List;

import org.snaker.engine.entity.HistoryTask;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hhh.fund.util.SpecificationsRepository;

public interface HistoryTaskDao extends SpecificationsRepository<HistoryTask, String> {
	
	@Query("select h from HistoryTask h where h.orderId = :orderId")
	public List<HistoryTask> findByOrderId(@Param("orderId")String order);
}
