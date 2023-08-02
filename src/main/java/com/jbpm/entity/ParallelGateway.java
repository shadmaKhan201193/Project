package com.jbpm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ParallelGateway extends Gateways {
	
	
	private String path;
	private String script;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	


	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}
}
