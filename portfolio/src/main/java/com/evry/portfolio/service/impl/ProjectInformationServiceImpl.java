package com.evry.portfolio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evry.portfolio.dao.ProjectDao;
import com.evry.portfolio.dao.ProjectInformationDao;
import com.evry.portfolio.entity.Project;
import com.evry.portfolio.entity.ProjectInformation;
import com.evry.portfolio.model.ProjectDto;
import com.evry.portfolio.model.ProjectInformationDto;
import com.evry.portfolio.service.ProjectInformationService;

@Service(value = "projectInformationService")
public class ProjectInformationServiceImpl implements ProjectInformationService {

	@Autowired
	private ProjectInformationDao projectInformationDao;
	@Autowired
	private ProjectDao projectIDao;

	public List<ProjectInformationDto> findAll() {
		List<ProjectInformationDto> list = new ArrayList<ProjectInformationDto>();
		for (ProjectInformation projectInfo : projectInformationDao.findAll()) {
			ProjectInformationDto dto = new ProjectInformationDto();

			dto.setId(projectInfo.getId());

			ProjectDto projectDto = new ProjectDto();

			projectDto.setId(projectInfo.getProject().getId());
			projectDto.setProjectName(projectInfo.getProject().getProjectName());
			dto.setProject(projectDto);
			dto.setProjectPhoto(projectInfo.getProjectPhoto());
			dto.setProjectPhotoName(projectInfo.getProjectPhotoName());
			
			list.add(dto);

		}
		return list;
	}

	@Override
	public void delete(long id) {
		projectInformationDao.deleteById(id);
	}

	@Override
	public ProjectInformationDto findById(Long id) {

		ProjectInformationDto dto = new ProjectInformationDto();
		ProjectInformation projectInfo = projectInformationDao.findById(id).get();
		if (null != projectInfo) {
			dto.setId(projectInfo.getId());

			ProjectDto projectDto = new ProjectDto();

			projectDto.setId(projectInfo.getProject().getId());
			projectDto.setProjectName(projectInfo.getProject().getProjectName());
			dto.setProject(projectDto);
			dto.setProjectPhoto(projectInfo.getProjectPhoto());
			dto.setProjectPhotoName(projectInfo.getProjectPhotoName());
		}
		return dto;
	}

	@Override
	public ProjectInformationDto save(ProjectInformationDto projectInfoDto) {
		ProjectInformationDto dto = new ProjectInformationDto();
		ProjectInformation projectInfo = new ProjectInformation();
		// if(null!=projectInfo){
		dto.setId(projectInfo.getId());

		Project project = projectIDao.findById(projectInfoDto.getProject().getId()).get();

		projectInfo.setId(projectInfo.getProject().getId());
		projectInfo.setProject(project);
		projectInfo.setProjectPhoto(projectInfo.getProjectPhoto());
		projectInfo.setProjectPhotoName(projectInfo.getProjectPhotoName());
		projectInfo = projectInformationDao.save(projectInfo);
		if (null != projectInfo) {
			dto.setId(projectInfo.getId());
			ProjectDto projectDto = new ProjectDto();
			projectDto.setId(projectInfo.getProject().getId());
			projectDto.setProjectName(projectInfo.getProject().getProjectName());
			dto.setProject(projectDto);
			dto.setProjectPhoto(projectInfo.getProjectPhoto());
			dto.setProjectPhotoName(projectInfo.getProjectPhotoName());
		}
		return dto;

	}

	@Override
	public ProjectInformationDto update(ProjectInformationDto projectInfoDto) {

		ProjectInformationDto dto = new ProjectInformationDto();
		ProjectInformation projectInfo = projectInformationDao.findById(projectInfoDto.getId()).get();
		if (null != projectInfo) {
			dto.setId(projectInfo.getId());

			Project project = projectIDao.findById(projectInfoDto.getProject().getId()).get();

			projectInfo.setId(projectInfo.getProject().getId());
			projectInfo.setProject(project);
			projectInfo.setProjectPhoto(projectInfo.getProjectPhoto());
			projectInfo.setProjectPhotoName(projectInfo.getProjectPhotoName());
			projectInfo = projectInformationDao.save(projectInfo);

			dto.setId(projectInfo.getId());
			ProjectDto projectDto = new ProjectDto();
			projectDto.setId(projectInfo.getProject().getId());
			projectDto.setProjectName(projectInfo.getProject().getProjectName());
			dto.setProject(projectDto);
			dto.setProjectPhoto(projectInfo.getProjectPhoto());
			dto.setProjectPhotoName(projectInfo.getProjectPhotoName());
		}

		return dto;

	}
}
