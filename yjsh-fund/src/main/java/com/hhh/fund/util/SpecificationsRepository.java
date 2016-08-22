package com.hhh.fund.util;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 动态条件查询接口，继承了CURD操作
 * @author 3hhjj
 *
 * @param <T>
 * @param <ID>
 */

@NoRepositoryBean
public interface SpecificationsRepository<T, ID extends Serializable> extends CrudRepository<T, ID>, JpaSpecificationExecutor<T> {


}
