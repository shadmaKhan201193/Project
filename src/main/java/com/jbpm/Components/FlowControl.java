package com.jbpm.Components;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbpm.ServiceImpl.MiddLogSave;
import com.jbpm.ServiceImpl.NodeInstanceLogSave;
import com.jbpm.entity.AssignmentStrategy;
import com.jbpm.entity.ConditionsVo;
import com.jbpm.entity.EndTask;
import com.jbpm.entity.ExclusiveGateway;
import com.jbpm.entity.Gateways;
import com.jbpm.entity.InclusiveGateway;
import com.jbpm.entity.NodeInstanceLog;
import com.jbpm.entity.ParallelGateway;
import com.jbpm.entity.RuleTask;
import com.jbpm.entity.ScriptTask;
import com.jbpm.entity.StartTask;
import com.jbpm.entity.Task;
import com.jbpm.entity.UserTask;
import com.jbpm.omniCommon.Constant;
import com.jbpm.repo.NodeInstanceRepo;
import com.jbpm.repo.ScriptRepo;
import com.jbpm.repo.UserRepo;
import com.jbpm.repo.UsersTableRepo;

@Component
public class FlowControl {
	Task taskObject = null;
	Gateways taskObject1 = null;
	String str = "";

	@Autowired
	NodeInstanceRepo NodeInstanceRepo;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ScriptRepo scriptRepo;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UsersTableRepo UsersTableRepo;
	
	@Autowired
	NodeInstanceLogSave nodeLogSave;

	@Autowired
	MiddLogSave middLogSave;

	Date dt = new Date();

	public List<Object> getWorkingTask1(String eventType, ConditionsVo con, List<Object> taskList, String processName)
			throws JsonProcessingException {

		switch (eventType) {
		case Constant.START:
			StartTask startTask = objectMapper.readValue(objectMapper.writeValueAsString(con), StartTask.class);
			taskObject = startTask;
			break;
			
		case Constant.RuleTask:
			
			RuleTask ruleTask = objectMapper.readValue(objectMapper.writeValueAsString(con), RuleTask.class);
			taskObject = ruleTask;
			break;

		case Constant.ScriptTask:
			if (con.getCopyComponent() != null && con.getCopyComponent().equalsIgnoreCase(Constant.copyComponent)) {
				Task scriptTask = getCopyObject(con, processName);
				taskObject = scriptTask;
			}
			else {
			ScriptTask scriptTask = objectMapper.readValue(objectMapper.writeValueAsString(con), ScriptTask.class);
			scriptTask.setProcessName(processName);
			taskObject = scriptTask;
			}
			break;

		case Constant.UserTask:

			if (con.getCopyComponent() != null && con.getCopyComponent().equalsIgnoreCase(Constant.copyComponent)) {
				Task userTask = getCopyObject(con, processName);
				taskObject = userTask;
			}

			else {
				UserTask userTask = objectMapper.readValue(objectMapper.writeValueAsString(con), UserTask.class);
				userTask.setProcessName(processName);
				taskObject = userTask;
			}

			break;

		case Constant.InclusiveGateway:
			InclusiveGateway inclusive = objectMapper.readValue(objectMapper.writeValueAsString(con),
					InclusiveGateway.class);
			taskObject1 = inclusive;
			taskList.add(taskObject1);
			break;

		case Constant.End:
			EndTask endTask = objectMapper.readValue(objectMapper.writeValueAsString(con), EndTask.class);
			taskObject = endTask;
			break;

		}

		if (taskObject != null) {
			taskList.add(taskObject);
		}

		return taskList;
	}

	public List<Object> getWorkingTask2(ConditionsVo con, List<Object> taskList, String processName)
			throws JsonProcessingException {

		List<ExclusiveGateway> conditions = con.getCondition();
		for (ExclusiveGateway conditions1 : conditions) {
			NodeInstanceLog NodeInstanceLog = new NodeInstanceLog();

			ExclusiveGateway exclusiveGateway = objectMapper.readValue(objectMapper.writeValueAsString(conditions1),ExclusiveGateway.class);
			taskObject1 = exclusiveGateway;
			taskList.add(taskObject1);
			// calling this method for save all task record in one table
			//nodeLogSave.setlog2(conditions1,processName);   //saved record here for node table
			
			middLogSave.setMiddlog2(conditions1,processName);   //saved record here for Middleware table
			
		}
		return taskList;
	}

