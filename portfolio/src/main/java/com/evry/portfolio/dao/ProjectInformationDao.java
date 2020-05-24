package com.evry.portfolio.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evry.portfolio.entity.ProjectInformation;

@Repository
public interface ProjectInformationDao extends CrudRepository<ProjectInformation, Long> {
	  
}
