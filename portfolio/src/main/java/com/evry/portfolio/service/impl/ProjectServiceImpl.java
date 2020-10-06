package com.evry.portfolio.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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

		// project image
		String[] requestStringArray = request.getRequestURL().toString().split("/");
		String applicationPath = requestStringArray[0] + "//" + requestStringArray[2] + "/" + requestStringArray[3];
		System.out.println("applicationPath======>" + applicationPath);
		FileStroreUtil fsu = new FileStroreUtil();

		for (Project project : projectDao.findAll()) {
			ProjectDto dto = new ProjectDto();

			dto.setId(project.getId());

			try {
				if (null != project.getThumnailImage()) {
					byte[] thumbnailPhotoBytes = project.getThumnailImage().getBytes(1l,
							(int) project.getThumnailImage().length());

					fsu.storeFile(context, thumbnailPhotoBytes,
							"project-thumbnail-" + project.getId() + "-" + project.getThumnailimageName(), env1);
					dto.setImage(project.getThumnailimageName());
					dto.setImagePath(applicationPath + "/photos/projectimages/" + "project-thumbnail-" + project.getId()
							+ "-" + project.getThumnailimageName());
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}

			try {
				if (null != project.getHeroImage()) {
					byte[] heroPhotoBytes = project.getHeroImage().getBytes(1l, (int) project.getHeroImage().length());

					fsu.storeFile(context, heroPhotoBytes,
							"project-hero-" + project.getId() + "-" + project.getHeroimageName(), env1);
					dto.setHeroImage(project.getHeroimageName());
					dto.setHeroImagePath(applicationPath + "/photos/projectimages/" + "project-hero-" + project.getId()
							+ "-" + project.getHeroimageName());
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}

			dto.setName(project.getProjectName());
			dto.setDescription(project.getShortSummary());

			if (null != project.getScopeofworks()) {
				String[] scopOfWorkArray = project.getScopeofworks().split(",");
				ArrayList<String> scopeofworkList = new ArrayList<String>(Arrays.asList(scopOfWorkArray));
				dto.setScopeofworks(scopeofworkList);
			}

			if (null != project.getWorks()) {
				String[] workArray = project.getWorks().split(",");
				ArrayList<String> workList = new ArrayList<String>(Arrays.asList(workArray));
				dto.setWorks(workList);
			}
			if (null != project.getTypes()) {
				String[] typeArray = project.getTypes().split(",");
				ArrayList<String> typeList = new ArrayList<String>(Arrays.asList(typeArray));
				dto.setTypes(typeList);
			}
			if (null != project.getIndustrys()) {
				String[] industryArray = project.getIndustrys().split(",");
				ArrayList<String> industryArrayList = new ArrayList<String>(Arrays.asList(industryArray));
				dto.setIndustrys(industryArrayList);
			}

			list.add(dto);

		}
		return list;
	}

	@Override
	public ProjectSummary findById(Long id) {

		Project projectEntity = projectDao.findById(id).get();

		ProjectSummary projectSummary = new ProjectSummary();
		SectionSummary section_summary = new SectionSummary();

		ArrayList<SectionDto> sections = new ArrayList<SectionDto>();

		// project image
		String[] requestStringArray = request.getRequestURL().toString().split("/");
		String applicationPath = requestStringArray[0] + "//" + requestStringArray[2] + "/" + requestStringArray[3];
		System.out.println("applicationPath======>" + applicationPath);
		FileStroreUtil fsu = new FileStroreUtil();
		try {
			if (null != projectEntity.getThumnailImage()) {
				byte[] thumbnailPhotoBytes = projectEntity.getThumnailImage().getBytes(1l,
						(int) projectEntity.getThumnailImage().length());

				fsu.storeFile(context, thumbnailPhotoBytes,
						"project-thumbnail-" + projectEntity.getId() + "-" + projectEntity.getThumnailimageName(),
						env1);
				section_summary.setSummary_image(applicationPath + "/photos/projectimages/" + "project-thumbnail-"
						+ projectEntity.getId() + "-" + projectEntity.getThumnailimageName());
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		try {
			if (null != projectEntity.getHeroImage()) {
				byte[] heroPhotoBytes = projectEntity.getHeroImage().getBytes(1l,
						(int) projectEntity.getHeroImage().length());

				fsu.storeFile(context, heroPhotoBytes,
						"project-hero-" + projectEntity.getId() + "-" + projectEntity.getHeroimageName(), env1);
				section_summary.setSummary_hero_image(applicationPath + "/photos/projectimages/" + "project-hero-"
						+ projectEntity.getId() + "-" + projectEntity.getHeroimageName());
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		section_summary.setSummary_heading(projectEntity.getProjectName());

		String[] tagsArray = projectEntity.getScopeofworks().split(",");
		ArrayList<TagDto> tags = new ArrayList<TagDto>();
		for (String tag : tagsArray) {
			TagDto tagDto = new TagDto();
			tagDto.setMessage(tag);
			tags.add(tagDto);
		}

		projectEntity.getSections().forEach(section -> {
			SectionDto sectionDto = new SectionDto();
			// section image

			try {
				if (null != section.getSectionimage()) {
					byte[] sectionPhotoBytes = section.getSectionimage().getBytes(1l,
							(int) section.getSectionimage().length());

					fsu.storeFile(context, sectionPhotoBytes,
							"section-" + projectEntity.getId() + "-" + section.getSectionimageName(), env1);
					sectionDto.setImagePath(applicationPath + "/photos/projectimages/" + "section-"
							+ projectEntity.getId() + "-" + section.getSectionimageName());
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
		projectSummary.setId(projectEntity.getId());
		projectSummary.setProject_name(projectEntity.getProjectName());
		projectSummary.setSection_summary(section_summary);
		projectSummary.setTags(tags);
		projectSummary.setSections(sections);

		return projectSummary;
	}
	@Override
	public ProjectDto findByIdForEdit(Long id) {

		Project projectEntity = projectDao.findById(id).get();

		ProjectDto projectDto = new ProjectDto();
		if(null!=projectEntity){
			projectDto.setId(projectEntity.getId());
			projectDto.setCategory(projectEntity.getCategorys());
			projectDto.setProjectName(projectEntity.getProjectName());
			projectDto.setShortSummary(projectEntity.getShortSummary());
			if(null!=projectEntity.getScopeofworks()){
			projectDto.setScopeofworks(new ArrayList<>(Arrays.asList(projectEntity.getScopeofworks().split(","))));
			}
			
			if(null!=projectEntity.getWorks()){
				projectDto.setWorks(new ArrayList<>(Arrays.asList(projectEntity.getWorks().split(","))));
			}
			if(null!=projectEntity.getTypes()){
				projectDto.setTypes(new ArrayList<>(Arrays.asList(projectEntity.getTypes().split(","))));
			}
			if(null!=projectEntity.getIndustrys()){
				projectDto.setIndustrys(new ArrayList<>(Arrays.asList(projectEntity.getIndustrys().split(","))));
			}
			
			// project image
			String[] requestStringArray = request.getRequestURL().toString().split("/");
			String applicationPath = requestStringArray[0] + "//" + requestStringArray[2] + "/" + requestStringArray[3];
			System.out.println("applicationPath======>" + applicationPath);
			FileStroreUtil fsu = new FileStroreUtil();
			try {
				if (null != projectEntity.getThumnailImage()) {
					byte[] thumbnailPhotoBytes = projectEntity.getThumnailImage().getBytes(1l,
							(int) projectEntity.getThumnailImage().length());
	
					fsu.storeFile(context, thumbnailPhotoBytes,
							"project-thumbnail-" + projectEntity.getId() + "-" + projectEntity.getThumnailimageName(),
							env1);
					projectDto.setImagePath(applicationPath + "/photos/projectimages/" + "project-thumbnail-"
							+ projectEntity.getId() + "-" + projectEntity.getThumnailimageName());
					projectDto.setThumnailimageName(projectEntity.getThumnailimageName());
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
	
			try {
				if (null != projectEntity.getHeroImage()) {
					byte[] heroPhotoBytes = projectEntity.getHeroImage().getBytes(1l,
							(int) projectEntity.getHeroImage().length());
	
					fsu.storeFile(context, heroPhotoBytes,
							"project-hero-" + projectEntity.getId() + "-" + projectEntity.getHeroimageName(), env1);
					projectDto.setHeroImage(applicationPath + "/photos/projectimages/" + "project-hero-"
							+ projectEntity.getId() + "-" + projectEntity.getHeroimageName());
					projectDto.setHeroimageName(projectEntity.getHeroimageName());
					
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
			ArrayList<SectionDto> sectionList=new ArrayList<>();
			projectEntity.getSections().forEach(item->{
				SectionDto sectionDto=new SectionDto();
				sectionDto.setId(item.getId());
				sectionDto.setSectionId(item.getId());
				sectionDto.setSectionLayout(item.getSectionLayout());
				sectionDto.setSectionTitle(item.getSectionTitle());
				sectionDto.setSectionContent(item.getSectionContent());
				try {
					if (null != item.getSectionimage()) {
						byte[] sectionPhotoBytes = item.getSectionimage().getBytes(1l,
								(int) item.getSectionimage().length());
	
						fsu.storeFile(context, sectionPhotoBytes,
								"section-" + projectEntity.getId() + "-" + item.getSectionimageName(), env1);
						sectionDto.setImagePath(applicationPath + "/photos/projectimages/" + "section-"
								+ projectEntity.getId() + "-" + item.getSectionimageName());
						sectionDto.setSectionimageName(item.getSectionimageName());
					}
				} catch (SQLException sqlex) {
					sqlex.printStackTrace();
				}
				sectionList.add(sectionDto);
			});
			projectDto.setSections(sectionList);
			
		}
		return projectDto;
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

		List<Section> sections = new ArrayList<Section>();
		projectDto.getSections().forEach(sectionDto -> {
			Section sectionEntity = new Section();
			sectionEntity.setSectionContent(sectionDto.getSectionContent());
			sectionEntity.setSectionimage(sectionDto.getSectionimage());
			sectionEntity.setSectionimageName(sectionDto.getSectionimageName());
			sectionEntity.setSectionLayout(sectionDto.getSectionLayout());
			sectionEntity.setSectionTitle(sectionDto.getSectionTitle());
			sectionEntity.setProjectId(projectEntity);

			sections.add(sectionEntity);
		});

		projectEntity.setSections(sections);

		projectDao.save(projectEntity);

		projectDto.setHeroBlob(null);
		projectDto.setThumnailBlob(null);
		projectDto.getSections().forEach(item -> {
			item.setSectionimage(null);
		});

		return projectDto;
	}
	@Override
	public ProjectDto update(Long projectId,ProjectDto projectDto) {
		Project projectEntity =projectDao.findById(projectId).get();

		projectEntity.setCategorys(projectDto.getCategory());
		projectEntity.setHeroImage(null!=projectDto.getHeroBlob()?projectDto.getHeroBlob():projectEntity.getHeroImage());
		projectEntity.setHeroimageName(null!=projectDto.getHeroimageName()?projectDto.getHeroimageName():projectEntity.getHeroimageName());
		projectEntity.setIndustrys(StringUtils.collectionToDelimitedString(projectDto.getIndustrys(), ","));
		projectEntity.setProjectName(projectDto.getProjectName());
		projectEntity.setScopeofworks(StringUtils.collectionToDelimitedString(projectDto.getScopeofworks(), ","));

		projectEntity.setShortSummary(projectDto.getShortSummary());
		projectEntity.setThumnailImage(null!=projectDto.getThumnailBlob()?projectDto.getThumnailBlob():projectEntity.getThumnailImage());
		projectEntity.setThumnailimageName(null!=projectDto.getThumnailimageName()?projectDto.getThumnailimageName():projectEntity.getThumnailimageName());
		projectEntity.setTypes(StringUtils.collectionToDelimitedString(projectDto.getTypes(), ","));
		projectEntity.setWorks(StringUtils.collectionToDelimitedString(projectDto.getWorks(), ","));

		List<Section> sections = new ArrayList<Section>();
		Map<Long,Section> sectionsMap=new HashMap<>();
		
		projectEntity.getSections().forEach(section -> {
			
			sectionsMap.put(section.getId(), section);
		});
		
		projectDto.getSections().forEach(sectionDto -> {
			
			if(sectionsMap.containsKey(sectionDto.getId())){
			Section sectionEntity=sectionsMap.get(sectionDto.getId());
			sectionEntity.setSectionContent(sectionDto.getSectionContent());
			if(null!=sectionDto.getSectionimage()){
			sectionEntity.setSectionimage(sectionDto.getSectionimage());
			sectionEntity.setSectionimageName(sectionDto.getSectionimageName());
			}
			sectionEntity.setSectionLayout(sectionDto.getSectionLayout());
			sectionEntity.setSectionTitle(sectionDto.getSectionTitle());
			sectionEntity.setProjectId(projectEntity);

			sections.add(sectionEntity);
			}else{
				Section sectionEntity=new Section();
				sectionEntity.setSectionContent(sectionDto.getSectionContent());
				if(null!=sectionDto.getSectionimage()){
				sectionEntity.setSectionimage(sectionDto.getSectionimage());
				sectionEntity.setSectionimageName(sectionDto.getSectionimageName());
				}
				sectionEntity.setSectionLayout(sectionDto.getSectionLayout());
				sectionEntity.setSectionTitle(sectionDto.getSectionTitle());
				sectionEntity.setProjectId(projectEntity);

				sections.add(sectionEntity);
			}
			
		});

		projectEntity.getSections().addAll(sections);

		projectDao.save(projectEntity);

		projectDto.setHeroBlob(null);
		projectDto.setThumnailBlob(null);
		projectDto.getSections().forEach(item -> {
			item.setSectionimage(null);
		});

		return projectDto;
	}
	@Override
	public ProjectSummary preview(ProjectDto projectDto) {
		ProjectSummary projectSummary = new ProjectSummary();
		SectionSummary section_summary = new SectionSummary();
		ArrayList<SectionDto> sections = new ArrayList<SectionDto>();
		// project image
		String[] requestStringArray = request.getRequestURL().toString().split("/");
		String applicationPath = requestStringArray[0] + "//" + requestStringArray[2] + "/" + requestStringArray[3];
		System.out.println("applicationPath======>" + applicationPath);
		FileStroreUtil fsu = new FileStroreUtil();
		try {
			if (null != projectDto.getThumnailBlob() && null!=projectDto.getThumnailimageName()) {
				byte[] thumbnailPhotoBytes = projectDto.getThumnailBlob().getBytes(1l,
						(int) projectDto.getThumnailBlob().length());

				fsu.storeFile(context, thumbnailPhotoBytes, "project-thumbnail-" + projectDto.getThumnailimageName(),
						env1);
				section_summary.setSummary_image(applicationPath + "/photos/projectimages/" + "project-thumbnail-"
						+ projectDto.getThumnailimageName());
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		try {
			if (null != projectDto.getHeroImage() && null!=projectDto.getHeroimageName()) {
				byte[] heroPhotoBytes = projectDto.getHeroBlob().getBytes(1l, (int) projectDto.getHeroBlob().length());

				fsu.storeFile(context, heroPhotoBytes, "project-hero-" + projectDto.getHeroimageName(), env1);
				section_summary.setSummary_hero_image(
						applicationPath + "/photos/projectimages/" + "project-hero-" + projectDto.getHeroimageName());
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		section_summary.setSummary_heading(projectDto.getProjectName());

		// String [] tagsArray=projectDto.getScopeofworks().split(",");
		ArrayList<TagDto> tags = new ArrayList<TagDto>();
		for (String tag : projectDto.getScopeofworks()) {
			TagDto tagDto = new TagDto();
			tagDto.setMessage(tag);
			tags.add(tagDto);
		}
		projectDto.getSections().forEach(section -> {
			SectionDto sectionDto = new SectionDto();
			// section image
			try {
				if (null != section.getSectionimage()) {
					byte[] sectionPhotoBytes = section.getSectionimage().getBytes(1l,
							(int) section.getSectionimage().length());

					fsu.storeFile(context, sectionPhotoBytes, "section-" + section.getSectionimageName(), env1);
					sectionDto.setImagePath(
							applicationPath + "/photos/projectimages/" + "section-" + section.getSectionimageName());
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

	public ProjectDto delete(Long id) {
		projectDao.deleteById(id);
		ProjectDto projectDto = new ProjectDto();
		projectDto.setId(id);
		return projectDto;
	}

}
