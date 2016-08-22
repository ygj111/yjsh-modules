package org.snaker.engine.access.jpa.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.snaker.engine.access.CustomerSpecs;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.WorkItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class WorkItemDaoImpl implements WorkItemDao {
	
	@PersistenceContext
	private EntityManager em; 

	
	public Page<WorkItem> findWorkItemsAll(QueryFilter filter, Pageable page) {
		String sql = CustomerSpecs.getWorkItemsSQL(filter);
		Query q = em.createNativeQuery(sql);
		q.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize());
		List<?> list = q.getResultList();
		
		List<WorkItem> items = new ArrayList<WorkItem>();
		if(null != list && !list.isEmpty()){
			for (Object row : list) { 
		         Object[] cells = (Object[]) row;
		         WorkItem item = fullOfWorkItem(cells);
		         items.add(item);
			}
		}
		String countSql = "select count(1) from ("+sql+") w";
		q = em.createNativeQuery(countSql);
		BigInteger total = (BigInteger)q.getSingleResult();
		PageImpl<WorkItem> pageimpl = new PageImpl<>(items, page, total.longValue());
		return pageimpl;
	}

	private WorkItem fullOfWorkItem(Object[] cells) {
		WorkItem item = new WorkItem();
		 item.setProcessId(String.valueOf(cells[0]));
		 item.setOrderId(String.valueOf(cells[1]));
		 item.setId(String.valueOf(cells[2]));
		 item.setTaskId(String.valueOf(cells[3]));
		 item.setProcessName(String.valueOf(cells[4]));
		 item.setInstanceUrl(String.valueOf(cells[5]));
		 item.setParentId(String.valueOf(cells[6]));
		 item.setCreator(String.valueOf(cells[7]));
		 item.setOrderCreateTime(String.valueOf(cells[8]));
		 item.setOrderExpireTime(String.valueOf(cells[9]));
		 item.setOrderNo(String.valueOf(cells[10]));
		 item.setOrderVariable(String.valueOf(cells[11]));
		 item.setTaskName(String.valueOf(cells[12]));
		 item.setTaskKey(String.valueOf(cells[13]));
		 item.setTaskType(Integer.valueOf(String.valueOf(cells[14])));
		 item.setPerformType(Integer.valueOf(String.valueOf(cells[15])));
		 item.setOperator(String.valueOf(cells[16]));
		 item.setActionUrl(String.valueOf(cells[16]));
		 item.setTaskCreateTime(String.valueOf(cells[18]));
		 item.setTaskEndTime(String.valueOf(cells[19]));
		 item.setTaskExpireTime(String.valueOf(cells[20]));
		 item.setTaskVariable(String.valueOf(cells[21]));
		 return item;
	}

	public Page<HistoryOrder> findCCWorksAll(QueryFilter filter, Pageable page) {
		String sql = CustomerSpecs.getCCWorksSQL(filter);
		Query q = em.createNativeQuery(sql);
		q.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize());
		List<?> list = q.getResultList();
		
		List<HistoryOrder> orders = new ArrayList<>();
		if(list != null && !list.isEmpty()){
			
		}
		
		String countSql = "select count(1) from ("+sql+") w";
		q = em.createNativeQuery(countSql);
		BigInteger total = (BigInteger)q.getSingleResult();
		PageImpl<HistoryOrder> pageimpl = new PageImpl<>(orders, page, total.longValue());
		return pageimpl;
	}

	public Page<WorkItem> findHistoryWorkItemsAll(QueryFilter filter, Pageable page) {
		String sql = CustomerSpecs.getHistoryWorkItemsSQL(filter);
		Query q = em.createNativeQuery(sql);
		q.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize());
		List<?> list = q.getResultList();
		List<WorkItem> items = new ArrayList<WorkItem>();
		if(null != list && !list.isEmpty()){
			for (Object row : list) {
		         Object[] cells = (Object[]) row;
		         WorkItem item = fullOfWorkItem(cells);
		         items.add(item);
			}
		}
		String countSql = "select count(1) from ("+sql+") w";
		q = em.createNativeQuery(countSql);
		BigInteger total = (BigInteger)q.getSingleResult();
		PageImpl<WorkItem> pageimpl = new PageImpl<>(items, page, total.longValue());
		return pageimpl;
	}

}
