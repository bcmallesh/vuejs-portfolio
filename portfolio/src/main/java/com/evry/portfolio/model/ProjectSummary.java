package com.evry.portfolio.model;

import java.util.ArrayList;

public class ProjectSummary {
	private Long id;
	private String project_name;
	private SectionSummary section_summary;
	private ArrayList<TagDto> tags=new ArrayList<TagDto>();
	private ArrayList<SectionDto> sections=new ArrayList<SectionDto>();
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public SectionSummary getSection_summary() {
		return section_summary;
	}
	public void setSection_summary(SectionSummary section_summary) {
		this.section_summary = section_summary;
	}
	public ArrayList<TagDto> getTags() {
		return tags;
	}
	public void setTags(ArrayList<TagDto> tags) {
		this.tags = tags;
	}
	public ArrayList<SectionDto> getSections() {
		return sections;
	}
	public void setSections(ArrayList<SectionDto> sections) {
		this.sections = sections;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

}
