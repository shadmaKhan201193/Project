package com.jbpm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jbpm.entity.MiddInstanceFuncAccessMappingVO;
import com.jbpm.entity.Middleware;

@Repository
public interface MiddRepo extends JpaRepository<Middleware,Long> {

	 @Query(value =" SELECT * FROM Middleware n1 WHERE n1.processName = :processName AND n1.previous IS NULL" , nativeQuery = true)
	 Middleware getAllbypreviousNull(@Param("processName") String processName);
	 
		@Query(value =" SELECT * FROM Middleware n1 WHERE n1.processName = :processName  AND n1.task= :task " , nativeQuery = true)
        List<Middleware> getAlltaskAndProcessAsList(@Param("task") String task,@Param("processName") String processName);

		@Query(value =" SELECT * FROM Middleware n1 WHERE n1.processName = :processName  AND (n1.task = :task1 OR n1.task = :task2 ) " , nativeQuery = true)
	    List<Middleware> getAllIfTask(@Param("task1") String task1,@Param("task2") String task2,@Param("processName") String processName);
	

	   public Middleware findByTaskAndProcessName(String task,String processName); 
	   
	   
	   @Query(value =" SELECT * FROM Middleware n1 WHERE n1.task  IN :next AND  n1.processName = :processName " , nativeQuery = true)
	    List<Middleware> getAllFoParallelTask(@Param("next") List<String> next,@Param("processName") String processName);
		
		
	   
	   
		
		

}
