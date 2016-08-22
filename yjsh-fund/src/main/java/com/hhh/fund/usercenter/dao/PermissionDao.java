package com.hhh.fund.usercenter.dao;

import java.util.List;
import java.util.Set;

import com.hhh.fund.usercenter.entity.Permission;
import com.hhh.fund.util.SpecificationsRepository;

public interface PermissionDao extends SpecificationsRepository<Permission, String> {

	public Long deleteByRoleId(String roleId);
	
	public long deleteByResId(String resId);
	
	public List<Permission> findByRoleId(String roleId);
	
	public List<Permission> findByRoleIdIn(Set<String> roleIds);
}
