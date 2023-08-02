package com.jbpm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jbpm.entity.ParallelGateway;

@Repository
public interface ParallelRepo extends JpaRepository<ParallelGateway, Long> {
	
	
}
