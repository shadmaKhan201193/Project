package com.jbpm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jbpm.entity.RuleTask;

@Repository
public interface RuleRepo extends JpaRepository<RuleTask,Long>{

	
	@Query(value = "select *  from RuleTask   where task = :task AND processName = :processName  ", nativeQuery = true)
	public RuleTask getRuleDetails(String task,String processName);
	
	
}
