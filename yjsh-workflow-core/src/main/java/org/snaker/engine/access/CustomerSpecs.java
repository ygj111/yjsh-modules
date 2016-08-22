package org.snaker.engine.access;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.snaker.engine.entity.CCOrder;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.HistoryTaskActor;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Surrogate;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.TaskActor;
import org.snaker.engine.helper.StringHelper;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecs {

	public static Specification<HistoryOrder> getHistoryOrderQueryField(QueryFilter filter) {
		Specification<HistoryOrder> spec = getHistoryOrderWhere(filter.getOperators(), filter.getNames(),
				filter.getProcessId(), filter.getProcessType(), filter.getDisplayName(), filter.getParentId(),
				filter.getState(), filter.getCreateTimeStart(), filter.getCreateTimeEnd(), filter.getOrderNo());
		return spec;
	}

	public static Specification<HistoryTask> getHistTaskQueryField(QueryFilter filter) {
		Specification<HistoryTask> spec = getHistoryTaskWhere(filter.getOrderId(), filter.getOperators(),
				filter.getNames(), filter.getCreateTimeStart(), filter.getCreateTimeEnd());
		return spec;
	}

	public static Specification<Order> getOrderQueryField(QueryFilter filter) {
		Specification<Order> spec = getOrderWhere(filter.getOperators(), filter.getNames(), filter.getProcessId(),
				filter.getProcessType(), filter.getDisplayName(), filter.getParentId(), filter.getExcludedIds(),
				filter.getCreateTimeStart(), filter.getCreateTimeEnd(), filter.getOrderNo());
		return spec;
	}

	public static Specification<Task> getTaskQueryField(QueryFilter filter) {
		Specification<Task> spec = getTaskWhere(filter.getOrderId(), filter.getOperators(), filter.getNames(),
				filter.getCreateTimeStart(), filter.getCreateTimeEnd(), filter.getExcludedIds());
		return spec;
	}

	public static Specification<Surrogate> getSurrogateQueryField(QueryFilter filter) {
		Specification<Surrogate> spec = getSurrogatWhere(filter.getOperators(), filter.getOperateTime(),
				filter.getNames());
		return spec;
	}

	public static Specification<Process> getProcessQueryField(QueryFilter filter) {
		Specification<Process> spec = getProcessWhere(filter.getNames(), filter.getVersion(), filter.getState(),
				filter.getDisplayName(), filter.getProcessType(), filter.getOperators());
		return spec;
	}

	private static Specification<Process> getProcessWhere(final String[] names, final Integer version,
			final Integer state, final String displayName, final String type, final String[] creators) {
		return new Specification<Process>() {

			public Predicate toPredicate(Root<Process> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (null != names && names.length != 0) {
					list.add(cb.and(root.get("name").as(String.class).in((Object[])names)));
				}

				if (null != creators && creators.length != 0) {
					list.add(cb.and(root.get("creator").as(String.class).in((Object[])creators)));
				}

				if (null != version) {
					list.add(cb.equal(root.get("version").as(Integer.class), version));
				}

				if (null != state) {
					list.add(cb.equal(root.get("state").as(Integer.class), state));
				}

				if (null != displayName && displayName.length() != 0) {
					list.add(cb.like(root.get("displayName").as(String.class), "%" + displayName + "%"));
				}

				if (null != type && type.length() != 0) {
					list.add(cb.equal(root.get("type").as(String.class), type));
				}

				query.where(list.toArray(new Predicate[list.size()]));
				query.orderBy(cb.asc(root.get("name").as(String.class)));
				return query.getRestriction();
			}

		};
	}

	/**
	 * Surrogate查询条件
	 * 
	 * @param operators
	 * @param operateTime
	 * @param processNames
	 * @return
	 */
	private static Specification<Surrogate> getSurrogatWhere(final String[] operators, final String operateTime,
			final String[] processNames) {
		return new Specification<Surrogate>() {

			public Predicate toPredicate(Root<Surrogate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("state").as(Integer.class), 1));
				if (null != operators && operators.length != 0) {
					list.add(root.get("operator").as(String.class).in((Object[])operators));
				}
				if (null != processNames && processNames.length != 0) {
					list.add(root.get("processName").as(String.class).in((Object[])processNames));
				}

				if (null != operateTime && operateTime.length() != 0) {
					list.add(cb.greaterThanOrEqualTo(root.get("sdate").as(String.class), operateTime));
					list.add(cb.lessThanOrEqualTo(root.get("edate").as(String.class), operateTime));
				}
				query.where(list.toArray(new Predicate[list.size()]));
				query.orderBy(cb.desc(root.get("sdate").as(String.class)));
				return query.getRestriction();
			}
		};
	}

	/**
	 * Task查询条件
	 * 
	 * @param orderId
	 * @param actorIds
	 * @param taskNames
	 * @param sdate
	 * @param edate
	 * @return
	 */
	private static Specification<Task> getTaskWhere(final String orderId, final String[] actorIds,
			final String[] taskNames, final String sdate, final String edate, final String[] ids) {
		Specification<Task> spec = new Specification<Task>() {

			public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (null != ids && ids.length != 0) {
					list.add(cb.not(root.get("id").as(String.class).in((Object[])ids)));
				}
				if (StringHelper.isNotEmpty(orderId)) {
					list.add(cb.equal(root.get("orderId").as(String.class), orderId));
				}
				if (null != actorIds && actorIds.length != 0) {
					SetJoin<Task, TaskActor> depJoin = root.join(root.getModel().getSet("taskActors", TaskActor.class),
							JoinType.LEFT);
					list.add(cb.and(depJoin.get("actorId").as(String.class).in((Object[])actorIds)));
				}
				if (null != taskNames && taskNames.length != 0) {
					list.add(cb.and(root.get("taskName").as(String.class).in((Object[])taskNames)));
				}
				if (null != sdate && sdate.length() != 0) {
					list.add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), sdate));
				}
				if (null != edate && edate.length() != 0) {
					list.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), edate));
				}
				query.where(cb.and(list.toArray(new Predicate[list.size()])));
				query.orderBy(cb.desc(root.get("finishTime").as(String.class)));
				return query.getRestriction();
			}
		};

		return spec;
	}

	/**
	 * HistoryTask 查询条件
	 * 
	 * @param orderId
	 * @param actorIds
	 * @param taskNames
	 * @param sdate
	 * @param edate
	 * @return
	 */
	private static Specification<HistoryTask> getHistoryTaskWhere(final String orderId, final String[] actorIds,
			final String[] taskNames, final String sdate, final String edate) {
		Specification<HistoryTask> spec = new Specification<HistoryTask>() {

			public Predicate toPredicate(Root<HistoryTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (StringHelper.isNotEmpty(orderId)) {
					list.add(cb.equal(root.get("orderId").as(String.class), orderId));
				}
				if (null != actorIds && actorIds.length != 0) {
					SetJoin<HistoryTask, HistoryTaskActor> depJoin = root
							.join(root.getModel().getSet("histActors", HistoryTaskActor.class), JoinType.LEFT);
					list.add(cb.and(depJoin.get("actorId").as(String.class).in((Object[])actorIds)));
				}
				if (null != taskNames && taskNames.length != 0) {
					list.add(cb.and(root.get("taskName").as(String.class).in((Object[])taskNames)));
				}
				if (null != sdate && sdate.length() != 0) {
					list.add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), sdate));
				}
				if (null != edate && edate.length() != 0) {
					list.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), edate));
				}
				query.where(cb.and(list.toArray(new Predicate[list.size()])));
				query.orderBy(cb.desc(root.get("finishTime").as(String.class)));
				return query.getRestriction();
			}
		};

		return spec;
	}

	/**
	 * CCOrder查询条件
	 * 
	 * @param orderId
	 * @param actorIds
	 * @return
	 */
	public static Specification<CCOrder> getCCOrderQueryField(final String orderId, final String... actorIds) {
		Specification<CCOrder> spec = new Specification<CCOrder>() {
			public Predicate toPredicate(Root<CCOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (StringHelper.isNotEmpty(orderId)) {
					list.add(cb.equal(root.get("id").get("orderId").as(String.class), orderId));
				}
				if (null != actorIds && actorIds.length != 0) {
					list.add(cb.and(root.get("id").get("actorId").as(String.class).in((Object[])actorIds)));
				}
				if (list.isEmpty())
					return null;
				else
					return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
		return spec;
	}

	private static Specification<HistoryOrder> getHistoryOrderWhere(final String[] creators, final String[] pnames,
			final String processId, final String ptype, final String pdispName, final String parentId,
			final Integer orderState, final String sdate, final String edate, final String orderNo) {
		Specification<HistoryOrder> spec = new Specification<HistoryOrder>() {

			public Predicate toPredicate(Root<HistoryOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				Join<HistoryOrder, Process> procJoin = root.join(root.getModel().getSingularAttribute("process", Process.class),
						JoinType.LEFT);

				if (null != creators && creators.length != 0) {
					list.add(cb.and(root.get("creator").as(String.class).in((Object[])creators)));
				}

				if (null != pnames && pnames.length != 0) {
					list.add(cb.and(procJoin.get("name").as(String.class).in((Object[])pnames)));
				}

				if (null != processId && processId.length() != 0) {
					list.add(cb.equal(procJoin.get("id").as(String.class), processId));
				}

				if (null != ptype && ptype.length() != 0) {
					list.add(cb.equal(procJoin.get("type").as(String.class), ptype));
				}

				if (null != pdispName && pdispName.length() != 0) {
					list.add(cb.like(procJoin.get("displayName").as(String.class), "%" + pdispName + "%"));
				}

				if (null != parentId && parentId.length() != 0) {
					list.add(cb.equal(root.get("parentId").as(String.class), parentId));
				}

				if (null != orderState) {
					list.add(cb.equal(root.get("orderState").as(Integer.class), orderState));
				}

				if (null != sdate && sdate.length() != 0) {
					list.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), sdate));
				}

				if (null != edate && edate.length() != 0) {
					list.add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), edate));
				}

				if (null != orderNo && orderNo.length() != 0) {
					list.add(cb.equal(root.get("orderNo").as(String.class), orderNo));
				}

				if(list != null && !list.isEmpty()){
					query.where(cb.and(list.toArray(new Predicate[list.size()])));
				}
				query.orderBy(cb.desc(root.get("createTime").as(String.class)));
				return query.getRestriction();
				
			}
		};
		return spec;
	}

	private static Specification<Order> getOrderWhere(final String[] creators, final String[] pnames,
			final String processId, final String ptype, final String pdispName, final String parentId,
			final String[] ids, final String sdate, final String edate, final String orderNo) {
		Specification<Order> spec = new Specification<Order>() {

			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				SetJoin<Order, Process> procJoin = root.join(root.getModel().getSet("process", Process.class),
						JoinType.LEFT);

				if (null != creators && creators.length != 0) {
					list.add(cb.and(root.get("creator").as(String.class).in((Object[])creators)));
				}

				if (null != pnames && pnames.length != 0) {
					list.add(cb.and(procJoin.get("name").as(String.class).in((Object[])creators)));
				}

				if (null != processId && processId.length() != 0) {
					list.add(cb.equal(procJoin.get("id").as(String.class), processId));
				}

				if (null != ptype && ptype.length() != 0) {
					list.add(cb.equal(procJoin.get("type").as(String.class), ptype));
				}

				if (null != pdispName && pdispName.length() != 0) {
					list.add(cb.like(procJoin.get("displayName").as(String.class), "%" + pdispName + "%"));
				}

				if (null != parentId && parentId.length() != 0) {
					list.add(cb.equal(root.get("parentId").as(String.class), parentId));
				}

				if (null != ids && ids.length != 0) {
					list.add(cb.not(root.get("id").as(String.class).in((Object[])ids)));
				}

				if (null != sdate && sdate.length() != 0) {
					list.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), sdate));
				}

				if (null != edate && edate.length() != 0) {
					list.add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), edate));
				}

				if (null != orderNo && orderNo.length() != 0) {
					list.add(cb.equal(root.get("orderNo").as(String.class), orderNo));
				}

				query.where(cb.and(list.toArray(new Predicate[list.size()])));
				query.orderBy(cb.desc(root.get("createTime").as(String.class)));
				return query.getRestriction();
			}
		};
		return spec;
	}

	/**
	 * 工作项（待办、已处理任务的查询结果实体）的SQL
	 * 
	 * @param filter
	 * @return
	 */
	public static String getWorkItemsSQL(QueryFilter filter) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" select distinct o.process_Id, t.order_Id, t.id as id, t.id as task_Id, p.display_Name as process_Name, p.instance_Url, o.parent_Id, o.creator, ");
		sql.append(
				" o.create_Time as order_Create_Time, o.expire_Time as order_Expire_Time, o.order_No, o.variable as order_Variable, ");
		sql.append(
				" t.display_Name as task_Name, t.task_Name as task_Key, t.task_Type, t.perform_Type, t.operator, t.action_Url, ");
		sql.append(
				" t.create_Time as task_Create_Time, t.finish_Time as task_End_Time, t.expire_Time as task_Expire_Time, t.variable as task_Variable ");
		sql.append(" from wf_task t ");
		sql.append(" left join wf_order o on t.order_id = o.id ");
		sql.append(" left join wf_task_actor ta on ta.task_id=t.id ");
		sql.append(" left join wf_process p on p.id = o.process_id ");
		sql.append(" where 1=1 ");

		/**
		 * 查询条件构造sql的where条件
		 */
		//List<Object> paramList = new ArrayList<Object>();
		if (filter.getOperators() != null && filter.getOperators().length > 0) {
			sql.append(" and ta.actor_Id in (");
			for (String actor : filter.getOperators()) {
				sql.append("'"+actor+"'").append(",");
				//paramList.add(actor);
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(") ");
		}

		if (StringHelper.isNotEmpty(filter.getProcessId())) {
			sql.append(" and o.process_Id = '").append(filter.getProcessId()).append("' ");
			//paramList.add(filter.getProcessId());
		}
		if (StringHelper.isNotEmpty(filter.getDisplayName())) {
			sql.append(" and p.display_Name like %'").append(filter.getDisplayName()).append("'% ");
			//paramList.add("%" + filter.getDisplayName() + "%");
		}
		if (StringHelper.isNotEmpty(filter.getParentId())) {
			sql.append(" and o.parent_Id = '").append(filter.getParentId()).append(", ");
			//paramList.add(filter.getParentId());
		}
		if (StringHelper.isNotEmpty(filter.getOrderId())) {
			sql.append(" and t.order_id = '").append(filter.getOrderId()).append("' ");
			//paramList.add(filter.getOrderId());
		}
		if (filter.getNames() != null && filter.getNames().length > 0) {
			sql.append(" and t.task_Name in (");
			for (int i = 0; i < filter.getNames().length; i++) {
				sql.append("'"+filter.getNames()[i]).append("',");
				//paramList.add(filter.getNames()[i]);
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(") ");
		}
		if (filter.getTaskType() != null) {
			sql.append(" and t.task_Type = '").append(filter.getTaskType()).append("' ");
			//paramList.add(filter.getTaskType());
		}
		if (filter.getPerformType() != null) {
			sql.append(" and t.perform_Type = '").append(filter.getPerformType()).append("' ");
//			paramList.add(filter.getPerformType());
		}
		if (StringHelper.isNotEmpty(filter.getCreateTimeStart())) {
			sql.append(" and t.create_Time >= '").append(filter.getCreateTimeStart()).append("' ");
//			paramList.add(filter.getCreateTimeStart());
		}
		if (StringHelper.isNotEmpty(filter.getCreateTimeEnd())) {
			sql.append(" and t.create_Time <= '").append(filter.getCreateTimeEnd()).append("' ");
//			paramList.add(filter.getCreateTimeEnd());
		}
		if (!filter.isOrderBySetted()) {
			filter.setOrder(QueryFilter.DESC);
			filter.setOrderBy("t.create_Time");
		}
		return sql.toString();
	}

	public static String getCCWorksSQL(QueryFilter filter) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" select id,process_Id,order_State,priority,cc.creator,cc.create_Time,end_Time,parent_Id,expire_Time,order_No,variable ");
		sql.append(" from wf_cc_order cc ");
		sql.append(" left join wf_hist_order o on cc.order_id = o.id ");
		sql.append(" where 1=1 ");

		/**
		 * 查询条件构造sql的where条件
		 */
