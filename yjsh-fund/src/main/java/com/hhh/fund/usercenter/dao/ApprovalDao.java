package com.hhh.fund.usercenter.dao;

import java.util.List;

import com.hhh.fund.usercenter.entity.Approval;
import com.hhh.fund.util.SpecificationsRepository;

public interface ApprovalDao extends SpecificationsRepository<Approval, Integer> {

	List<Approval> findByOrderIdAndTaskId(String orderId, String taskId);
	
	List<Approval> findByOrderId(String orderId);
}
