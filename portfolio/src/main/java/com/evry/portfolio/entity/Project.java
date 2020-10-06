package com.evry.portfolio.entity;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "PROJECT")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "PROJECT_NAME")
	private String projectName;
	@Column(name = "THUMBNAIL_IMAGE")
	private Blob thumnailImage;
	@Column(name = "THUMBNAIL_IMAGE_NAME")
	private String thumnailimageName;
	@Column(name = "HERO_IMAGE")
	private Blob heroImage;
	@Column(name = "HERO_IMAGE_NAME")
	private String heroimageName;
	@Column(name = "SCOPE_OF_WORKS")
	private String scopeofworks;
	@Column(name = "SHORT_SUMMARY", columnDefinition="LONGTEXT")
	private String shortSummary;
	@Column(name = "CATEGORYS")
	private String categorys;
	@Column(name = "WORKS")
	private String works;
	@Column(name = "TYPES")
	private String types;
	@Column(name = "INDUSTRYS")
	private String industrys;
	@OneToMany(mappedBy = "projectId", cascade= CascadeType.ALL, orphanRemoval = true)
	private List<Section> sections = new ArrayList<Section>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Blob getThumnailImage() {
		return thumnailImage;
	}

	public void setThumnailImage(Blob thumnailImage) {
		this.thumnailImage = thumnailImage;
	}

	public String getThumnailimageName() {
		return thumnailimageName;
	}

	public void setThumnailimageName(String thumnailimageName) {
		this.thumnailimageName = thumnailimageName;
	}

	public Blob getHeroImage() {
		return heroImage;
	}

	public void setHeroImage(Blob heroImage) {
		this.heroImage = heroImage;
	}

	public String getHeroimageName() {
		return heroimageName;
	}

	public void setHeroimageName(String heroimageName) {
		this.heroimageName = heroimageName;
	}

	public String getScopeofworks() {
		return scopeofworks;
	}

	public void setScopeofworks(String scopeofworks) {
		this.scopeofworks = scopeofworks;
	}

	public String getShortSummary() {
		return shortSummary;
	}

	public void setShortSummary(String shortSummary) {
		this.shortSummary = shortSummary;
	}

	public String getCategorys() {
		return categorys;
	}

	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}

	public String getWorks() {
		return works;
	}

	public void setWorks(String works) {
		this.works = works;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getIndustrys() {
		return industrys;
	}

	public void setIndustrys(String industrys) {
		this.industrys = industrys;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

}