//		List<Object> paramList = new ArrayList<Object>();
		if (filter.getOperators() != null && filter.getOperators().length > 0) {
			sql.append(" and cc.actor_Id in(");
			for (int i = 0; i < filter.getOperators().length; i++) {
				sql.append("'"+filter.getOperators()[i]).append("',");
//				paramList.add(filter.getOperators()[i]);
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(") ");
		}
		if (filter.getState() != null) {
			sql.append(" and cc.status ='").append(filter.getState()).append("' ");
//			paramList.add(filter.getState());
		}
		if (StringHelper.isNotEmpty(filter.getProcessId())) {
			sql.append(" and process_Id = '").append(filter.getProcessId()).append("' ");
//			paramList.add(filter.getProcessId());
		}
		if (StringHelper.isNotEmpty(filter.getParentId())) {
			sql.append(" and parent_Id = '").append(filter.getParentId()).append("' ");
//			paramList.add(filter.getParentId());
		}
		if (StringHelper.isNotEmpty(filter.getCreateTimeStart())) {
			sql.append(" and cc.create_Time >= '").append(filter.getCreateTimeStart()).append("' ");
//			paramList.add(filter.getCreateTimeStart());
		}
		if (StringHelper.isNotEmpty(filter.getCreateTimeEnd())) {
			sql.append(" and cc.create_Time <= '").append(filter.getCreateTimeEnd()).append("' ");
//			paramList.add(filter.getCreateTimeEnd());
		}
		if (StringHelper.isNotEmpty(filter.getOrderNo())) {
			sql.append(" and order_No = '").append(filter.getOrderNo()).append("' ");
//			paramList.add(filter.getOrderNo());
		}
		if (!filter.isOrderBySetted()) {
			filter.setOrder(QueryFilter.DESC);
			filter.setOrderBy("cc.create_Time");
		}
		return sql.toString();
	}

	public static String getHistoryWorkItemsSQL(QueryFilter filter) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" select distinct o.process_Id, t.order_Id, t.id as id, t.id as task_Id, p.display_Name as process_Name, p.instance_Url, o.parent_Id, o.creator, ");
		sql.append(
				" o.create_Time as order_Create_Time, o.expire_Time as order_Expire_Time, o.order_No, o.variable as order_Variable, ");
		sql.append(
				" t.display_Name as task_Name, t.task_Name as task_Key, t.task_Type, t.perform_Type,t.operator, t.action_Url, ");
		sql.append(
				" t.create_Time as task_Create_Time, t.finish_Time as task_End_Time, t.expire_Time as task_Expire_Time, t.variable as task_Variable ");
		sql.append(" from wf_hist_task t ");
		sql.append(" left join wf_hist_order o on t.order_id = o.id ");
		sql.append(" left join wf_hist_task_actor ta on ta.task_id=t.id ");
		sql.append(" left join wf_process p on p.id = o.process_id ");
		sql.append(" where 1=1 ");
		/**
		 * 查询条件构造sql的where条件
		 */
