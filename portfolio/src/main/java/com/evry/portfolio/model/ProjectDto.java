package com.evry.portfolio.model;

import java.util.ArrayList;
import java.sql.Blob;
public class ProjectDto {

	private Long id;
	private String projectName;
	private Blob thumnailBlob;
	private String thumnailimageName;
	private Blob heroBlob;
	private String heroimageName;
    private String shortSummary;
    private String category;
    private ArrayList<String> works=new ArrayList<String>();
    private ArrayList<String> types=new ArrayList<String>();
    private ArrayList<String> industrys=new ArrayList<String>();
    private ArrayList<String> scopeofworks=new ArrayList<String>();
    private ArrayList<SectionDto> sections=new ArrayList<SectionDto>();
    private String work=new String();
    private String type=new String();
    private String industry=new String();
    
    private String image;
    private String imagePath;
    private String heroImage;
    private String heroImagePath;
    private String name;
    private String description;
    
    private String pictureInput1;
    private String pictureInput2;
    
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
	public Blob getThumnailBlob() {
		return thumnailBlob;
	}
	public void setThumnailBlob(Blob thumnailBlob) {
		this.thumnailBlob = thumnailBlob;
	}
	public String getThumnailimageName() {
		return thumnailimageName;
	}
	public void setThumnailimageName(String thumnailimageName) {
		this.thumnailimageName = thumnailimageName;
	}
	public Blob getHeroBlob() {
		return heroBlob;
	}
	public void setHeroBlob(Blob heroBlob) {
		this.heroBlob = heroBlob;
	}
	public String getHeroimageName() {
		return heroimageName;
	}
	public void setHeroimageName(String heroimageName) {
		this.heroimageName = heroimageName;
	}
	public String getShortSummary() {
		return shortSummary;
	}
	public void setShortSummary(String shortSummary) {
		this.shortSummary = shortSummary;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public ArrayList<String> getWorks() {
		return works;
	}
	public void setWorks(ArrayList<String> works) {
		this.works = works;
	}
	public ArrayList<String> getTypes() {
		return types;
	}
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	public ArrayList<String> getIndustrys() {
		return industrys;
	}
	public void setIndustrys(ArrayList<String> industrys) {
		this.industrys = industrys;
	}
	public ArrayList<String> getScopeofworks() {
		return scopeofworks;
	}
	public void setScopeofworks(ArrayList<String> scopeofworks) {
		this.scopeofworks = scopeofworks;
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
	public String getHeroImage() {
		return heroImage;
	}
	public void setHeroImage(String heroImage) {
		this.heroImage = heroImage;
	}
	public String getHeroImagePath() {
		return heroImagePath;
	}
	public void setHeroImagePath(String heroImagePath) {
		this.heroImagePath = heroImagePath;
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
	public ArrayList<SectionDto> getSections() {
		return sections;
	}
	public void setSections(ArrayList<SectionDto> sections) {
		this.sections = sections;
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
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getPictureInput1() {
		return pictureInput1;
	}
	public void setPictureInput1(String pictureInput1) {
		this.pictureInput1 = pictureInput1;
	}
	public String getPictureInput2() {
		return pictureInput2;
	}
	public void setPictureInput2(String pictureInput2) {
		this.pictureInput2 = pictureInput2;
	}
	@Override
	public String toString() {
		return "ProjectDto [id=" + id + ", projectName=" + projectName + ", thumnailBlob=" + thumnailBlob
				+ ", thumnailimageName=" + thumnailimageName + ", heroBlob=" + heroBlob + ", heroimageName="
				+ heroimageName + ", shortSummary=" + shortSummary + ", category=" + category + ", works=" + works
				+ ", types=" + types + ", industrys=" + industrys + ", scopeofworks=" + scopeofworks + ", sections="
				+ sections + ", work=" + work + ", type=" + type + ", industry=" + industry + ", image=" + image
				+ ", imagePath=" + imagePath + ", heroImage=" + heroImage + ", heroImagePath=" + heroImagePath
				+ ", name=" + name + ", description=" + description + ", pictureInput1=" + pictureInput1
				+ ", pictureInput2=" + pictureInput2 + "]";
	}
    
}
