package com.jbpm.ServiceImpl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbpm.entity.ConditionsVo;
import com.jbpm.entity.Gateways;
import com.jbpm.entity.Middleware;
import com.jbpm.repo.MiddRepo;

@Component
public class MiddLogSave {
	
	
	@Autowired
	MiddRepo MiddRepo;
	
	
	Date dt = new Date();
		public void setMiddlog(ConditionsVo con, String processName) {
			Middleware middleware = new Middleware();
			middleware.setProcessName(processName);
			middleware.setEventType(con.getEventType());
			middleware.setNext(con.getNext());	
			middleware.setName(con.getName());	
			middleware.setPrevious(con.getPrevious());
			middleware.setTask(con.getTask());
			middleware.setCreatedDate(dt);
			MiddRepo.save(middleware);
			
		}
		
		public void setMiddlog2(Gateways con, String processName) {
			Middleware middleware = new Middleware();
			middleware.setProcessName(processName);
			middleware.setEventType(con.getEventType());
			middleware.setNext(con.getNext());	
			middleware.setName(con.getName());	
			middleware.setPrevious(con.getPrevious());
			middleware.setTask(con.getTask());
			middleware.setCreatedDate(dt);
			MiddRepo.save(middleware);
			
		}
		
		
		///for copyobject
		public void setMidLogForCopy(ConditionsVo con, String name, String mapvalue, String owner, String processName,Map<String, String> parameters) {
			Middleware middleware = new Middleware();
			middleware.setTask(con.getTask());
			middleware.setEventType(con.getEventType());
			middleware.setPrevious(con.getPrevious());
			middleware.setNext(con.getNext());
			
			middleware.setProcessName(processName);
			middleware.setEventType(con.getEventType());
			middleware.setNext(con.getNext());	
			middleware.setName(con.getName());	
			middleware.setPrevious(con.getPrevious());
			middleware.setTask(con.getTask());
			middleware.setCreatedDate(dt);
			MiddRepo.save(middleware);
		
		}

}
