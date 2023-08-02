package com.jbpm.ServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbpm.entity.MiddInstanceFuncAccessMappingVO;
import com.jbpm.entity.Middleware;
import com.jbpm.entity.NodeInstanceFunctionAccessMappingVO;
import com.jbpm.entity.NodeInstanceLog;
import com.jbpm.omniCommon.ProcessNodesWhile;
import com.jbpm.repo.MiddRepo;
import com.jbpm.repo.NodeInstanceRepo;
import com.jbpm.threads.ThreadClass1;

@Component
public class ProcessForParallelGateway {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	NodeInstanceRepo nodeInstanceRepo;

	@Autowired
	ProcessStartWork processStartWork;

	@Autowired
	MiddRepo middRepo;

	Date dt = new Date();
	String status = "";

	public void parallelGateway(String processName, Middleware item, ProcessNodesWhile processNodesWhile,
			NodeInstanceLogSave nodeLogSave, String uuid) throws JsonMappingException, JsonProcessingException {

		// NodeInstanceLog nodeInstanceLog =
		// objectMapper.readValue(objectMapper.writeValueAsString(item),NodeInstanceLog.class);
		status = "Completed";
		// nodeInstanceLog.setStartTaskDate(dt);
		nodeLogSave.setlog1(item, processName, status, null, uuid);

		List<Middleware> data1;
		String value[] = item.getNext().split(",");

		List<String> nextNodes = Arrays.asList(value);

		// data1 = nodeInstanceRepo.getAllFoParallelTask(nextNodes, processName);
		data1 = middRepo.getAllFoParallelTask(nextNodes, processName);

		int threadCount = data1.size();
		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		for (Middleware condition : data1) {
			ThreadClass1 threadClass1 = new ThreadClass1(processName, condition, nodeInstanceRepo, processStartWork,
					processNodesWhile, nodeLogSave, uuid);
			executorService.submit(threadClass1);
		}
		executorService.shutdown();
	}
}
