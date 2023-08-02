package com.jbpm.ServiceImpl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbpm.entity.ConditionsVo;
import com.jbpm.entity.Gateways;
import com.jbpm.entity.Middleware;
import com.jbpm.entity.NodeInstanceLog;
import com.jbpm.repo.NodeInstanceRepo;

@Component
public class NodeInstanceLogSave {

	@Autowired
	NodeInstanceRepo NodeInstanceRepo;

	Date dt = new Date();

	
	//this method for copy object only
	public void setlog(ConditionsVo con, String name, String mapvalue, String owner, String processName,Map<String, String> parameters) {

		NodeInstanceLog NodeInstanceLog = new NodeInstanceLog();

		NodeInstanceLog.setPreviousNode(con.getPrevious());
		NodeInstanceLog.setNextNode(con.getNext());
		NodeInstanceLog.setNodeName(name);
		NodeInstanceLog.setNodeType(con.getEventType());
		NodeInstanceLog.setProcessName(processName);
		NodeInstanceLog.setTaskId(con.getTask());
		NodeInstanceLog.setMapvalue(mapvalue);
		NodeInstanceLog.setParameters(parameters);
		NodeInstanceLog.setWorkStatus("pending");
		NodeInstanceLog.setLogDate(dt);
		NodeInstanceLog.setCreatedTaskDate(dt);
		NodeInstanceLog.setOwner(owner);
		NodeInstanceRepo.save(NodeInstanceLog);

	}

	
	//for task calll
	public void setlog2(ConditionsVo con, String processName,String owner) {
		NodeInstanceLog NodeInstanceLog = new NodeInstanceLog();
		NodeInstanceLog.setPreviousNode(con.getPrevious());
		NodeInstanceLog.setNextNode(con.getNext());
		NodeInstanceLog.setNodeName(con.getName());
		NodeInstanceLog.setNodeType(con.getEventType());
		NodeInstanceLog.setProcessName(processName);
		NodeInstanceLog.setTaskId(con.getTask());
		NodeInstanceLog.setMapvalue(con.getMapvalue());
		NodeInstanceLog.setParameters(con.getParameters());
		NodeInstanceLog.setOwner(con.getOwner());
		NodeInstanceLog.setWorkStatus("pending");
		NodeInstanceLog.setLogDate(dt);
		NodeInstanceLog.setCreatedTaskDate(dt);
		NodeInstanceLog.setOwner(owner);
		NodeInstanceRepo.save(NodeInstanceLog);
		
	}

	
	//for gateway call
	public void setlog2(Gateways conditions1, String processName) {
		NodeInstanceLog NodeInstanceLog = new NodeInstanceLog();

		NodeInstanceLog.setPreviousNode(conditions1.getPrevious());
		NodeInstanceLog.setNextNode(conditions1.getNext());
		NodeInstanceLog.setNodeName(conditions1.getName());
		NodeInstanceLog.setNodeType(conditions1.getEventType());
		NodeInstanceLog.setProcessName(processName);
		NodeInstanceLog.setTaskId(conditions1.getTask());
		NodeInstanceLog.setMapvalue(conditions1.getMapvalue());
		NodeInstanceLog.setParameters(conditions1.getParameters());
		NodeInstanceLog.setWorkStatus("pending");
		NodeInstanceLog.setLogDate(dt);
		NodeInstanceLog.setCreatedTaskDate(dt);
		NodeInstanceRepo.save(NodeInstanceLog);
		
	}
	
	
	
	////------------------------------------
	
	public void setlog1(Middleware conditions1, String processName,String status,String mapValue, String uuid) {
		
		
		
		NodeInstanceLog NodeInstanceLog = new NodeInstanceLog();

		NodeInstanceLog.setPreviousNode(conditions1.getPrevious());
		NodeInstanceLog.setNextNode(conditions1.getNext());
		NodeInstanceLog.setNodeName(conditions1.getName());
		NodeInstanceLog.setNodeType(conditions1.getEventType());
		NodeInstanceLog.setProcessName(processName);
		NodeInstanceLog.setTaskId(conditions1.getTask());
		NodeInstanceLog.setWorkStatus(status);
		NodeInstanceLog.setMapvalue(mapValue);
		NodeInstanceLog.setUuid(uuid);
		//NodeInstanceLog.setMapvalue(conditions1.getMapvalue());
		//NodeInstanceLog.setParameters(conditions1.getParameters());
		NodeInstanceLog.setStartTaskDate(dt);
		NodeInstanceLog.setEndTaskDate(dt);
		NodeInstanceLog.setLogDate(dt);
		NodeInstanceLog.setCreatedTaskDate(dt);
		NodeInstanceRepo.save(NodeInstanceLog);
		
	}
	
	public void setlog2(NodeInstanceLog conditions1, String processName,String status) {
		NodeInstanceLog NodeInstanceLog = new NodeInstanceLog();

		NodeInstanceLog.setPreviousNode(conditions1.getPreviousNode());
		NodeInstanceLog.setNextNode(conditions1.getNextNode());
		NodeInstanceLog.setNodeName(conditions1.getNodeName());
		NodeInstanceLog.setNodeType(conditions1.getNodeType());
		NodeInstanceLog.setProcessName(processName);
		NodeInstanceLog.setTaskId(conditions1.getTaskId());
		NodeInstanceLog.setWorkStatus(status);
		//NodeInstanceLog.setMapvalue(conditions1.getMapvalue());
		//NodeInstanceLog.setParameters(conditions1.getParameters());
		NodeInstanceLog.setLogDate(dt);
		NodeInstanceLog.setCreatedTaskDate(dt);
		NodeInstanceRepo.save(NodeInstanceLog);
		
	}
	
	


	public void setlogForUser(Middleware item, String processName, String status, Map<String, String> parameters,
			String mapvalue, String owner,String uuid) {
		NodeInstanceLog NodeInstanceLog = new NodeInstanceLog();

		NodeInstanceLog.setPreviousNode(item.getPrevious());
		NodeInstanceLog.setNextNode(item.getNext());
		NodeInstanceLog.setNodeName(item.getName());
		NodeInstanceLog.setNodeType(item.getEventType());
		NodeInstanceLog.setProcessName(processName);
		NodeInstanceLog.setTaskId(item.getTask());
		NodeInstanceLog.setWorkStatus(status);
		NodeInstanceLog.setOwner(owner);
		NodeInstanceLog.setUuid(uuid);
		NodeInstanceLog.setMapvalue(mapvalue);
		NodeInstanceLog.setParameters(parameters);
		NodeInstanceLog.setStartTaskDate(dt);
		NodeInstanceLog.setLogDate(dt);
		NodeInstanceLog.setCreatedTaskDate(dt);
		NodeInstanceRepo.save(NodeInstanceLog);
		
	}




	




}
