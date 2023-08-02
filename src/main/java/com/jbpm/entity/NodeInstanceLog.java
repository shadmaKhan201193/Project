package com.jbpm.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.ConstructorResult;
import javax.persistence.Column;
import javax.persistence.ColumnResult;

@Entity

@NamedNativeQueries({
		@NamedNativeQuery(name = "NodeInstanceLog.getAll_MSSQL", query = "SELECT n1.nextNode, n1.nodeName, n1.nodeType, n1.previousNode,n1.nodeId, n1.processName,n1.owner,n1.logDate, n1.taskId, n1.workStatus ,n1.mapvalue,n1.parameters,n1.uuid "
				+ "FROM NodeInstanceLog n1 "
				+ "WHERE n1.processName = :processName AND n1.previousNode IS NULL ", resultSetMapping = "NodeInstanceLogMapping"),
		@NamedNativeQuery(name = "NodeInstanceLog.getAll_MSSQLNew", query = "SELECT n1.nextNode, n1.nodeName, n1.nodeType, n1.previousNode,n1.nodeId,n1.owner,n1.processName,n1.logDate, n1.taskId, n1.workStatus,n1.mapvalue,n1.parameters,n1.uuid "
				+ "FROM NodeInstanceLog n1 "
				+ "WHERE n1.processName = :processName  AND n1.taskId= :taskId ", resultSetMapping = "NodeInstanceLogMappingNew"),

		@NamedNativeQuery(name = "NodeInstanceLog.getAll_iftask", query = "SELECT n1.nextNode, n1.nodeName, n1.nodeType, n1.previousNode,n1.nodeId,n1.owner,n1.processName,n1.logDate, n1.taskId, n1.workStatus,n1.mapvalue,n1.parameters,n1.uuid "
				+ "FROM NodeInstanceLog n1 "
				+ "WHERE n1.processName = :processName  AND (n1.taskId = :task1 OR n1.taskId = :task2 )", resultSetMapping = "NodeInstanceLogMappingNew"),

		@NamedNativeQuery(name = "NodeInstanceLog.getAllFoParallelTask", query = "SELECT n1.nextNode, n1.nodeName, n1.nodeType, n1.previousNode,n1.nodeId,n1.owner,n1.processName,n1.logDate, n1.taskId, n1.workStatus,n1.mapvalue,n1.parameters,n1.uuid "
				+ "FROM NodeInstanceLog n1 "
				+ "WHERE n1.taskId  IN :next AND  n1.processName = :processName", resultSetMapping = "NodeInstanceLogMappingNew")

})

@SqlResultSetMappings({
		@SqlResultSetMapping(name = "NodeInstanceLogMapping", classes = @ConstructorResult(targetClass = NodeInstanceFunctionAccessMappingVO.class, columns = {
				@ColumnResult(name = "nextNode"), @ColumnResult(name = "nodeName"), @ColumnResult(name = "nodeType"),
				@ColumnResult(name = "previousNode"), @ColumnResult(name = "processName"),
				@ColumnResult(name = "taskId"), @ColumnResult(name = "workStatus"), @ColumnResult(name = "mapvalue"),
				@ColumnResult(name = "nodeId", type = Long.class), @ColumnResult(name = "logDate", type = Date.class),
				@ColumnResult(name = "owner"),
				@ColumnResult(name = "parameters"),
				@ColumnResult(name = "uuid"),

		})),
		@SqlResultSetMapping(name = "NodeInstanceLogMappingNew", classes = @ConstructorResult(targetClass = NodeInstanceFunctionAccessMappingVO.class, columns = {
				@ColumnResult(name = "nextNode"), @ColumnResult(name = "nodeName"), @ColumnResult(name = "nodeType"),
				@ColumnResult(name = "previousNode"), @ColumnResult(name = "processName"),
				@ColumnResult(name = "taskId"), @ColumnResult(name = "workStatus"), @ColumnResult(name = "mapvalue"),
				@ColumnResult(name = "nodeId", type = Long.class), @ColumnResult(name = "logDate", type = Date.class),
				@ColumnResult(name = "owner"),
				@ColumnResult(name = "parameters"),
				@ColumnResult(name = "uuid"),
			

		})) })
public class NodeInstanceLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@Column(length = 10, updatable = false)
	private Long nodeId;

	private String previousNode;
	private String nextNode;
	private String processName;
	private String nodeName;
	private String nodeType;
	private String taskId;
	private String workStatus;
	private String mapvalue;
	private Date logDate;
	private String owner;
	


	private Date createdTaskDate;

	private Date startTaskDate;
	private Date endTaskDate;

	private String parameters;
	private  String uuid;
	
	

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(String previousNode) {
		this.previousNode = previousNode;
	}

	public String getNextNode() {
		return nextNode;
	}

	public void setNextNode(String nextNode) {
		this.nextNode = nextNode;
	}

	public String getMapvalue() {
		return mapvalue;
	}

	public void setMapvalue(String mapvalue) {
		this.mapvalue = mapvalue;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getCreatedTaskDate() {
		return createdTaskDate;
	}

	public void setCreatedTaskDate(Date createdTaskDate) {
		this.createdTaskDate = createdTaskDate;
	}

	public Date getStartTaskDate() {
		return startTaskDate;
	}

	public void setStartTaskDate(Date startTaskDate) {
		this.startTaskDate = startTaskDate;
	}

	public Date getEndTaskDate() {
		return endTaskDate;
	}

	public void setEndTaskDate(Date endTaskDate) {
		this.endTaskDate = endTaskDate;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setParameters(Map<String, String> parameters) {
		 try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            this.parameters = objectMapper.writeValueAsString(parameters);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public Map<String, String> getParameters() {
	  
	    	 try {
	             ObjectMapper objectMapper = new ObjectMapper();
	             return objectMapper.readValue(parameters, HashMap.class);
	         } catch (Exception e) {
	             e.printStackTrace();
	             return null;
	         }
	    }

}
