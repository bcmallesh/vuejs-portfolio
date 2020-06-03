package com.evry.portfolio.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evry.portfolio.entity.Project;



@Repository
public interface ProjectDao extends CrudRepository<Project, Long> {
	 @Query ("SELECT project FROM Project project  where project.projectName=:name")
	    public Project findByName(@Param("name") String name);
	 
}
