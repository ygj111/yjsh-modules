package com.hhh.fund.usercenter.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import com.hhh.fund.usercenter.entity.Resources;
import com.hhh.fund.util.SpecificationsRepository;

public interface ResourcesDao extends SpecificationsRepository<Resources, String> {

	@Query("select r from Resources r, Permission p where r.id=p.resId and p.roleId in ?1")
	public List<Resources> findByRoleIds(Set<String> roleids);
}
