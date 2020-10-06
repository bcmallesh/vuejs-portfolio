package com.evry.portfolio.model;

import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

public class SectionDto {

	private Long id;
	private String sectionTitle;
	private String sectionLayout;
	private String sectionContent;
	private Blob sectionimage;
	private String sectionimageName;
	private Integer sectionImageIndex;
	private String sectionImagePath;
	private MultipartFile sectionMultipartFile;
	//private String sectionImageName
	private String pictureInputSection;
	Long sectionId;
	String imagePath;
	String heading;
	String description;
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
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPictureInputSection() {
		return pictureInputSection;
	}
	public void setPictureInputSection(String pictureInputSection) {
		this.pictureInputSection = pictureInputSection;
	}
	public Integer getSectionImageIndex() {
		return sectionImageIndex;
	}
	public void setSectionImageIndex(Integer sectionImageIndex) {
		this.sectionImageIndex = sectionImageIndex;
	}
	public String getSectionImagePath() {
		return sectionImagePath;
	}
	public void setSectionImagePath(String sectionImagePath) {
		this.sectionImagePath = sectionImagePath;
	}
	public MultipartFile getSectionMultipartFile() {
		return sectionMultipartFile;
	}
	public void setSectionMultipartFile(MultipartFile sectionMultipartFile) {
		this.sectionMultipartFile = sectionMultipartFile;
	}
	
}
