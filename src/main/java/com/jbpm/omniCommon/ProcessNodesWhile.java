package com.jbpm.omniCommon;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbpm.Components.FlowControl;
import com.jbpm.ServiceImpl.NodeInstanceLogSave;
import com.jbpm.ServiceImpl.ProcessForParallelGateway;
import com.jbpm.ServiceImpl.ProcessStartWork;
import com.jbpm.entity.MiddInstanceFuncAccessMappingVO;
import com.jbpm.entity.Middleware;
import com.jbpm.entity.NodeInstanceFunctionAccessMappingVO;
import com.jbpm.entity.NodeInstanceLog;
import com.jbpm.entity.UserTask;
import com.jbpm.repo.MiddRepo;
import com.jbpm.repo.NodeInstanceRepo;
import com.jbpm.repo.UserRepo;

@Component
public class ProcessNodesWhile {
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	NodeInstanceRepo nodeInstanceRepo;

	@Autowired
	ProcessStartWork processStartWork;

	@Autowired
	ProcessForParallelGateway processForParallelGateway;
	
	@Autowired
	MiddRepo middRepo;
	
	@Autowired
	NodeInstanceLogSave nodeLogSave; 
	
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	FlowControl flowControl;
	
	Date dt = new Date();

	String status="";
	public void processNodesUserWork(String initialNextNode, String processName, String mapvalue,
			Map<String, String> param, ProcessNodesWhile processNodesWhile,String uuid)
			throws JsonMappingException, JsonProcessingException {

		boolean userTaskFound = false;
		boolean inclusiveFound = true;
		String nextNode = initialNextNode;
		String currentNextNode = null;

		List<Middleware> data1;

		while (nextNode != null) {

			//data1 = nodeInstanceRepo.getAllNodeInstanceLogsNew(nextNode, processName);
			data1 = middRepo.getAlltaskAndProcessAsList(nextNode, processName);
			
			for (Middleware item : data1) {
	
				
				if (item.getEventType().equals(Constant.UserTask)) {
					setUserStarted(item,processName,uuid);
					userTaskFound = true;
					break;
				}
				if (item.getEventType().equals(Constant.ExclusiveGateway)) {
					currentNextNode = processStartWork.gateway(processName, item, mapvalue, param,nodeLogSave,uuid);
				}
				if (item.getEventType().equals(Constant.ScriptTask)) {
					processStartWork.startScript(processName,item,nodeLogSave,uuid);
				}
				if (item.getEventType().equals(Constant.ParallelGateway)) {
					processForParallelGateway.parallelGateway(processName, item, processNodesWhile,nodeLogSave,uuid);
					userTaskFound = true;
					break;
				}
				if (item.getEventType().equals(Constant.InclusiveGateway)) {
					inclusiveFound = processStartWork.inclusiveCheck(processName,item,nodeLogSave,uuid);
					if (inclusiveFound) {
						currentNextNode = item.getNext();
					} else {
						break;
					}

				}
				if (item.getEventType().equals(Constant.RuleTask)) {
					currentNextNode=processStartWork.ruleStarted(processName, item,nodeLogSave,uuid);
					if(currentNextNode==null) {
						userTaskFound = true;
						break;
						
					}
					break;
				}

				if (item.getNext() == null && item.getEventType().equals(Constant.End)) {
					processStartWork.startEnd(processName,item,nodeLogSave,uuid);
				}
				nextNode = data1 != null && !data1.isEmpty() ? data1.get(0).getNext() : null;
			}

			if (currentNextNode != null) {
				nextNode = currentNextNode;
				currentNextNode = null;
			}
			if (userTaskFound) {
				break;

			}
			if (!inclusiveFound) {
				break;

			}
		}
	}
	
	
	public String processForCurrentNodeType(String processName,Middleware item1,NodeInstanceLogSave nodeLogSave,String uuid) throws JsonMappingException, JsonProcessingException {
		boolean userTaskFound = false;
		String nextNode=null;
		
		if(item1.getEventType().equals(Constant.ScriptTask)) {
			processStartWork.startScript(processName,item1,nodeLogSave,uuid);
			 nextNode = item1.getNext();
			 processStartWork.checkInclusive(processName,item1,nodeLogSave,uuid);
		}
		if(item1.getEventType().equals(Constant.UserTask)) {
			setUserStarted(item1,processName,uuid);
			userTaskFound = true;
			
			
		}
		
		return nextNode;
		
	}

	public void setUserStarted(Middleware item,String processName,String uuid) throws JsonMappingException, JsonProcessingException {
		
		UserTask userdetails = userRepo.getUserTaskDetails(item.getTask(), processName);
		status="pending";
		//nodeInstanceLog.setStartTaskDate(dt);
		String owner = null;
		if (userdetails!=null  && userdetails.getAssignmentStrategy()!=null) {
			owner = flowControl.userAssignmentForTask(userdetails.getRole(), userdetails.getAssignmentStrategy().toString(), userdetails.getEventType());
		}
		
		nodeLogSave.setlogForUser(item, processName,status,userdetails.getParameters(),userdetails.getMapvalue(), owner,uuid);
		

		
		
		

	}

}
