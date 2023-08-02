package com.jbpm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jbpm.entity.UserTask;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepo  extends JpaRepository<UserTask, Long> {
	
	
	@Query(value = "select * from UserTask  where componentId = :id ", nativeQuery = true)
	public UserTask getCopyUser(String id);
	
	@Query(value =" SELECT * FROM UserTask n1 WHERE n1.processName = :processName  AND n1.task= :task " , nativeQuery = true)
	public  UserTask getUserTaskDetails(@Param("task") String task,@Param("processName") String processName);
	

}
