package com.evry.portfolio.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evry.portfolio.dao.ProjectDao;

import com.evry.portfolio.entity.Project;
import com.evry.portfolio.entity.Section;
import com.evry.portfolio.model.ProjectDto;
import com.evry.portfolio.model.ProjectSummary;
import com.evry.portfolio.model.SectionDto;
import com.evry.portfolio.model.SectionSummary;
import com.evry.portfolio.model.TagDto;
import com.evry.portfolio.service.ProjectService;
import com.evry.portfolio.util.FileStroreUtil;


@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	@Autowired
	ServletContext context;
	@Autowired
	HttpServletRequest request;
	@Autowired
	private Environment env1;
	
	public List<ProjectDto> findAll() {
		List<ProjectDto> list = new ArrayList<ProjectDto>();
		
		//project image
		String[] requestStringArray = request.getRequestURL().toString().split("/");
		String applicationPath = requestStringArray[0] + "//" + requestStringArray[2] + "/" + requestStringArray[3];
		System.out.println("applicationPath======>" + applicationPath);
		FileStroreUtil fsu = new FileStroreUtil();
		
		for (Project project : projectDao.findAll()) {
			ProjectDto dto = new ProjectDto();

			dto.setId(project.getId());
			
			
			try {
				if (null != project.getThumnailImage()) {
					byte[] thumbnailPhotoBytes = project.getThumnailImage().getBytes(1l, (int) project.getThumnailImage().length());

					fsu.storeFile(context, thumbnailPhotoBytes, "project-thumbnail-"+project.getId()+"-"+project.getThumnailimageName(),env1);
					dto.setImage(project.getThumnailimageName());
					dto.setImagePath(applicationPath + "/photos/projectimages/" + "project-thumbnail-"+project.getId()+"-"+project.getThumnailimageName());
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}

			try {
				if (null != project.getHeroImage()) {
					byte[] heroPhotoBytes = project.getHeroImage().getBytes(1l, (int) project.getHeroImage().length());

					fsu.storeFile(context, heroPhotoBytes, "project-hero-"+project.getId()+"-"+project.getHeroimageName(),env1);
					dto.setHeroImage(project.getHeroimageName());
					dto.setHeroImagePath(applicationPath + "/photos/projectimages/" + "project-hero-"+project.getId()+"-"+project.getHeroimageName());
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
			
			dto.setName(project.getProjectName()); 
			dto.setDescription(project.getShortSummary());
			
			if(null!=project.getScopeofworks()){
				String [] scopOfWorkArray=project.getScopeofworks().split(",");
			     ArrayList<String> scopeofworkList=new ArrayList<String>(Arrays.asList(scopOfWorkArray));
			     dto.setScopeofworks(scopeofworkList);
			}
			
			if(null!=project.getWorks()){
				String [] workArray=project.getWorks().split(",");
			     ArrayList<String> workList=new ArrayList<String>(Arrays.asList(workArray));
			     dto.setWorks(workList);
			}
			if(null!=project.getTypes()){
				String [] typeArray=project.getTypes().split(",");
			     ArrayList<String> typeList=new ArrayList<String>(Arrays.asList(typeArray));
			     dto.setTypes(typeList);
			}
			if(null!=project.getIndustrys()){
				String [] industryArray=project.getIndustrys().split(",");
			     ArrayList<String> industryArrayList=new ArrayList<String>(Arrays.asList(industryArray));
			     dto.setIndustrys(industryArrayList);
			}
			
			
			list.add(dto);

			
		}
		return list;
	}
	@Override
	public ProjectSummary findById(Long id) {
		
		Project projectEntity = projectDao.findById(id).get();

ProjectSummary projectSummary=new ProjectSummary();
SectionSummary section_summary=new SectionSummary();

 ArrayList<SectionDto> sections=new ArrayList<SectionDto>();



//project image
String[] requestStringArray = request.getRequestURL().toString().split("/");
String applicationPath = requestStringArray[0] + "//" + requestStringArray[2] + "/" + requestStringArray[3];
System.out.println("applicationPath======>" + applicationPath);
FileStroreUtil fsu = new FileStroreUtil();
try {
	if (null != projectEntity.getThumnailImage()) {
		byte[] thumbnailPhotoBytes = projectEntity.getThumnailImage().getBytes(1l, (int) projectEntity.getThumnailImage().length());

		fsu.storeFile(context, thumbnailPhotoBytes, "project-thumbnail-"+projectEntity.getId()+"-"+projectEntity.getThumnailimageName(),env1);
		section_summary.setSummary_image(applicationPath + "/photos/projectimages/" + "project-thumbnail-"+projectEntity.getId()+"-"+projectEntity.getThumnailimageName());
	}
} catch (SQLException sqlex) {
	sqlex.printStackTrace();
}

try {
	if (null != projectEntity.getHeroImage()) {
		byte[] heroPhotoBytes = projectEntity.getHeroImage().getBytes(1l, (int) projectEntity.getHeroImage().length());

		fsu.storeFile(context, heroPhotoBytes, "project-hero-"+projectEntity.getId()+"-"+projectEntity.getHeroimageName(),env1);
		section_summary.setSummary_hero_image(applicationPath + "/photos/projectimages/" + "project-hero-"+projectEntity.getId()+"-"+projectEntity.getHeroimageName());
	}
} catch (SQLException sqlex) {
	sqlex.printStackTrace();
}

section_summary.setSummary_heading(projectEntity.getProjectName());

String [] tagsArray=projectEntity.getScopeofworks().split(",");
ArrayList<TagDto> tags=new ArrayList<TagDto>();
for(String tag:tagsArray){
	TagDto tagDto=new TagDto();
	tagDto.setMessage(tag);
	tags.add(tagDto);
}

projectEntity.getSections().forEach(section->{
	SectionDto sectionDto=new SectionDto();
	//section image
	
	try {
		if (null != section.getSectionimage()) {
			byte[] sectionPhotoBytes = section.getSectionimage().getBytes(1l, (int) section.getSectionimage().length());

			fsu.storeFile(context, sectionPhotoBytes, "section-"+projectEntity.getId()+"-"+section.getSectionimageName(),env1);
			sectionDto.setImagePath(applicationPath + "/photos/projectimages/" + "section-"+projectEntity.getId()+"-"+section.getSectionimageName());
		}
	} catch (SQLException sqlex) {
		sqlex.printStackTrace();
	}
	sectionDto.setSectionId(section.getId());
	sectionDto.setHeading(section.getSectionTitle());
	sectionDto.setDescription(section.getSectionContent());
	sectionDto.setSectionLayout(section.getSectionLayout());
	sections.add(sectionDto);
});
projectSummary.setProject_name(projectEntity.getProjectName());
projectSummary.setSection_summary(section_summary);
projectSummary.setTags(tags);
projectSummary.setSections(sections);

		return projectSummary;
	}

	@Override
	public ProjectDto save(ProjectDto projectDto) {
		Project projectEntity = new Project();
		
		projectEntity.setCategorys(projectDto.getCategory());
		projectEntity.setHeroImage(projectDto.getHeroBlob());
		projectEntity.setHeroimageName(projectDto.getHeroimageName());
		projectEntity.setIndustrys(StringUtils.collectionToDelimitedString(projectDto.getIndustrys(), ","));
		projectEntity.setProjectName(projectDto.getProjectName());
		projectEntity.setScopeofworks(StringUtils.collectionToDelimitedString(projectDto.getScopeofworks(), ","));
		
		
		projectEntity.setShortSummary(projectDto.getShortSummary());
		projectEntity.setThumnailImage(projectDto.getThumnailBlob());
		projectEntity.setThumnailimageName(projectDto.getThumnailimageName());
		projectEntity.setTypes(StringUtils.collectionToDelimitedString(projectDto.getTypes(), ","));
		projectEntity.setWorks(StringUtils.collectionToDelimitedString(projectDto.getWorks(), ","));
		
		Set<Section> sections=new HashSet<Section>();
		projectDto.getSections().forEach(sectionDto->{
			Section sectionEntity=new Section();
			sectionEntity.setSectionContent(sectionDto.getSectionContent());
			sectionEntity.setSectionimage(sectionDto.getSectionimage());
			sectionEntity.setSectionimageName(sectionDto.getSectionimageName());
			sectionEntity.setSectionLayout(sectionDto.getSectionLayout());
			sectionEntity.setSectionTitle(sectionDto.getSectionTitle());
			sectionEntity.setProjectId(projectEntity);
			
			sections.add(sectionEntity);
		});
		
		
		projectEntity.setSections(sections);


		Project createdProjectEntity = projectDao.save(projectEntity);

		
		projectDto.setHeroBlob(null);
		projectDto.setThumnailBlob(null);
		projectDto.getSections().forEach(item->{
			item.setSectionimage(null);
		});
		
		return projectDto;
	}

	
	
	@Override
	public ProjectSummary preview(ProjectDto projectDto) {
		//Project projectEntity = projectDao.findById(id).get();

		ProjectSummary projectSummary=new ProjectSummary();
		SectionSummary section_summary=new SectionSummary();

		 ArrayList<SectionDto> sections=new ArrayList<SectionDto>();



		//project image
		String[] requestStringArray = request.getRequestURL().toString().split("/");
		String applicationPath = requestStringArray[0] + "//" + requestStringArray[2] + "/" + requestStringArray[3];
		System.out.println("applicationPath======>" + applicationPath);
		FileStroreUtil fsu = new FileStroreUtil();
		try {
			if (null != projectDto.getThumnailBlob()) {
				byte[] thumbnailPhotoBytes = projectDto.getThumnailBlob().getBytes(1l, (int) projectDto.getThumnailBlob().length());

				fsu.storeFile(context, thumbnailPhotoBytes, "project-thumbnail-"+projectDto.getThumnailimageName(),env1);
				section_summary.setSummary_image(applicationPath + "/photos/projectimages/" + "project-thumbnail-"+projectDto.getThumnailimageName());
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		try {
			if (null != projectDto.getHeroImage()) {
				byte[] heroPhotoBytes = projectDto.getHeroBlob().getBytes(1l, (int) projectDto.getHeroBlob().length());

				fsu.storeFile(context, heroPhotoBytes, "project-hero-"+projectDto.getHeroimageName(),env1);
				section_summary.setSummary_hero_image(applicationPath + "/photos/projectimages/" + "project-hero-"+projectDto.getHeroimageName());
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		section_summary.setSummary_heading(projectDto.getProjectName());

		//String [] tagsArray=projectDto.getScopeofworks().split(",");
		ArrayList<TagDto> tags=new ArrayList<TagDto>();
		for(String tag:projectDto.getScopeofworks()){
			TagDto tagDto=new TagDto();
			tagDto.setMessage(tag);
			tags.add(tagDto);
		}

		projectDto.getSections().forEach(section->{
			SectionDto sectionDto=new SectionDto();
			//section image
			
			try {
				if (null != section.getSectionimage()) {
					byte[] sectionPhotoBytes = section.getSectionimage().getBytes(1l, (int) section.getSectionimage().length());

					fsu.storeFile(context, sectionPhotoBytes, "section-"+section.getSectionimageName(),env1);
					sectionDto.setImagePath(applicationPath + "/photos/projectimages/" + "section-"+section.getSectionimageName());
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
			sectionDto.setSectionId(section.getId());
			sectionDto.setHeading(section.getSectionTitle());
			sectionDto.setDescription(section.getSectionContent());
			sectionDto.setSectionLayout(section.getSectionLayout());
			sections.add(sectionDto);
		});
		projectSummary.setProject_name(projectDto.getProjectName());
		projectSummary.setSection_summary(section_summary);
		projectSummary.setTags(tags);
		projectSummary.setSections(sections);

				return projectSummary;
	}
	
}
