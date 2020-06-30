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
@Table(name="SECTION")
public class Section {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SECTION_TITLE")
	private String sectionTitle;
    @Column(name = "SECTION_LAYOUT")
	private String sectionLayout;
    @Column(name = "SECTION_CONTENT")
	private String sectionContent;
    @Column(name = "SECTION_IMAGE")
	private Blob sectionimage;
    @Column(name = "SECTION_IMAGE_NAME")
	private String sectionimageName;
    @OneToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project projectId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSectionTitle() {
		return sectionTitle;
	}
	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}
	public String getSectionLayout() {
		return sectionLayout;
	}
	public void setSectionLayout(String sectionLayout) {
		this.sectionLayout = sectionLayout;
	}
	public String getSectionContent() {
		return sectionContent;
	}
	public void setSectionContent(String sectionContent) {
		this.sectionContent = sectionContent;
	}
	public Blob getSectionimage() {
		return sectionimage;
	}
	public void setSectionimage(Blob sectionimage) {
		this.sectionimage = sectionimage;
	}
	public String getSectionimageName() {
		return sectionimageName;
	}
	public void setSectionimageName(String sectionimageName) {
		this.sectionimageName = sectionimageName;
	}
	public Project getProjectId() {
		return projectId;
	}
	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}
    
}
