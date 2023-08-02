package com.jbpm.threads;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jbpm.ServiceImpl.NodeInstanceLogSave;
import com.jbpm.ServiceImpl.ProcessStartWork;
import com.jbpm.entity.MiddInstanceFuncAccessMappingVO;
import com.jbpm.entity.Middleware;
import com.jbpm.entity.NodeInstanceFunctionAccessMappingVO;
import com.jbpm.omniCommon.Constant;
import com.jbpm.omniCommon.ProcessNodesWhile;

import com.jbpm.repo.NodeInstanceRepo;

public class ThreadClass1 implements Runnable{
	
	
	private String processName;
	
	private Middleware item;

	private NodeInstanceRepo nodeInstanceRepo;

	
	private ProcessStartWork processStartWork;
	private ProcessNodesWhile processNodesWhile;

	private NodeInstanceLogSave nodeLogSave;

	private String uuid;


	public ThreadClass1(String processName, Middleware item, NodeInstanceRepo nodeInstanceRepo,
			ProcessStartWork processStartWork,ProcessNodesWhile processNodesWhile, NodeInstanceLogSave nodeLogSave,String uuid) {
		super();
		this.processName = processName;
		this.item = item;
		this.nodeInstanceRepo = nodeInstanceRepo;
		this.processStartWork = processStartWork;
		this.processNodesWhile = processNodesWhile;
		this.nodeLogSave = nodeLogSave;
		this.uuid = uuid;
	}


	public ThreadClass1() {
		super();
	}






	public void parallelTask(String processName,Middleware item1) throws IOException {
	
		Map<String, String> param = null;
		String mvalue = " ";
		
		String nextNode=processNodesWhile.processForCurrentNodeType( processName, item1,nodeLogSave,uuid);
		
		
		processNodesWhile.processNodesUserWork(nextNode, processName,mvalue,param,processNodesWhile,uuid);	

	}

	public void run() {

		try {
			parallelTask(processName, item);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
