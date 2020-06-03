package com.evry.portfolio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evry.portfolio.dao.ProjectDao;

import com.evry.portfolio.entity.Project;
import com.evry.portfolio.model.ProjectDto;
import com.evry.portfolio.service.ProjectService;

@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	public List<ProjectDto> findAll() {
		List<ProjectDto> list = new ArrayList<ProjectDto>();
		for (Project project : projectDao.findAll()) {
			ProjectDto dto = new ProjectDto();

			dto.setId(project.getId());
			dto.setName(project.getProjectName());
			dto.setIndustry(project.getIndustry());

			
			list.add(dto);

		}
		return list;
	}

	@Override
	public void delete(long id) {
		projectDao.deleteById(id);
	}


	@Override
	public ProjectDto findById(Long id) {

		Project project = projectDao.findById(id).get();
		ProjectDto projectDto = new ProjectDto();
		projectDto.setId(project.getId());
		projectDto.setName(project.getProjectName());
		projectDto.setIndustry(project.getIndustry());

		return projectDto;
	}

	@Override
	public ProjectDto save(ProjectDto projectDto) {
		Project projectEntity = new Project();
		projectEntity.setProjectName(projectDto.getName());
		projectEntity.setIndustry(projectDto.getIndustry());

		Project createdProjectEntity = projectDao.save(projectEntity);

		ProjectDto pdto = new ProjectDto();
		pdto.setId(createdProjectEntity.getId());
		pdto.setName(createdProjectEntity.getProjectName());
		pdto.setIndustry(createdProjectEntity.getIndustry());

		return pdto;
	}

	@Override
	public ProjectDto update(ProjectDto projectDto) {

		Project projectEntity = projectDao.findById(projectDto.getId()).get();
		projectEntity.setProjectName(projectDto.getName());
		projectEntity.setIndustry(projectDto.getIndustry());

		Project createdProjectEntity = projectDao.save(projectEntity);

		ProjectDto pdto = new ProjectDto();
		pdto.setId(createdProjectEntity.getId());
		pdto.setName(createdProjectEntity.getProjectName());
		pdto.setIndustry(createdProjectEntity.getIndustry());

		return pdto;
	}
	@Override
	public ProjectDto findByName(String name) {

		Project project = projectDao.findByName(name);
		ProjectDto projectDto = new ProjectDto();
		projectDto.setId(project.getId());
		projectDto.setName(project.getProjectName());
		projectDto.setIndustry(project.getIndustry());

		return projectDto;
	}
}
