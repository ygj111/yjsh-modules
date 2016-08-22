package com.hhh.fund.usercenter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.hhh.fund.usercenter.entity.Department;
import com.hhh.fund.util.SpecificationsRepository;

public interface DepartmentDao extends SpecificationsRepository<Department, String> {
	
	/**
	 * 取用户所在的部门
	 * @param accountId
	 * @return
	 */
	@Query("select d from Department d, UserOfDepartment ud where ud.accountId=?1 and ud.departId=d.id")
	List<Department> findByAccountId(String accountId);
}
