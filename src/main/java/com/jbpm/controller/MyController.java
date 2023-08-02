package com.jbpm.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbpm.Components.ProcessForDiagram;
import com.jbpm.ServiceImpl.ProcessForStart2;
import com.jbpm.entity.MesssgeVO;
import com.jbpm.entity.NodeILogListVO;
import com.jbpm.entity.NodeInstanceLog;
import com.jbpm.entity.ProcessDef;
import com.jbpm.entity.Task;
import com.jbpm.repo.StartRepo;
import com.jbpm.repo.UsersTableRepo;

@RestController
public class MyController {

	@Autowired
	ProcessForDiagram processForDiagram;

	@Autowired
	ProcessForStart2 processForStart2;

	@Autowired
	UsersTableRepo UsersTableRepo;

	ObjectMapper Obj = new ObjectMapper();
	String responce = "";

	@Autowired
	StartRepo startRepo;

	@PostMapping("/createProcess")
	public ResponseEntity<?> processFordiagram(@RequestBody ProcessDef Process) throws IOException {
		Task process = startRepo.getfindbyProcessName(Process.getProcessname());
		if (process == null) {
			try {

				processForDiagram.Event(Process);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok("Process definition received");
		}

		else {
			return ResponseEntity.ok("Process name already exist");
		}
	}

	@GetMapping("/startProcess/{processName}")
	public MesssgeVO startProcessWithGAteway(@PathVariable String processName) throws IOException {

		MesssgeVO response = processForStart2.StartProcess(processName);
		if (response != null) {
			return response;
		}

		response.setSuccess("not started");
		return response;

	}

	@GetMapping("/initiateUser/{processName}/{taskId}/{uuId}")
	public MesssgeVO initiateUserTask(@PathVariable String processName, @PathVariable String taskId,@PathVariable String uuId) throws IOException {

		MesssgeVO response = processForStart2.initiateUser(taskId, processName,uuId);

		if (response != null) {
			return response;
		}
		response.setSuccess("not initiated");
		response.setUniqueId(uuId);
		return response;

	}

	@PostMapping("/startUser/{processName}/{taskId}/{uuId}")
	public MesssgeVO startUserTaskWithGAteway(@PathVariable String processName, @PathVariable String taskId, @PathVariable String uuId,
			@RequestBody Map<String, String> param) throws IOException {
		MesssgeVO response = processForStart2.startUser(processName, taskId, param,uuId);
		if (response != null) {
			return response;
		}
		response.setSuccess("not started");
		response.setUniqueId(uuId);
		return response;
	}
	
	
	

	@GetMapping("/reassignTaskToUser/{processname}/{taskId}/{usrerName}/{uuId}")
	public MesssgeVO reassignTaskToUser(@PathVariable String processname, @PathVariable String taskId,
			@PathVariable String usrerName,@PathVariable String uuId) {
		  MesssgeVO message=new MesssgeVO();

		NodeInstanceLog NodeInstanceLog = processForStart2.reassignTask(usrerName, processname, taskId,uuId);
		if (NodeInstanceLog != null) {
			message.setSuccess("user reAssign successfully");
			message.setUniqueId(uuId);
			return message;
		} else {
			message.setSuccess("failure while user reAssign for task");
			message.setUniqueId(uuId);
			return message;
		
		}
	}
	
	@GetMapping("/getAll/{processname}{usrerName}/{uuId}")
	public List<NodeInstanceLog> getAllRecordStatus(@PathVariable String processname,@PathVariable String uuId) {
		
		List<NodeInstanceLog> NodeInstanceLog = processForStart2.getAllRecord(processname,uuId);
		if(NodeInstanceLog!=null){
			
         return NodeInstanceLog;
			
		}
		else {
			return null;
		}
		
		
		
	}

}
