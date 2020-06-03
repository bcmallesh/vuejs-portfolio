package com.evry.portfolio.service;


import java.util.List;

import com.evry.portfolio.model.ProjectDto;


public interface ProjectService {
	ProjectDto save(ProjectDto project);
	ProjectDto update(ProjectDto project);
    List<ProjectDto> findAll();
    void delete(long id);
    ProjectDto findById(Long id);
    ProjectDto findByName(String name);
}
