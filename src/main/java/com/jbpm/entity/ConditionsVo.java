package com.jbpm.entity;

import java.util.List;
import java.util.Map;

public class ConditionsVo {

	private String id;
	private String name;
	private String eventType;
	private String next;
	private String previous;
	private String script;
	private String owner;
	private List<ExclusiveGateway> condition;
	private List<ParallelGateway> path;
	private String mapvalue;
	private String role;
	private String assignmentStrategy;
	private String copyComponent;
	private String task;
	private Long componentId;
	private String processname;

	private String url;
	private String method;

	private Map<String, String> parameters;

	public String getMapvalue() {
		return mapvalue;
	}

	public void setMapvalue(String mapvalue) {
		this.mapvalue = mapvalue;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<ParallelGateway> getPath() {
		return path;
	}

	public void setPath(List<ParallelGateway> path) {
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public List<ExclusiveGateway> getCondition() {
		return condition;
	}

	public void setCondition(List<ExclusiveGateway> condition) {
		this.condition = condition;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAssignmentStrategy() {
		return assignmentStrategy;
	}

	public void setAssignmentStrategy(String assignmentStrategy) {
		this.assignmentStrategy = assignmentStrategy;
	}

	public String getCopyComponent() {
		return copyComponent;
	}

	public void setCopyComponent(String copyComponent) {
		this.copyComponent = copyComponent;
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

	public String getProcessname() {
		return processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

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

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	
	
	
	

}