//		List<Object> paramList = new ArrayList<Object>();
		if (filter.getOperators() != null && filter.getOperators().length > 0) {
			sql.append(" and ta.actor_Id in (");
			for (String actor : filter.getOperators()) {
				sql.append("'"+actor).append("',");
//				paramList.add(actor);
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(") ");
		}

		if (StringHelper.isNotEmpty(filter.getProcessId())) {
			sql.append(" and o.process_Id = '").append(filter.getProcessId()).append("' ");
//			paramList.add(filter.getProcessId());
		}
		if (StringHelper.isNotEmpty(filter.getDisplayName())) {
			sql.append(" and p.display_Name like %'").append(filter.getDisplayName()).append("'% ");
//			paramList.add("%" + filter.getDisplayName() + "%");
		}
		if (StringHelper.isNotEmpty(filter.getParentId())) {
			sql.append(" and o.parent_Id = '").append(filter.getParentId()).append("' ");
//			paramList.add(filter.getParentId());
		}
		if (StringHelper.isNotEmpty(filter.getOrderId())) {
			sql.append(" and t.order_id = '").append(filter.getOrder()).append("' ");
//			paramList.add(filter.getOrderId());
		}
		if (filter.getNames() != null && filter.getNames().length > 0) {
			sql.append(" and t.task_Name in (");
			for (int i = 0; i < filter.getNames().length; i++) {
				sql.append("'"+filter.getNames()[i]).append("',");
//				paramList.add(filter.getNames()[i]);
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(") ");
		}
		if (filter.getTaskType() != null) {
			sql.append(" and t.task_Type = '").append(filter.getTaskType()).append("' ");
//			paramList.add(filter.getTaskType());
		}
		if (filter.getPerformType() != null) {
			sql.append(" and t.perform_Type = '").append(filter.getPerformType()).append("' ");
//			paramList.add(filter.getPerformType());
		}
		if (StringHelper.isNotEmpty(filter.getCreateTimeStart())) {
			sql.append(" and t.create_Time >= '").append(filter.getCreateTimeStart()).append("' ");
//			paramList.add(filter.getCreateTimeStart());
		}
		if (StringHelper.isNotEmpty(filter.getCreateTimeEnd())) {
			sql.append(" and t.create_Time <= '").append(filter.getCreateTimeEnd()).append("' ");
//			paramList.add(filter.getCreateTimeEnd());
		}

		if (!filter.isOrderBySetted()) {
			filter.setOrder(QueryFilter.DESC);
			filter.setOrderBy("t.create_Time");
		}
		return sql.toString();
	}
}
