package com.evry.portfolio.service;

import java.util.List;

import com.evry.portfolio.model.ProjectDto;
import com.evry.portfolio.model.ProjectSummary;

public interface ProjectService {
	ProjectDto save(ProjectDto project);

	List<ProjectDto> findAll();

	ProjectSummary findById(Long id);
	ProjectDto findByIdForEdit(Long id);
	ProjectSummary preview(ProjectDto projectDto);

	ProjectDto delete(Long id);
 ProjectDto update(Long projectId,ProjectDto projectDto);
}
