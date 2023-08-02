package com.jbpm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ExclusiveGateway  extends Gateways {

	
	  private String script;
	
	@Transient
	private String condition;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}




}
