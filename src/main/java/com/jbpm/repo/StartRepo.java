package com.jbpm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jbpm.entity.StartTask;


@Repository
public interface StartRepo extends JpaRepository<StartTask, Long> {
	
	@Query(value = "select *  from StartTask   where processName = :processName  ", nativeQuery = true)
	public StartTask getfindbyProcessName(String processName);
	

}
