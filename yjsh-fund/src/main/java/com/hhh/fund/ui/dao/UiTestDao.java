package com.hhh.fund.ui.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hhh.fund.ui.entity.UiTest;
import com.hhh.fund.util.SpecificationsRepository;

@Repository
public interface UiTestDao extends PagingAndSortingRepository<UiTest, String>,JpaSpecificationExecutor<UiTest> {
	public UiTest findById(String id);
	public Page<UiTest> findAll(Pageable pageable);
}
