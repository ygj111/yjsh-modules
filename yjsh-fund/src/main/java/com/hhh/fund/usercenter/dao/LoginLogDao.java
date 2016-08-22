package com.hhh.fund.usercenter.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import com.hhh.fund.usercenter.entity.LoginLog;
import com.hhh.fund.util.SpecificationsRepository;

public interface LoginLogDao extends SpecificationsRepository<LoginLog, String> {

	@Query("delete from LoginLog log where log.loginTime < ?1")
	public void deleteByLoginTime(Date time);
}
