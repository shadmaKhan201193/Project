package com.jbpm.Components;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.jbpm.entity.ConditionsVo;
import com.jbpm.entity.EndTask;
import com.jbpm.entity.ExclusiveGateway;
import com.jbpm.entity.InclusiveGateway;
import com.jbpm.entity.ParallelGateway;
import com.jbpm.entity.ProcessDef;
import com.jbpm.entity.RuleTask;
import com.jbpm.entity.ScriptTask;
import com.jbpm.entity.StartTask;
import com.jbpm.entity.UserTask;
import com.jbpm.omniCommon.XmlCreator;
import com.jbpm.repo.EndRepo;
import com.jbpm.repo.ExclusiveRepo;
import com.jbpm.repo.InclusiveRepo;
import com.jbpm.repo.ParallelRepo;
import com.jbpm.repo.RuleRepo;
import com.jbpm.repo.ScriptRepo;
import com.jbpm.repo.StartRepo;
import com.jbpm.repo.UserRepo;
@Component
public class ProcessForDiagram {
	
	@Autowired
	FlowNodeBuilder flowNodeBuilder;

	@Autowired
	XmlCreator XmlCreator;
	
	@Autowired
	ObjectType objectType;
	
	@Autowired
	ExclusiveRepo exclusiveRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ScriptRepo scriptRepo;
	
	@Autowired
	InclusiveRepo inclusiveRepo;
	
	@Autowired
	EndRepo endRepo;
	
	@Autowired
	ParallelRepo parallelRepo;
	
	@Autowired
	StartRepo startRepo;
	
	@Autowired
	RuleRepo ruleRepo;
	
	
	StartTask object;
	
	public String Event(ProcessDef Process) throws ParserConfigurationException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element rootElement = doc.createElement("process");
		doc.appendChild(rootElement);		
		List<ConditionsVo> conditionList = Process.getAllTaskList();

		List<Object> tasklist=objectType.getObjectType(conditionList,Process.getProcessname());
		for (Object task : tasklist) {
			
	
		if(task instanceof StartTask) {
			StartTask start = (StartTask) task;
			start.setProcessName(Process.getProcessname());
            startRepo.save(start);
            flowNodeBuilder.startTask(start, rootElement, doc);
		}
		 else if (task instanceof UserTask) {
            UserTask userTask = (UserTask) task;
            userTask.setProcessName(Process.getProcessname());
            userRepo.save(userTask);
           flowNodeBuilder.userTask(userTask, rootElement, doc);
        }
		
		 else if (task instanceof ScriptTask) {
			 ScriptTask scriptTask = (ScriptTask) task;
			 scriptTask.setProcessName(Process.getProcessname());
			scriptRepo.save(scriptTask);
	            flowNodeBuilder.scriptTask(scriptTask, rootElement, doc);
	        }
		 else if (task instanceof ExclusiveGateway) {
			 ExclusiveGateway ExclusiveGateway = (ExclusiveGateway) task;
			 ExclusiveGateway.setProcessName(Process.getProcessname());
			 exclusiveRepo.save(ExclusiveGateway);
	            flowNodeBuilder.exclusiveGateway(ExclusiveGateway, rootElement, doc);
	        }
		 else if (task instanceof InclusiveGateway) {
			 InclusiveGateway inclusive = (InclusiveGateway) task;
			 inclusive.setProcessName(Process.getProcessname());
			inclusiveRepo.save(inclusive);
	            flowNodeBuilder.inclusiveGateway(inclusive, rootElement, doc);
	        }
		 else if (task instanceof ParallelGateway) {
			 ParallelGateway parallel = (ParallelGateway) task;
			 parallel.setProcessName(Process.getProcessname());
			 parallelRepo.save(parallel);
	            flowNodeBuilder.parallelGateway(parallel, rootElement, doc);
	        }
		if(task instanceof RuleTask) {
			RuleTask rule = (RuleTask) task;
			rule.setProcessName(Process.getProcessname());
			ruleRepo.save(rule);
            flowNodeBuilder.ruleTask(rule, rootElement, doc);
		}
		 else if (task instanceof EndTask) {
			 EndTask end = (EndTask) task;
			 end.setProcessName(Process.getProcessname());
			 endRepo.save(end);
	            flowNodeBuilder.endTask(end, rootElement, doc);
	        }
		}
		XmlCreator.createfile(doc);   
		 
		 
		return "Success request";
	}
	
	

}
