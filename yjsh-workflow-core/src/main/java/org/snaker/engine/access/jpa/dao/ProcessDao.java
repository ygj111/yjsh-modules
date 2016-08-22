package org.snaker.engine.access.jpa.dao;

import org.snaker.engine.entity.Process;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hhh.fund.util.SpecificationsRepository;

public interface ProcessDao extends SpecificationsRepository<Process, String> {
	
	/**
	 * 根据流程名称查询最近的版本号
	 * @param name 流程名称
	 * @return Integer 流程定义版本号
	 */
	@Query("select max(p.version) from Process p where p.name=:name")
	public Integer findByName(@Param("name") String name);
	
	@Modifying
	@Query("update Process u set u.type = :type where u.id = :id")
	public int setProcessType(@Param("id")String id, @Param("type")String type);
	
}
