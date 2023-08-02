package com.jbpm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jbpm.entity.ExclusiveGateway;



@Repository
public interface ExclusiveRepo extends JpaRepository<ExclusiveGateway, Long> {
	
	
}
