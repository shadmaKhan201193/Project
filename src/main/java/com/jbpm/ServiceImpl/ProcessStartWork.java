package com.jbpm.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbpm.entity.ConditionsVo;
import com.jbpm.entity.MiddInstanceFuncAccessMappingVO;
import com.jbpm.entity.Middleware;
import com.jbpm.entity.NodeInstanceFunctionAccessMappingVO;
import com.jbpm.entity.NodeInstanceLog;
import com.jbpm.entity.RuleTask;
import com.jbpm.omniCommon.Constant;
import com.jbpm.repo.MiddRepo;
import com.jbpm.repo.NodeInstanceRepo;
import com.jbpm.repo.RuleRepo;


@Component
public class ProcessStartWork {
	
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	NodeInstanceRepo nodeInstanceRepo;
	
	@Autowired
	RuleRepo ruleRepo;
	
	RestTemplate restTemplate = new RestTemplate();
	
	Date dt = new Date();
	
	@Autowired
	MiddRepo middRepo;
	
	String status="";
	
	public void startUSer(NodeInstanceFunctionAccessMappingVO item)throws JsonMappingException, JsonProcessingException {

		item.setWorkStatus("Completed");
		NodeInstanceLog nodeInstanceLog = objectMapper.readValue(objectMapper.writeValueAsString(item),NodeInstanceLog.class);
		nodeInstanceLog.setStartTaskDate(dt);
		nodeInstanceLog.setEndTaskDate(dt);
		nodeInstanceRepo.save(nodeInstanceLog);

	}

	public void startEnd(String processName,Middleware item,NodeInstanceLogSave nodeLogSave,String uuid)throws JsonMappingException, JsonProcessingException {
		
		//NodeInstanceLog nodeInstanceLog = objectMapper.readValue(objectMapper.writeValueAsString(item),NodeInstanceLog.class);
		status="Completed";
//		nodeInstanceLog.setStartTaskDate(dt);
//		nodeInstanceLog.setEndTaskDate(dt);
		nodeLogSave.setlog1(item, processName,status,null,uuid);

	}
	
	public void startScript(String processName,Middleware item,NodeInstanceLogSave nodeLogSave,String uuid)throws JsonMappingException, JsonProcessingException {

		
		//NodeInstanceLog nodeInstanceLog = objectMapper.readValue(objectMapper.writeValueAsString(item),NodeInstanceLog.class);
		status="Completed";
//		nodeInstanceLog.setStartTaskDate(dt);
//		nodeInstanceLog.setEndTaskDate(dt);
		nodeLogSave.setlog1(item, processName,status,null,uuid);
		

	}
	
	
	
	
	public String  gateway(String processName,Middleware item,String userowner,Map<String, String> param,NodeInstanceLogSave nodeLogSave,String uuid)throws JsonMappingException, JsonProcessingException
	{
	
	
		
		//NodeInstanceLog nodeInstanceLog = objectMapper.readValue(objectMapper.writeValueAsString(item),NodeInstanceLog.class);
		String status="Completed";
	
		nodeLogSave.setlog1(item, processName,status,null,uuid);
		String node="";
		String paramowner=String.valueOf(param.get("owner"));
		String str[]=item.getNext().split(",");   //uska next 2 hai wo get kiya 
		  String nextNode1 = str[0];
          String nextNode2 =str[1];
          
    
  		List<Middleware> data1;

  			//data1 = nodeInstanceRepo.getAllIfTask(nextNode1,nextNode2, processName);   //2 if mil rahe yeha pe trueEvent or falseEvent
			data1 = middRepo.getAllIfTask(nextNode1,nextNode2, processName);   //2 if mil rahe yeha pe trueEvent or falseEvent

  			
  			for (Middleware condition : data1) {
  				
  				if(userowner.equalsIgnoreCase(paramowner)&&condition.getName().equalsIgnoreCase("trueEvent")) {
  					
  					//NodeInstanceLog nodelog = objectMapper.readValue(objectMapper.writeValueAsString(condition),NodeInstanceLog.class);
  					String status1="Completed";
//  					nodelog.setStartTaskDate(dt);
//  					nodelog.setEndTaskDate(dt);
  					nodeLogSave.setlog1(condition, processName,status1,null,uuid);
  					node=condition.getNext();
  					checkInclusive(processName,condition,nodeLogSave,uuid);
  				}
  				if(!(userowner.equalsIgnoreCase(paramowner))&&condition.getName().equalsIgnoreCase("falseEvent")) {
  				
  					//NodeInstanceLog condition1 = objectMapper.readValue(objectMapper.writeValueAsString(condition),NodeInstanceLog.class);
  					String status2="Completed";
//  					condition1.setStartTaskDate(dt);
//  					nodeInstanceLog.setEndTaskDate(dt);
  					nodeLogSave.setlog1(condition, processName,status2,null,uuid);
  					node=condition.getNext();
  				}
  			}
			return node;
		
	}
	


