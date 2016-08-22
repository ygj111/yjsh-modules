package com.hhh.fund.usercenter.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hhh.fund.usercenter.entity.Company;
import com.hhh.fund.util.SpecificationsRepository;

public interface CompanyDao extends SpecificationsRepository<Company, String> {
	public Company findByCustomerId(String customerId);
	
	@Modifying
	@Query("update Company set customerId=:customerId where id=:id")
	public void updateCustomerId(@Param("customerId") String customerId,@Param("id") String id);
}
