package com.hhh.fund.usercenter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hhh.fund.usercenter.entity.Account;
import com.hhh.fund.util.SpecificationsRepository;

public interface AccountDao extends SpecificationsRepository<Account, String> {

	public Account findByLoginName(String username);
	
	public Account findByKeyId(String keyId);
	
	/**
	 * 取部门下所有的用户
	 * @param departid
	 * @return
	 */
	@Query("select u from Account u, UserOfDepartment d where u.id = d.accountId and d.departId=?1")
	public List<Account> findByDepartmentInUser(String departid);
	
//	public Account findByCustomerId(String customerId);
	
	@Modifying
	@Query("update Account set customerId=:customerId where id=:accountId")
	public void updateCustomerIdByAccountId(String accountId,String customerId);
}
