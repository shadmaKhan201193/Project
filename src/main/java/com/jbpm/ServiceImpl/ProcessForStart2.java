package com.jbpm.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbpm.Components.FlowControl;
import com.jbpm.Components.ObjectType;
import com.jbpm.entity.Middleware;
import com.jbpm.entity.NodeInstanceFunctionAccessMappingVO;
import com.jbpm.entity.NodeInstanceLog;
import com.jbpm.entity.UserTask;
import com.jbpm.omniCommon.ProcessNodesWhile;
import com.jbpm.repo.MiddRepo;
import com.jbpm.repo.NodeInstanceRepo;
import com.jbpm.repo.UserRepo;
import com.jbpm.entity.MesssgeVO;
@Component
public class ProcessForStart2 {
	Mapper mapper = new DozerBeanMapper();

	@Autowired
	NodeInstanceRepo nodeInstanceRepo;

	ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	ProcessStartWork processStartWork;
	
	@Autowired
	ObjectType objectType;
	
	@Autowired
	ProcessForParallelGateway processForParallelGateway;
	
	@Autowired
	ProcessNodesWhile processNodesWhile;
	
	
	@Autowired
	MiddRepo middRepo;
	
	@Autowired
	NodeInstanceLogSave nodeLogSave; 
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	FlowControl flowControl;
	
	String uuid;
	
	Date dt=new Date();
    String status="";
    
    MesssgeVO message=new MesssgeVO();
	public MesssgeVO StartProcess(String processName) throws JsonMappingException, JsonProcessingException {
		
		//NodeInstanceFunctionAccessMappingVO data = nodeInstanceRepo.getAllNodeInstanceLogs(processName);
		Middleware data = middRepo.getAllbypreviousNull(processName);
		 uuid = UUID.randomUUID().toString();
     
		//objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//NodeInstanceLog nodeInstanceLog = objectMapper.readValue(objectMapper.writeValueAsString(data),NodeInstanceLog.class);
		//nodeInstanceRepo.save(nodeInstanceLog);
		status="started";
		nodeLogSave.setlog1(data, processName,status,null,uuid);
		String nextNode = data.getNext();
		Map<String, String> param = null;
		String mvalue = " ";
		
		processNodesWhile.processNodesUserWork(nextNode, processName,mvalue,param,processNodesWhile,uuid);

		message.setSuccess("process started");
		message.setUniqueId(uuid);
		return message;
	}

	public MesssgeVO initiateUser(String task, String processName,String id) {
		UserTask userdetails = userRepo.getUserTaskDetails(task, processName);

		NodeInstanceLog nodeInstanceLog = nodeInstanceRepo.findByTaskIdAndProcessNameAndUuid(task, processName,id);
		
			if (nodeInstanceLog != null) {
			
				status = "inProgress";
				nodeInstanceLog.setWorkStatus(status);
				nodeInstanceRepo.save(nodeInstanceLog);
				// nodeLogSave.setlog2(nodeInstanceLog, processName,status);
				
				message.setSuccess("user initiated");
				message.setUniqueId(id);
				return message;
			}
		
		else {
			return null;
		}

	}
	public MesssgeVO startUser(String processName, String tastId, Map<String, String> param,String id)
			throws JsonMappingException, JsonProcessingException {

		NodeInstanceLog nodeInstanceLog = nodeInstanceRepo.findByTaskIdAndProcessNameAndUuid(tastId, processName,id);
		nodeInstanceLog.setWorkStatus("Completed");
		nodeInstanceLog.setEndTaskDate(dt);
         String Mapvalue=nodeInstanceLog.getMapvalue();
		nodeInstanceRepo.save(nodeInstanceLog);
     	
	   String nextNode = nodeInstanceLog.getNextNode();
	   processNodesWhile.processNodesUserWork(nextNode, processName,Mapvalue,param,processNodesWhile,id);
		message.setSuccess("Success");
		message.setUniqueId(id);

		return message;
	}
	
	public NodeInstanceLog reassignTask(String userName, String processname, String taskId,String id) {

		List<NodeInstanceFunctionAccessMappingVO> data1;

		NodeInstanceLog nodeInstanceLog=nodeInstanceRepo.findByTaskIdAndProcessNameAndUuid(taskId, processname,id); 
       if(nodeInstanceLog!=null) {
	
    	     nodeInstanceLog.setOwner(userName);
    	     NodeInstanceLog NodeInstanceLog=nodeInstanceRepo.save(nodeInstanceLog);
    	     return NodeInstanceLog;
		}
       else {
    	  return null; 
       }
				
	}
	
	public List<NodeInstanceLog> getAllRecord(String processname,String id) {
		
		List<NodeInstanceLog> nodeInstanceLog=nodeInstanceRepo.getallRecordBYUUID( processname,id);
		
		return nodeInstanceLog;
		
	}
	


	

}
