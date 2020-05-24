
package com.evry.portfolio.model;

import java.sql.Blob;


public class ProjectInformationDto {


    private Long id;
   
    private ProjectDto project;
    
	private Blob projectPhoto;
	private String projectPhotoName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProjectDto getProject() {
		return project;
	}
	public void setProject(ProjectDto project) {
		this.project = project;
	}
	public Blob getProjectPhoto() {
		return projectPhoto;
	}
	public void setProjectPhoto(Blob projectPhoto) {
		this.projectPhoto = projectPhoto;
	}
	public String getProjectPhotoName() {
		return projectPhotoName;
	}
	public void setProjectPhotoName(String projectPhotoName) {
		this.projectPhotoName = projectPhotoName;
	}
	
	
	

}
