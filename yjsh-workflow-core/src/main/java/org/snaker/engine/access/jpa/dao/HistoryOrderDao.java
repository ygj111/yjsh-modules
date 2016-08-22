package org.snaker.engine.access.jpa.dao;

import java.util.List;

import org.snaker.engine.entity.HistoryOrder;
import org.springframework.data.jpa.repository.Query;

import com.hhh.fund.util.SpecificationsRepository;

public interface HistoryOrderDao extends SpecificationsRepository<HistoryOrder, String> {
	
	@Query("select h from HistoryOrder h where h.process.id = ?1 ")
	public List<HistoryOrder> findByProcessId(String processId);

}
