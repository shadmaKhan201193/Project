package com.jbpm.Components;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.jbpm.entity.EndTask;
import com.jbpm.entity.ExclusiveGateway;
import com.jbpm.entity.InclusiveGateway;
import com.jbpm.entity.ParallelGateway;
import com.jbpm.entity.RuleTask;
import com.jbpm.entity.ScriptTask;
import com.jbpm.entity.StartTask;
import com.jbpm.entity.UserTask;

@Component
public class FlowNodeBuilder {

	public void startTask(StartTask task, Element rootElement, Document doc) {


		Element startTask = doc.createElement(task.getName());
		startTask.setAttribute("id", (String.valueOf(task.getComponentId())));
		startTask.setAttribute("name", task.getName());
		startTask.setAttribute("source", task.getEventType());
		startTask.setAttribute("target", task.getNext());
		rootElement.appendChild(startTask);

	}

	public void scriptTask(ScriptTask task, Element rootElement, Document doc) {
		Element scripTask = doc.createElement(task.getName());
		scripTask.setAttribute("id", (String.valueOf(task.getComponentId())));
		scripTask.setAttribute("name", task.getName());
		scripTask.setAttribute("source", task.getPrevious());
		scripTask.setAttribute("target", task.getNext());
		scripTask.setAttribute("script", task.getScript());
		rootElement.appendChild(scripTask);
	}

	public void endTask(EndTask task, Element rootElement, Document doc) {
		Element endTask = doc.createElement(task.getName());
		endTask.setAttribute("id",(String.valueOf(task.getComponentId())));
		endTask.setAttribute("name", task.getName());
		endTask.setAttribute("source", task.getPrevious());
		rootElement.appendChild(endTask);

	}

	public void userTask(UserTask task, Element rootElement, Document doc) {
		Element userTask = doc.createElement(task.getName());
		userTask.setAttribute("id", (String.valueOf(task.getComponentId())));
		userTask.setAttribute("name", task.getName());
		userTask.setAttribute("source", task.getPrevious());
		userTask.setAttribute("target", task.getNext());
		//userTask.setAttribute("owner", task.getOwner());
		rootElement.appendChild(userTask);

	}

	
	
	public void exclusiveGateway(ExclusiveGateway task, Element rootElement, Document doc) {
		Element gateway = doc.createElement(task.getName());
		gateway.setAttribute("id", (String.valueOf(task.getComponentId())));
		gateway.setAttribute("name", task.getName());
		gateway.setAttribute("eventType", task.getEventType());				
		rootElement.appendChild(gateway);
	}
	public void parallelGateway(ParallelGateway task, Element rootElement, Document doc) {
		Element gateway = doc.createElement(task.getEventType());
		gateway.setAttribute("id", (String.valueOf(task.getComponentId())));
		gateway.setAttribute("name", task.getName());
		gateway.setAttribute("eventType", task.getEventType());	
		gateway.setAttribute("source", task.getPrevious());
		rootElement.appendChild(gateway);
	}
	
	public void inclusiveGateway(InclusiveGateway task, Element rootElement, Document doc) {
		Element gateway = doc.createElement(task.getEventType());
		gateway.setAttribute("id", (String.valueOf(task.getComponentId())));
		gateway.setAttribute("name", task.getName());
		gateway.setAttribute("eventType", task.getEventType());	
		gateway.setAttribute("target", task.getNext());
		gateway.setAttribute("target", task.getCombineFrom());
		rootElement.appendChild(gateway);
	}
	
	public void ruleTask(RuleTask task, Element rootElement, Document doc) {


		Element startTask = doc.createElement(task.getName());
		startTask.setAttribute("id", (String.valueOf(task.getComponentId())));
		startTask.setAttribute("name", task.getName());
		startTask.setAttribute("source", task.getEventType());
		startTask.setAttribute("target", task.getNext());
		rootElement.appendChild(startTask);

	}
	
//	public void ifEvent(Task2 task, Element rootElement, Document doc) {
//		Element ifevent = doc.createElement(task.getId());
//		ifevent.setAttribute("id", task.getId());
//		ifevent.setAttribute("name", task.getName());
//		ifevent.setAttribute("source", task.getPrevious());
//		ifevent.setAttribute("target", task.getNext());
//		ifevent.setAttribute("script", task.getScript());
//		rootElement.appendChild(ifevent);
//
//	}

	

}
