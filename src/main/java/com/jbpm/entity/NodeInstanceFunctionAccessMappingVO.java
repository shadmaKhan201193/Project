package com.jbpm.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NodeInstanceFunctionAccessMappingVO {

	private String nextNode;
	private String nodeName;
	private String nodeType;
	private String previousNode;
	private String processName;
	private String taskId;
	private String workStatus;
	private String mapvalue;
	private Long nodeId;
	private Date logDate;
	 private String owner;
	 private String parameters;
	 private  UUID uuid;

	



	
	public NodeInstanceFunctionAccessMappingVO(String nextNode, String nodeName, String nodeType, String previousNode,
			String processName, String taskId, String workStatus, String mapvalue, Long nodeId, Date logDate,
			String owner, String parameters, UUID uuid) {
		super();
		this.nextNode = nextNode;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.previousNode = previousNode;
		this.processName = processName;
		this.taskId = taskId;
		this.workStatus = workStatus;
		this.mapvalue = mapvalue;
		this.nodeId = nodeId;
		this.logDate = logDate;
		this.owner = owner;
		this.parameters = parameters;
		this.uuid = uuid;
	}

	public NodeInstanceFunctionAccessMappingVO() {
		super();
	}

	public String getNextNode() {
		return nextNode;
	}

	public void setNextNode(String nextNode) {
		this.nextNode = nextNode;
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

	public String getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(String previousNode) {
		this.previousNode = previousNode;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getMapvalue() {
		return mapvalue;
	}

	public void setMapvalue(String mapvalue) {
		this.mapvalue = mapvalue;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
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
