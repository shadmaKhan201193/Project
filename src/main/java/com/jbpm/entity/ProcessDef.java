package com.jbpm.entity;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ProcessDef {
	
	
	 private String processname;
	 
	 @Transient
	  private List<ConditionsVo> allTaskList;
	  
	
	
	public List<ConditionsVo> getAllTaskList() {
		return allTaskList;
	}
	public void setAllTaskList(List<ConditionsVo> allTaskList) {
		this.allTaskList = allTaskList;
	}
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	
	  
	  
}
