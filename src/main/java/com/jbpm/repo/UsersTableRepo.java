package com.jbpm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jbpm.entity.Users;


@Repository
public interface UsersTableRepo extends JpaRepository<Users, Long> {
	
	public Users findByUserName(String userName); 
	

	@Query(value = "SELECT * FROM Users WHERE taskcount = (SELECT MIN(taskcount) FROM Users)", nativeQuery = true)
	public Users getAllminCountUser();
	
	
	@Query(value = "select userName  from Users u  where userRoleName = :userRoleName  order by userName asc", nativeQuery = true)
	public String[] getAllUser(String userRoleName);



}
