package com.jbpm.entity;

import java.util.Date;

public class MiddInstanceFuncAccessMappingVO {
	
	
    private Long middId;
	private String processName;
	private String Name;
	private String eventType;
	private String next;
    private String previous;
    private String task;
    private Date createdDate;
    
    
	

	public MiddInstanceFuncAccessMappingVO(Long middId, String processName, String name, String eventType, String next,
			String previous, String task, Date createdDate) {
		super();
		this.middId = middId;
		this.processName = processName;
		Name = name;
		this.eventType = eventType;
		this.next = next;
		this.previous = previous;
		this.task = task;
		this.createdDate = createdDate;
	}


	public MiddInstanceFuncAccessMappingVO() {
		super();
	}


	public Long getMiddId() {
		return middId;
	}


	public void setMiddId(Long middId) {
		this.middId = middId;
	}


	public String getProcessName() {
		return processName;
	}


	public void setProcessName(String processName) {
		this.processName = processName;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
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


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	

	
    
    
}
