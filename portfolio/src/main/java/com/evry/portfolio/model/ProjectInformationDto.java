
package com.evry.portfolio.model;

import java.sql.Blob;

public class ProjectInformationDto {


    private Long id;
	private String image;
	private String imagePath;
    private String name;
    private String description;
	private String work;
	private String type;
	private String industry;

	private ProjectDto project;
	private Blob projectPhoto;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	
}
