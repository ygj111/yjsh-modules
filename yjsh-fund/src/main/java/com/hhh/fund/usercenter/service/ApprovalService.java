package com.hhh.fund.usercenter.service;

import java.util.List;

import com.hhh.fund.usercenter.entity.Approval;

/**
 * 流程公共审批服务
 * @author 3hhjj
 *
 */
public interface ApprovalService {
	
	/**
	 * 保存审批数据
	 * @param entity
	 * @return
	 */
	public boolean saveApproval(Approval entity);
	
	/**
	 * 根据实例和任务ID取审批数据列表
	 * @param orderId
	 * @param taskId
	 * @return
	 */
	public List<Approval> findApprovalByOrderIdAndTaskId(String orderId, String taskId);
	
	/**
	 * 根据实例ID取审批数据
	 * @param orderId
	 * @return
	 */
	public List<Approval> findApprovalByOrderId(String orderId);
}
