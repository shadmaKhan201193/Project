package com.jbpm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InclusiveGateway extends Gateways{
	
	private String combineFrom;

	public String getCombineFrom() {
		return combineFrom;
	}

	public void setCombineFrom(String combineFrom) {
		this.combineFrom = combineFrom;
	}

	

	
	
	

}
