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

import com.evry.portfolio.model.ProjectDto;
import com.evry.portfolio.service.ProjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public List<ProjectDto> projectsList() {
		return projectService.findAll();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public ProjectDto getProjectById(@PathVariable(value = "id") Long id) {
		return projectService.findById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/createproject", method = RequestMethod.POST)
	public ProjectDto saveProject(@RequestBody ProjectDto project) {
		return projectService.save(project);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/updateproject/{id}", method = RequestMethod.PUT)
	public ProjectDto updateProject(@PathVariable(value = "id") Long id, @RequestBody ProjectDto project) {
		project.setId(id);
		return projectService.update(project);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/deleteproject/{id}", method = RequestMethod.DELETE)
	public void deleteProject(@PathVariable(value = "id") Long id) {
		projectService.delete(id);

	}

}
