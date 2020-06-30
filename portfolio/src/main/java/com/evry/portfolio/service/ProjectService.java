package com.evry.portfolio.service;


import java.util.List;

import com.evry.portfolio.model.ProjectDto;
import com.evry.portfolio.model.ProjectSummary;


public interface ProjectService {
	ProjectDto save(ProjectDto project);
/*	ProjectDto update(ProjectDto project);
   
    void delete(long id);
    ProjectDto findById(Long id);
    ProjectDto findByName(String name);*/
	 List<ProjectDto> findAll();
	ProjectSummary findById(Long id);

	ProjectSummary preview(ProjectDto projectDto) ;
}
