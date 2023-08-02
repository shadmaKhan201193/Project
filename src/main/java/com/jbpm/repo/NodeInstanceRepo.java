package com.jbpm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jbpm.entity.NodeILogListVO;
import com.jbpm.entity.NodeInstanceFunctionAccessMappingVO;
import com.jbpm.entity.NodeInstanceLog;


@Repository
public interface NodeInstanceRepo extends JpaRepository<NodeInstanceLog,Long>{
	
	
	
	 @Query(nativeQuery = true, name = "NodeInstanceLog.getAll_MSSQL")
	    NodeInstanceFunctionAccessMappingVO getAllNodeInstanceLogs(@Param("processName") String processName);
	    
	    @Query(nativeQuery = true, name = "NodeInstanceLog.getAll_MSSQLNew")
	    List<NodeInstanceFunctionAccessMappingVO> getAllNodeInstanceLogsNew(@Param("taskId") String taskId,@Param("processName") String processName);
	    
	    public NodeInstanceLog findByTaskIdAndProcessNameAndUuid(String task,String processName,String uuid);  	    
	    
	    @Query(nativeQuery = true, name = "NodeInstanceLog.getAll_iftask")
	    List<NodeInstanceFunctionAccessMappingVO> getAllIfTask(@Param("task1") String task1,@Param("task2") String task2,@Param("processName") String processName);
	    
	    
	    @Query(nativeQuery = true, name = "NodeInstanceLog.getAllFoParallelTask")
	    List<NodeInstanceFunctionAccessMappingVO> getAllFoParallelTask(@Param("next") List<String> next,@Param("processName") String processName);

	    @Query(value = "SELECT n1.nextNode, n1.nodeName, n1.nodeType, n1.previousNode,n1.nodeId,n1.owner,n1.processName,n1.logDate,n1.createdTaskDate,n1.endTaskDate ,n1.startTaskDate, n1.taskId, n1.workStatus,n1.mapvalue FROM NodeInstanceLog n1 WHERE n1.processName = :processName  AND n1.taskId = :task ", nativeQuery = true)
	    NodeInstanceLog getfindAllByTask(@Param("task") String task,@Param("processName") String processName);
	    
	    
//	    @Query(value = "SELECT * FROM NodeInstanceLog WHERE nodeType = :nodeType ORDER BY createdTaskDate DESC LIMIT 1", nativeQuery = true)
//	    NodeInstanceLog getfindLastAssignUser(@Param("nodeType") String nodeType);
	    
	    @Query(value = "SELECT * FROM NodeInstanceLog WHERE owner  IN :owner and nodeType = :nodeType ORDER BY createdTaskDate DESC LIMIT 1", nativeQuery = true)
	    NodeInstanceLog getfindLastAssignUser(@Param("owner") List<String>owner,@Param("nodeType") String nodeType);
	    
	    
	    
//	    @Query(value = "select owner, count(owner) from NodeInstanceLog where nodeType = :nodeType and workStatus <> 'completed' group by owner ", nativeQuery = true)
//	    List<Object[]> getfindleastLoad(@Param("nodeType") String nodeType);
	    
	    @Query(value = "select owner, count(owner) from NodeInstanceLog where  owner  IN :owner and nodeType = :nodeType and workStatus <> 'completed' group by owner ", nativeQuery = true)
	    List<Object[]> getfindleastLoad(@Param("owner") List<String>owner,@Param("nodeType") String nodeType);
	    
	    
	    @Query(value = "SELECT *   FROM NodeInstanceLog n1 WHERE n1.processName = :processName  AND  n1.uuid = :uuid ", nativeQuery = true)
	    List <NodeInstanceLog> getallRecordBYUUID(String processName,String uuid);
	    
	   
	    
	    


}
