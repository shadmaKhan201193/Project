package com.jbpm.Components;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbpm.ServiceImpl.MiddLogSave;
import com.jbpm.ServiceImpl.NodeInstanceLogSave;
import com.jbpm.entity.ConditionsVo;
import com.jbpm.entity.NodeInstanceLog;
import com.jbpm.omniCommon.Constant;
import com.jbpm.repo.MiddRepo;
import com.jbpm.repo.NodeInstanceRepo;
import com.jbpm.repo.UsersTableRepo;

@Component
public class ObjectType {

	String responce = "";

	@Autowired
	FlowControl flowControl;

	@Autowired
	UsersTableRepo UsersTableRepo;

	@Autowired
	NodeInstanceRepo NodeInstanceRepo;
	Date dt = new Date();
	
	@Autowired
	NodeInstanceLogSave nodeLogSave; 
	
	@Autowired
	MiddLogSave middLogSave;


	public List<Object> getObjectType(List<ConditionsVo> conditionList, String processName)
			throws JsonProcessingException {
		List<Object> taskList = new ArrayList<>();

		for (ConditionsVo con : conditionList) {
	
			NodeInstanceLog NodeInstanceLog = new NodeInstanceLog();
			String eventType = con.getEventType();
			if(con.getCopyComponent()==null) {
				//nodeLogSave.setlog2(con,processName,owner);   //saved record here for nodelog table
				middLogSave.setMiddlog(con,processName);   //saved record here for Middleware table
			}
			
			if (!con.getEventType().equalsIgnoreCase(Constant.ExclusiveGateway)
					&& (!con.getEventType().equalsIgnoreCase(Constant.ParallelGateway))) {
				taskList = flowControl.getWorkingTask1(eventType, con, taskList,processName);
			}

			if (con.getEventType().equalsIgnoreCase(Constant.ExclusiveGateway)) {
				taskList = flowControl.getWorkingTask2(con, taskList, processName);
			}

			if (con.getEventType().equalsIgnoreCase(Constant.ParallelGateway)) {
				taskList = flowControl.getWorkingTask3(con, taskList, processName);
			}
			
			

		}

		return taskList;
	}

	

	
}
