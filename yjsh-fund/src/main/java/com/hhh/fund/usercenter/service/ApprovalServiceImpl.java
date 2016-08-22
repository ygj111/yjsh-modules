package com.hhh.fund.usercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hhh.fund.usercenter.dao.ApprovalDao;
import com.hhh.fund.usercenter.entity.Approval;

public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	private ApprovalDao approvalDao;
	
	@Override
	public List<Approval> findApprovalByOrderIdAndTaskId(String orderId, String taskId) {
		return approvalDao.findByOrderIdAndTaskId(orderId, taskId);
	}

	@Override
	public List<Approval> findApprovalByOrderId(String orderId) {
		return approvalDao.findByOrderId(orderId);
	}

	@Override
	public boolean saveApproval(Approval entity) {
		approvalDao.save(entity);
		return true;
	}

}
