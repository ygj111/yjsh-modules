package org.snaker.engine.access.jpa.dao;

import java.util.List;

import org.snaker.engine.entity.CCOrder;
import org.snaker.engine.entity.CCOrderPK;
import org.springframework.data.jpa.repository.Query;

import com.hhh.fund.util.SpecificationsRepository;

/**
 * 抄送实例实体DAO
 * @author 3hhjj
 *
 */
public interface CCOrderDao extends SpecificationsRepository<CCOrder, CCOrderPK> {

	@Query("select c from CCOrder c where c.id.orderId = ?1")
	public List<CCOrder> findByOrderId(String orderId);
	
}
