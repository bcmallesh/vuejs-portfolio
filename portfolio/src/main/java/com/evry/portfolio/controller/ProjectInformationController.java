package com.evry.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.evry.portfolio.model.ProjectInformationDto;
import com.evry.portfolio.service.ProjectInformationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProjectInformationController {

	@Autowired
	private ProjectInformationService projectInformationService;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/projectsinfo", method = RequestMethod.GET)
	public List<ProjectInformationDto> projectsInfoList() {
		return projectInformationService.findAll();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/projectsinfo/{id}", method = RequestMethod.GET)
	public ProjectInformationDto getProjectInfoById(@PathVariable(value = "id") Long id) {
		return projectInformationService.findById(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/createprojectinfo", method = RequestMethod.POST)
	public ProjectInformationDto saveProjectInfo(@RequestBody ProjectInformationDto project) {
		return projectInformationService.save(project);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/updateprojectinfo/{id}", method = RequestMethod.PUT)
	public ProjectInformationDto updateProjectInfo(@PathVariable(value = "id") Long id, @RequestBody ProjectInformationDto project) {
		project.setId(id);
		return projectInformationService.save(project);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/deleteprojectinfo/{id}", method = RequestMethod.DELETE)
	public void deleteProjectInfo(@PathVariable(value = "id") Long id) {
		projectInformationService.delete(id);

	}

}
