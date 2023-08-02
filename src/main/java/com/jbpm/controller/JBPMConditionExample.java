package com.jbpm.controller;




import java.io.File;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;


	public class JBPMConditionExample {
		
		public static void main(String[] args) {
		
//			BpmnModelInstance modelInstance = Bpmn.createProcess()
//					 .name("myProcess")
//					.startEvent()
//				      .userTask()
//				      .exclusiveGateway()
//				        .condition("approved", "${approved}")
//				        .scriptTask()
//					    .scriptFormat("java")
//					    .scriptText("System.out.println(\"hi script 1 !\")")
//				        .endEvent()
//				      .moveToLastGateway()
//				        .condition("not approved", "${!approved}")
//				        .scriptTask()
//					    .scriptFormat("java")
//					    .scriptText("System.out.println(\"hi script 2 !\")")
//				        .endEvent()
//				      .done();
//			
//
//			Bpmn.validateModel(modelInstance);
//			Bpmn.writeModelToFile(new File("process3.bpmn"), modelInstance);
//		    System.out.println("BPMN model instance created successfully.");
	
//			
//			BpmnModelInstance modelInstance = Bpmn.createProcess()
//					 .name("myProcess")
//					.startEvent()
//				      .userTask()
//				      .exclusiveGateway()
//				        .condition("approved", "${approved}")
//				        .scriptTask()
//				        .endEvent()
//				      .moveToLastGateway()
//				        .condition("not approved", "${!approved}")
//				        .scriptTask()
//				        .endEvent()
//				      .done();
//			
//
//			Bpmn.validateModel(modelInstance);
//			Bpmn.writeModelToFile(new File("process.bpmn"), modelInstance);
//		    System.out.println("BPMN model instance created successfully.");
		


		 ////---------------------------------------------------
		    
		    
			BpmnModelInstance modelInstance = Bpmn.createProcess()
					
					 .startEvent()
				      .scriptTask()
				      .endEvent()
				      .done();
					
//				    .startEvent()
//				    .userTask("task1")
//				    .scriptTask("task2")
//				    .scriptFormat("java")
//				    .scriptText("println(\"hi shadma!\")")
//				    .endEvent()
//				    .done();

				// validate and write the BPMN model to a file
				Bpmn.validateModel(modelInstance);
				Bpmn.writeModelToFile(new File("test.bpmn"), modelInstance);
			    System.out.println("BPMN model instance created successfully.");
          
	}

	

	
	    	
	}    
