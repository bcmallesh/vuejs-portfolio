package com.evry.portfolio.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.evry.portfolio.dao.ProjectDao;
import com.evry.portfolio.dao.ProjectInformationDao;
import com.evry.portfolio.entity.Project;
import com.evry.portfolio.entity.ProjectInformation;
import com.evry.portfolio.model.ProjectDto;
import com.evry.portfolio.model.ProjectInformationDto;
import com.evry.portfolio.service.ProjectInformationService;
import com.evry.portfolio.util.FileStroreUtil;


@Service(value = "projectInformationService")
public class ProjectInformationServiceImpl implements ProjectInformationService {

	@Autowired
	private ProjectInformationDao projectInformationDao;
	@Autowired
	private ProjectDao projectIDao;
	@Autowired
	HttpServletRequest request;
	@Autowired
	ServletContext context;
	@Autowired
	private Environment env;
	public List<ProjectInformationDto> findAll() {
		List<ProjectInformationDto> list = new ArrayList<ProjectInformationDto>();
		for (ProjectInformation projectInfo : projectInformationDao.findAll()) {
			ProjectInformationDto dto = new ProjectInformationDto();

			dto.setId(projectInfo.getId());
			dto.setName(projectInfo.getProject().getProjectName());
			dto.setDescription(projectInfo.getDescription());
			dto.setType(projectInfo.getType());
			dto.setWork(projectInfo.getWork());
			dto.setIndustry(projectInfo.getProject().getIndustry());
			String[] requestStringArray = request.getRequestURL().toString().split("/");
			String applicationPath = requestStringArray[0] + "//" + requestStringArray[2] + "/" + requestStringArray[3];
			System.out.println("applicationPath======>" + applicationPath);
			FileStroreUtil fsu = new FileStroreUtil();
			try {
				if (null != projectInfo.getProjectPhoto()) {
					byte[] shopPhotoBytes = projectInfo.getProjectPhoto().getBytes(1l, (int) projectInfo.getProjectPhoto().length());

					fsu.storeFile(context, shopPhotoBytes, "project-"+projectInfo.getId()+"-"+projectInfo.getProjectPhotoName(),env);
					dto.setImagePath(applicationPath + "/photos/projectinfoimages/" + "project-"+projectInfo.getId()+"-"+projectInfo.getProjectPhotoName());
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
			dto.setImage(projectInfo.getProjectPhotoName());
			
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
			//ProjectInformationDto dto = new ProjectInformationDto();

			dto.setId(projectInfo.getId());
			dto.setName(projectInfo.getProject().getProjectName());
			dto.setDescription(projectInfo.getDescription());
			dto.setType(projectInfo.getType());
			dto.setWork(projectInfo.getWork());
			dto.setIndustry(projectInfo.getProject().getIndustry());
			String[] requestStringArray = request.getRequestURL().toString().split("/");
			String applicationPath = requestStringArray[0] + "//" + requestStringArray[2] + "/" + requestStringArray[3];
			System.out.println("applicationPath======>" + applicationPath);
			FileStroreUtil fsu = new FileStroreUtil();
			try {
				if (null != projectInfo.getProjectPhoto()) {
					byte[] shopPhotoBytes = projectInfo.getProjectPhoto().getBytes(1l, (int) projectInfo.getProjectPhoto().length());

					fsu.storeFile(context, shopPhotoBytes, "project-"+projectInfo.getId()+"-"+projectInfo.getProjectPhotoName(),env);
					dto.setImagePath(applicationPath + "/photos/projectinfoimages/" + "project-"+projectInfo.getId()+"-"+projectInfo.getProjectPhotoName());
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
			dto.setImage(projectInfo.getProjectPhotoName());
			
		}
		return dto;
	}

	@Override
	public ProjectInformationDto save(ProjectInformationDto projectInfoDto) {
		ProjectInformationDto dto = new ProjectInformationDto();
		ProjectInformation projectInfo = new ProjectInformation();
		Project project = projectIDao.findById(projectInfoDto.getProject().getId()).get();
		projectInfo.setId(projectInfo.getProject().getId());
		projectInfo.setProject(project);
		projectInfo.setProjectPhoto(projectInfo.getProjectPhoto());
		projectInfo.setProjectPhotoName(projectInfo.getProjectPhotoName());
		projectInfo.setDescription(projectInfo.getDescription());
		projectInfo.setType(projectInfo.getType());
		projectInfo.setWork(projectInfo.getWork());
		projectInfo = projectInformationDao.save(projectInfo);
		if (null != projectInfo) {
			dto.setId(projectInfo.getId());
			dto.setName(projectInfo.getProject().getProjectName());
			dto.setDescription(projectInfo.getDescription());
			dto.setType(projectInfo.getType());
			dto.setWork(projectInfo.getWork());
			dto.setIndustry(projectInfo.getProject().getIndustry());
			String[] requestStringArray = request.getRequestURL().toString().split("/");
			String applicationPath = requestStringArray[0] + "//" + requestStringArray[2] + "/" + requestStringArray[3];
			System.out.println("applicationPath======>" + applicationPath);
			FileStroreUtil fsu = new FileStroreUtil();
			try {
				if (null != projectInfo.getProjectPhoto()) {
					byte[] shopPhotoBytes = projectInfo.getProjectPhoto().getBytes(1l, (int) projectInfo.getProjectPhoto().length());

					fsu.storeFile(context, shopPhotoBytes, "project-"+projectInfo.getId()+"-"+projectInfo.getProjectPhotoName(),env);
					dto.setImagePath(applicationPath + "/photos/projectinfoimages/" + "project-"+projectInfo.getId()+"-"+projectInfo.getProjectPhotoName());
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
			dto.setImage(projectInfo.getProjectPhotoName());
			
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
			projectInfo.setDescription(projectInfo.getDescription());
			projectInfo.setType(projectInfo.getType());
			projectInfo.setWork(projectInfo.getWork());
			projectInfo = projectInformationDao.save(projectInfo);

			//ProjectInformationDto dto = new ProjectInformationDto();

			dto.setId(projectInfo.getId());
			dto.setName(projectInfo.getProject().getProjectName());
			dto.setDescription(projectInfo.getDescription());
			dto.setType(projectInfo.getType());
			dto.setWork(projectInfo.getWork());
			dto.setIndustry(projectInfo.getProject().getIndustry());
			String[] requestStringArray = request.getRequestURL().toString().split("/");
			String applicationPath = requestStringArray[0] + "//" + requestStringArray[2] + "/" + requestStringArray[3];
			System.out.println("applicationPath======>" + applicationPath);
			FileStroreUtil fsu = new FileStroreUtil();
			try {
				if (null != projectInfo.getProjectPhoto()) {
					byte[] shopPhotoBytes = projectInfo.getProjectPhoto().getBytes(1l, (int) projectInfo.getProjectPhoto().length());

					fsu.storeFile(context, shopPhotoBytes, "project-"+projectInfo.getId()+"-"+projectInfo.getProjectPhotoName(),env);
					dto.setImagePath(applicationPath + "/photos/projectinfoimages/" + "project-"+projectInfo.getId()+"-"+projectInfo.getProjectPhotoName());
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
			dto.setImage(projectInfo.getProjectPhotoName());
			
		}

		return dto;

	}
}
