package com.jbpm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jbpm.entity.ScriptTask;


@Repository
public interface ScriptRepo  extends JpaRepository<ScriptTask, Long> {

	
	@Query(value = "select *  from ScriptTask   where componentId = :id  ", nativeQuery = true)
	public ScriptTask getCopyScript(String id);
	
	
}
