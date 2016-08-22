package com.hhh.fund.usercenter.dao;

import java.util.List;

import com.hhh.fund.usercenter.entity.Dictionary;
import com.hhh.fund.util.SpecificationsRepository;

public interface DictionaryDao extends SpecificationsRepository<Dictionary, Integer> {

	
	public List<Dictionary> findByCategory(String category);
}
