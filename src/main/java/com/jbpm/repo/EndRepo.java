package com.jbpm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jbpm.entity.EndTask;


@Repository
public interface EndRepo extends JpaRepository<EndTask, Long> {

}
