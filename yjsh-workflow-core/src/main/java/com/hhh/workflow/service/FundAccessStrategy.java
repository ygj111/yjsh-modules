package com.hhh.workflow.service;

import java.util.List;

import org.snaker.engine.impl.GeneralAccessStrategy;
import org.springframework.stereotype.Component;

import com.hhh.security.util.ShiroUtils;


/**
 * 自定义访问策略，根据操作人获取其所有组集合（部门、角色、权限）
 * @author yuqs
 * @since 0.1
 */
@Component
public class FundAccessStrategy extends GeneralAccessStrategy {
	protected List<String> ensureGroup(String operator) {
		return ShiroUtils.getRoles();
	}
}
