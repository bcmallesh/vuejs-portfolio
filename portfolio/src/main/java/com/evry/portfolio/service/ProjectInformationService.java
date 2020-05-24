package com.evry.portfolio.service;


import java.util.List;

import com.evry.portfolio.model.ProjectInformationDto;


public interface ProjectInformationService {
	ProjectInformationDto save(ProjectInformationDto projectInfo);
	ProjectInformationDto update(ProjectInformationDto projectInfo);
    List<ProjectInformationDto> findAll();
    void delete(long id);
    ProjectInformationDto findById(Long id);
}
