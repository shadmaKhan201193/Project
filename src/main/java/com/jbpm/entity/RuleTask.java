package com.jbpm.entity;

import javax.persistence.Entity;

@Entity
public class RuleTask extends Task{
	
	  private String url;
	  private String method;
	  
	  
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	

	
	
	  

}
