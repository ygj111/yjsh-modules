package com.hhh.fund.usercenter.dao;

import com.hhh.fund.usercenter.entity.UserOfDepartment;
import com.hhh.fund.util.SpecificationsRepository;

public interface UserOfDepartmentDao extends SpecificationsRepository<UserOfDepartment, Integer> {
	
	public Long deleteByAccountId(String accountId);
	
	public Long deleteByAccountIdAndDepartId(String accountId, String departId);
	
	public Long deleteByDepartId(String departId);
}