	public List<Object> getWorkingTask3(ConditionsVo con, List<Object> taskList, String processName)
			throws JsonProcessingException {
		List<ParallelGateway> conditions = con.getPath();
		for (ParallelGateway conditions1 : conditions) {

			//nodeLogSave.setlog2(conditions1,processName);   //saved record here for node table
			
			middLogSave.setMiddlog2(conditions1,processName);   //saved record here for Middleware table

			String eventType1 = conditions1.getEventType();
			switch (eventType1) {
			case Constant.ScriptTask:
				ScriptTask startTask = objectMapper.readValue(objectMapper.writeValueAsString(conditions1),
						ScriptTask.class);
				// str += startTask.getId() + " ";
				taskObject = startTask;
				break;
			case Constant.UserTask:
				UserTask userTask = objectMapper.readValue(objectMapper.writeValueAsString(conditions1),
						UserTask.class);
				// str += userTask.getId() + " ";
				taskObject = userTask;
				break;

			}
			taskList.add(taskObject);
		}
		ParallelGateway parallel = new ParallelGateway();
		// parallel.setId(con.getId());
		parallel.setEventType(con.getEventType());
		parallel.setPrevious(con.getPrevious());
		// parallel.setPath(str);
		taskObject1 = parallel;
		taskList.add(taskObject1);

		return taskList;
	}

	private Task getCopyObject(ConditionsVo con, String processName) {
		String owner = null;
		if (con != null && con.getEventType().equalsIgnoreCase(Constant.UserTask)) {

			UserTask userTask = userRepo.getCopyUser(String.valueOf(con.getComponentId()));
			if(userTask!=null) {
			
				owner = userAssignmentForTask(userTask.getRole(), userTask.getAssignmentStrategy().name(), userTask.getEventType());
				
				//nodeLogSave.setlog(con,userTask.getName(), userTask.getMapvalue(), owner,processName,userTask.getParameters());
				middLogSave.setMidLogForCopy(con,userTask.getName(), userTask.getMapvalue(), owner,processName,userTask.getParameters());
				UserTask usertask=new UserTask();
				
				usertask.setProcessName(processName);
				usertask.setNext(con.getNext());;
				usertask.setPrevious(con.getPrevious());
				usertask.setTask(con.getTask());
				usertask.setEventType(userTask.getEventType());
				usertask.setMapvalue(userTask.getMapvalue());
				usertask.setName(userTask.getName());
				usertask.setAssignmentStrategy(userTask.getAssignmentStrategy());
				usertask.setRole(userTask.getRole());
				return usertask;
			}
			
		

		}
		if (con != null && con.getEventType().equalsIgnoreCase(Constant.ScriptTask)) {

			ScriptTask scriptTask = scriptRepo.getCopyScript(String.valueOf(con.getComponentId()));
			if(scriptTask!=null) {
				
				nodeLogSave.setlog(con,scriptTask.getName(), scriptTask.getMapvalue(), owner,processName,scriptTask.getParameters());
				ScriptTask scripttask1=new ScriptTask();
				
				scripttask1.setProcessName(processName);
				scripttask1.setNext(con.getNext());;
				scripttask1.setPrevious(con.getPrevious());
				scripttask1.setTask(con.getTask());
				scripttask1.setEventType(scriptTask.getEventType());
				scripttask1.setMapvalue(scriptTask.getMapvalue());
				scripttask1.setName(scriptTask.getName());
				scripttask1.setScript(scriptTask.getScript());
				
				return scripttask1;

		}
		
		}
		return null;
	}
	
	
	
	
	public String userAssignmentForTask(String userRole, String strategy, String nodeType) {
		String assignedUser = null;
		BigInteger assignedCount = null;
		BigInteger minCount = BigInteger.valueOf(Long.MAX_VALUE);

		String[] Users = UsersTableRepo.getAllUser(userRole);
		
		List<String> usersListbyrole = Arrays.asList(Users);

		
		
		if (strategy.equals(AssignmentStrategy.ROUND_ROBIN.name())) {

			NodeInstanceLog nodeInstanceLog = NodeInstanceRepo.getfindLastAssignUser(usersListbyrole,nodeType);

			if (nodeInstanceLog == null || nodeInstanceLog.getOwner() == null) {
				return assignedUser = Users[0];
			} else {
				int lastIndex = -1;
				for (int i = 0; i < Users.length; i++) {
					if (Users[i].equals(nodeInstanceLog.getOwner())) {
						lastIndex = i;
						break;
					}
				}
				int nextIndex = (lastIndex + 1) % Users.length;
				return assignedUser = Users[nextIndex];
			}

		} // first if end here

		if (strategy.equals(AssignmentStrategy.LEAST_LOAD.name())) {

			List<Object[]> nodeInstanceLog = NodeInstanceRepo.getfindleastLoad(usersListbyrole,nodeType);

			if (nodeInstanceLog.isEmpty()) {
				return assignedUser = Users[0];
			} else {
				for (Object[] row : nodeInstanceLog) {
					String owner = (String) row[0];
					BigInteger count = (BigInteger) row[1];

					if (Arrays.asList(Users).contains(owner) && count.compareTo(minCount) < 0) {
						minCount = count;
						assignedUser = owner;
						assignedCount = count;
					}
				}

				return assignedUser;
			} // else end here

		}

		return assignedUser;
	}

	
}
