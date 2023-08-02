package com.jbpm.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.databind.ObjectMapper;

@MappedSuperclass
public class Component {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long componentId;
	
	   //private String id;
	    private String name;
	    private String eventType;
	    private String next;
	    private String previous;
	    private String processName;   
	    private String task;  
	    private String mapvalue;
	   
	    private String parameters;
	   
	 
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEventType() {
			return eventType;
		}
		public void setEventType(String eventType) {
			this.eventType = eventType;
		}
		public String getNext() {
			return next;
		}
		public void setNext(String next) {
			this.next = next;
		}
		public String getPrevious() {
			return previous;
		}
		public void setPrevious(String previous) {
			this.previous = previous;
		}
		public String getProcessName() {
			return processName;
		}
		public void setProcessName(String processName) {
			this.processName = processName;
		}
		
		public String getMapvalue() {
			return mapvalue;
		}
		public void setMapvalue(String mapvalue) {
			this.mapvalue = mapvalue;
		}
		public Long getComponentId() {
			return componentId;
		}
		public void setComponentId(Long componentId) {
			this.componentId = componentId;
		}
		public String getTask() {
			return task;
		}
		public void setTask(String task) {
			this.task = task;
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
