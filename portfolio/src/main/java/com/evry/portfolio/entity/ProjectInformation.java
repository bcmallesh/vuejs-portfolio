package com.evry.portfolio.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="PROJECT_INFORMATION")
public class ProjectInformation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @OneToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;
    
    @Column(name = "PROJECT_PHOTO")
	private Blob projectPhoto;
	@Column(name = "PROJECT_PHOTO_NAME")
	private String projectPhotoName;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "WORK")
	private String work;
	
	@Column(name = "TYPE")
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
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
	
	

}
