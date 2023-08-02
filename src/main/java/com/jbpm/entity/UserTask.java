package com.jbpm.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class UserTask extends Task {
	
	
	
   private String owner;
    private String role;
    
    @Enumerated(EnumType.STRING)
    private AssignmentStrategy assignmentStrategy;


	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public AssignmentStrategy getAssignmentStrategy() {
		return assignmentStrategy;
	}

	public void setAssignmentStrategy(AssignmentStrategy assignmentStrategy) {
		this.assignmentStrategy = assignmentStrategy;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	
	
	  

}
