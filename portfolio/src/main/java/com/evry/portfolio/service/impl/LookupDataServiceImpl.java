package com.evry.portfolio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evry.portfolio.dao.CategoryDao;
import com.evry.portfolio.dao.IndustryDao;
import com.evry.portfolio.dao.ScopeOfWorkDao;

import com.evry.portfolio.dao.TypeDao;
import com.evry.portfolio.dao.WorkDao;
import com.evry.portfolio.model.LookUpDataDto;
import com.evry.portfolio.model.LookupDto;
import com.evry.portfolio.service.LookupDataService;

@Service(value = "lookupDataService")
public class LookupDataServiceImpl implements LookupDataService {

	@Autowired
	private IndustryDao industryDao;

	@Autowired
	private TypeDao typeDao;

	@Autowired
	private WorkDao workDao;
	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ScopeOfWorkDao scopeOfWorkDao;

	public LookUpDataDto getAllLookUpData() {
		LookUpDataDto lookUpDataDto = new LookUpDataDto();

		List<LookupDto> industryLookupData = new ArrayList<LookupDto>();
		List<LookupDto> typeLookupData = new ArrayList<LookupDto>();
		List<LookupDto> workLookupData = new ArrayList<LookupDto>();
		List<LookupDto> scopeOfWorkLookupData = new ArrayList<LookupDto>();
		List<LookupDto> categoryLookupData = new ArrayList<LookupDto>();

		industryDao.findAll().forEach(industryType -> {
			LookupDto industryDto = new LookupDto();
			industryDto.setId(industryType.getId());
			industryDto.setKey(industryType.getLookupDataKey());
			industryDto.setValue(industryType.getLookupDataValue());
			industryLookupData.add(industryDto);
		});
		lookUpDataDto.setIndustryLookupData(industryLookupData);

		typeDao.findAll().forEach(typeItem -> {
			LookupDto typeDto = new LookupDto();
			typeDto.setId(typeItem.getId());
			typeDto.setKey(typeItem.getLookupDataKey());
			typeDto.setValue(typeItem.getLookupDataValue());
			typeLookupData.add(typeDto);
		});

		workDao.findAll().forEach(workItem -> {
			LookupDto workDto = new LookupDto();
			workDto.setId(workItem.getId());
			workDto.setKey(workItem.getLookupDataKey());
			workDto.setValue(workItem.getLookupDataValue());
			workLookupData.add(workDto);
		});
		categoryDao.findAll().forEach(categoryItem -> {
			LookupDto categoryDto = new LookupDto();
			categoryDto.setId(categoryItem.getId());
			categoryDto.setKey(categoryItem.getLookupDataKey());
			categoryDto.setValue(categoryItem.getLookupDataValue());
			categoryLookupData.add(categoryDto);
		});
		scopeOfWorkDao.findAll().forEach(scopeOfWorkItem -> {
			LookupDto scopeOfWorkDto = new LookupDto();
			scopeOfWorkDto.setId(scopeOfWorkItem.getId());
			scopeOfWorkDto.setKey(scopeOfWorkItem.getLookupDataKey());
			scopeOfWorkDto.setValue(scopeOfWorkItem.getLookupDataValue());
			scopeOfWorkLookupData.add(scopeOfWorkDto);
		});
		lookUpDataDto.setIndustryLookupData(industryLookupData);
		lookUpDataDto.setTypeLookupData(typeLookupData);
		lookUpDataDto.setWorkLookupData(workLookupData);
		lookUpDataDto.setCategoryLookupData(categoryLookupData);

		lookUpDataDto.setScopeOfWorkLookupData(scopeOfWorkLookupData);

		return lookUpDataDto;
	}

}
