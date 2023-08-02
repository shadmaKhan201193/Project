package com.jbpm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jbpm.entity.InclusiveGateway;

@Repository
public interface InclusiveRepo extends JpaRepository<InclusiveGateway, Long> {
	
	
}
