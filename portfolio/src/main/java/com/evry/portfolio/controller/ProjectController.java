package com.evry.portfolio.controller;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.evry.portfolio.model.ProjectDto;
import com.evry.portfolio.model.ProjectSummary;
import com.evry.portfolio.service.ProjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProjectController {
	protected final Log logger = LogFactory.getLog(ProjectController.class);
	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = "/projectsinfo", method = RequestMethod.GET)
	public List<ProjectDto> projectsList() {
		return projectService.findAll();
	}

	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public ProjectSummary getProjectById(@PathVariable(value = "id") Long id) {
		return projectService.findById(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/createproject", method = RequestMethod.POST, consumes = { "multipart/form-data",
			"multipart/form-data", "multipart/form-data" })
	public ProjectDto saveProjectInfo(@RequestPart("projectdata") ProjectDto projectdata,
			@RequestParam(name = "thumnailimage", required = false) MultipartFile thumnailimage,
			@RequestParam(name = "heroimage", required = false) MultipartFile heroimage,
			@RequestParam("sectionfiles") MultipartFile[] sectionfiles) {

		try {

			logger.info("projectdata ==" + projectdata.getProjectName());
			if (null != thumnailimage) {
				logger.info("original thumnailimage==" + thumnailimage.getOriginalFilename());
				Blob thumnailimageBlob = new javax.sql.rowset.serial.SerialBlob(thumnailimage.getBytes());
				if (null != thumnailimageBlob) {
					projectdata.setThumnailBlob(thumnailimageBlob);
					projectdata.setThumnailimageName(thumnailimage.getOriginalFilename());
				}

			}
			if (null != heroimage) {
				logger.info("original heroimage==" + heroimage.getOriginalFilename());
				Blob heroimageBlob = new javax.sql.rowset.serial.SerialBlob(heroimage.getBytes());
				if (null != heroimageBlob) {
					projectdata.setHeroBlob(heroimageBlob);
					projectdata.setHeroimageName(heroimage.getOriginalFilename());
				}

			}
			// logger.info("original sectionfile=="+sectionfiles);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (null != sectionfiles) {
			// logger.info("original sectionfile=1="+sectionfiles);

			ArrayList<MultipartFile> sectionFiles = new ArrayList<MultipartFile>(Arrays.asList(sectionfiles));
			int index = 0;
			for (MultipartFile sectionfile : sectionFiles) {
				logger.info(index + " sectionfile   " + sectionfile.getOriginalFilename());
				try {
					Blob sectionfileBlob = new javax.sql.rowset.serial.SerialBlob(sectionfile.getBytes());
					projectdata.getSections().get(index).setSectionimage(sectionfileBlob);
					projectdata.getSections().get(index).setSectionimageName(sectionfile.getOriginalFilename());

				} catch (Exception ex) {

				}
				index = index + 1;
			}

		}

		return projectService.save(projectdata);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/previewproject", method = RequestMethod.POST, consumes = { "multipart/form-data",
			"multipart/form-data", "multipart/form-data" })
	public ProjectSummary PreviewProjectInfo(@RequestPart("projectdata") ProjectDto projectdata,
			@RequestParam(name = "thumnailimage", required = false) MultipartFile thumnailimage,
			@RequestParam(name = "heroimage", required = false) MultipartFile heroimage,
			@RequestParam("sectionfiles") MultipartFile[] sectionfiles) {

		try {

			logger.info("projectdata ==" + projectdata.getProjectName());
			if (null != thumnailimage) {
				logger.info("original thumnailimage==" + thumnailimage.getOriginalFilename());
				Blob thumnailimageBlob = new javax.sql.rowset.serial.SerialBlob(thumnailimage.getBytes());
				if (null != thumnailimageBlob) {
					projectdata.setThumnailBlob(thumnailimageBlob);
					projectdata.setThumnailimageName(thumnailimage.getOriginalFilename());
				}

			}
			if (null != heroimage) {
				logger.info("original heroimage==" + heroimage.getOriginalFilename());
				Blob heroimageBlob = new javax.sql.rowset.serial.SerialBlob(heroimage.getBytes());
				if (null != heroimageBlob) {
					projectdata.setHeroBlob(heroimageBlob);
					projectdata.setHeroimageName(heroimage.getOriginalFilename());
				}

			}
			// logger.info("original sectionfile=="+sectionfiles);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (null != sectionfiles) {
			// logger.info("original sectionfile=1="+sectionfiles);

			ArrayList<MultipartFile> sectionFiles = new ArrayList<MultipartFile>(Arrays.asList(sectionfiles));
			int index = 0;
			for (MultipartFile sectionfile : sectionFiles) {
				logger.info(index + " sectionfile   " + sectionfile.getOriginalFilename());
				try {
					Blob sectionfileBlob = new javax.sql.rowset.serial.SerialBlob(sectionfile.getBytes());
					projectdata.getSections().get(index).setSectionimage(sectionfileBlob);
					projectdata.getSections().get(index).setSectionimageName(sectionfile.getOriginalFilename());

				} catch (Exception ex) {

				}
				index = index + 1;
			}
		}
		return projectService.preview(projectdata);
	}

	@RequestMapping(value = "/deleteproject/{id}", method = RequestMethod.DELETE)
	public ProjectDto deleteProject(@PathVariable(value = "id") Long id) {
		return projectService.delete(id);
	}

	@RequestMapping(value = "/projectsedit/{id}", method = RequestMethod.GET)
	public ProjectDto getProjectByIdForEdit(@PathVariable(value = "id") Long id) {
		return projectService.findByIdForEdit(id);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/updateproject/{id}", method = RequestMethod.PUT, consumes = { "multipart/form-data",
			"multipart/form-data", "multipart/form-data" })
	public ProjectDto updateProjectInfo(@RequestPart("projectdata") ProjectDto projectdata,
			@RequestParam(name = "thumnailimage", required = false) MultipartFile thumnailimage,
			@RequestParam(name = "heroimage", required = false) MultipartFile heroimage,
			@RequestParam("sectionfiles") MultipartFile[] sectionfiles,@PathVariable(value = "id") Long id) {

		try {

			logger.info("projectdata ==update===" + projectdata.getProjectName());
			if (null != thumnailimage) {
				logger.info("original thumnailimage==" + thumnailimage.getOriginalFilename());
				Blob thumnailimageBlob = new javax.sql.rowset.serial.SerialBlob(thumnailimage.getBytes());
				if (null != thumnailimageBlob) {
					projectdata.setThumnailBlob(thumnailimageBlob);
					projectdata.setThumnailimageName(thumnailimage.getOriginalFilename());
				}

			}
			if (null != heroimage) {
				logger.info("original heroimage==" + heroimage.getOriginalFilename());
				Blob heroimageBlob = new javax.sql.rowset.serial.SerialBlob(heroimage.getBytes());
				if (null != heroimageBlob) {
					projectdata.setHeroBlob(heroimageBlob);
					projectdata.setHeroimageName(heroimage.getOriginalFilename());
				}

			}
			// logger.info("original sectionfile=="+sectionfiles);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (null != sectionfiles) {
			// logger.info("original sectionfile=1="+sectionfiles);

			ArrayList<MultipartFile> sectionFiles = new ArrayList<MultipartFile>(Arrays.asList(sectionfiles));
			int index = 0;
			for (MultipartFile sectionfile : sectionFiles) {
				logger.info(index + " sectionfile   " + sectionfile.getOriginalFilename());
				try {
					Blob sectionfileBlob = new javax.sql.rowset.serial.SerialBlob(sectionfile.getBytes());
					projectdata.getSections().get(index).setSectionimage(sectionfileBlob);
					projectdata.getSections().get(index).setSectionimageName(sectionfile.getOriginalFilename());

				} catch (Exception ex) {

				}
				index = index + 1;
			}

		}

		return projectService.update(id,projectdata);
	}

}