	public void checkInclusive(String processName,Middleware nextnode,NodeInstanceLogSave nodeLogSave,String uuid) throws JsonMappingException, JsonProcessingException {
		if(processName!=null && nextnode!=null) {
			   int mapValue = 1; // Initial mapValue
		       Middleware data1 = middRepo.findByTaskAndProcessName(nextnode.getNext(), processName);
		
				if(data1.getEventType().equals(Constant.InclusiveGateway)) 
				{
					NodeInstanceLog data = nodeInstanceRepo.findByTaskIdAndProcessNameAndUuid(data1.getTask(), processName,uuid);
					
				if(data==null) {
					status="pending";
					nodeLogSave.setlog1(data1, processName,status,String.valueOf(mapValue),uuid);
					data1.setMapvalue(mapValue);
					middRepo.save(data1);
				}
				
				
				
				else {
				int currentMapValue =Integer.parseInt((data.getMapvalue()));
				data.setMapvalue(String.valueOf(currentMapValue + 1));
				nodeInstanceRepo.save(data);
				data1.setMapvalue((currentMapValue + 1));
				middRepo.save(data1);
		         }
			
			
			
				
				}
				
				
			
			
			
			
		}
		
	}
	
	

	public boolean inclusiveCheck(String processName,Middleware item,NodeInstanceLogSave nodeLogSave,String uuid) throws JsonMappingException, JsonProcessingException {
		boolean userTaskFound = false;
		
		String str[]=item.getPrevious().split(",");
		  //NodeInstanceLog condition1 = objectMapper.readValue(objectMapper.writeValueAsString(item),NodeInstanceLog.class);
		if (str.length == (item.getMapvalue())) {
			NodeInstanceLog data = nodeInstanceRepo.findByTaskIdAndProcessNameAndUuid(item.getTask(), processName,uuid);
			data.setWorkStatus("Completed");
			nodeInstanceRepo.save(data);
	 
	   
		return userTaskFound=true;
	  
		}
		else {

			return userTaskFound;
		}
		
	}
	
	
	public String ruleStarted(String processName, Middleware item,NodeInstanceLogSave nodeLogSave,String uuid) throws JsonMappingException, JsonProcessingException {
		String node=null;
		String doAlert =null;
		try {
		RuleTask rule=ruleRepo.getRuleDetails(item.getTask(), processName);
		if(rule!=null) {
			NodeInstanceLog getparam=nodeInstanceRepo.findByTaskIdAndProcessNameAndUuid(item.getPrevious(), processName,uuid);
			
			if(getparam.getParameters()!=null) {
				
		
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<?> httpEntity = new HttpEntity<>(getparam.getParameters(),headers);
            ResponseEntity<?> rersponse = restTemplate.exchange(rule.getUrl(),HttpMethod.POST, httpEntity, String.class);
            JsonNode parent = objectMapper.readTree(rersponse.getBody().toString());
			 doAlert = parent.path("doAlert").asText();
			
			}
		
			
            if(doAlert=="true") {
            	 // NodeInstanceLog condition1 = objectMapper.readValue(objectMapper.writeValueAsString(item),NodeInstanceLog.class);
            	status="Completed";
//            	  condition1.setStartTaskDate(dt);
//            	  condition1.setEndTaskDate(dt);
            	  nodeLogSave.setlog1(item, processName,status,null,uuid);
    			node=item.getNext();
            }
            else {
             //NodeInstanceLog condition1 = objectMapper.readValue(objectMapper.writeValueAsString(item),NodeInstanceLog.class);
            	status="Completed";
//           	  condition1.setStartTaskDate(dt);
//           	  condition1.setEndTaskDate(dt);
           	 nodeLogSave.setlog1(item, processName,status,null,uuid);
   			node=item.getNext();
            }
        
            
		}
	
		
		} catch (Exception e) {
			
			 e.printStackTrace();
			    doAlert = "false";
				node=item.getNext();
			   
			}
		return node;
	}

	

	
	
	
	
}



