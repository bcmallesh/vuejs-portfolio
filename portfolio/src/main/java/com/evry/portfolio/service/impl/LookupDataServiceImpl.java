package com.evry.portfolio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evry.portfolio.dao.IndustryDao;
import com.evry.portfolio.dao.TypeDao;
import com.evry.portfolio.dao.WorkDao;
import com.evry.portfolio.model.IndustryDto;
import com.evry.portfolio.model.LookUpDataDto;
import com.evry.portfolio.model.TypeDto;
import com.evry.portfolio.model.WorkDto;
import com.evry.portfolio.service.LookupDataService;

@Service(value = "lookupDataService")
public class LookupDataServiceImpl implements LookupDataService {

	@Autowired
	private IndustryDao industryDao;

	@Autowired
	private TypeDao typeDao;
	
	@Autowired
	private WorkDao workDao;
	
	public LookUpDataDto getAllLookUpData() {
		LookUpDataDto lookUpDataDto = new LookUpDataDto();
		
		 List<IndustryDto> industryLookupData=new  ArrayList<IndustryDto>();
		 List<TypeDto> typeLookupData=new  ArrayList<TypeDto>();
		 List<WorkDto> WorkLookupData=new  ArrayList<WorkDto>();
		 
		 industryDao.findAll().forEach(industryType->{
			 IndustryDto industryDto=new IndustryDto();
			 industryDto.setId(industryType.getId());
			 industryDto.setKey(industryType.getLookupDataKey());
			 industryDto.setValue(industryType.getLookupDataValue());
			 industryLookupData.add(industryDto);
		 });
		 lookUpDataDto.setIndustryLookupData(industryLookupData);
		 
		 
		 typeDao.findAll().forEach(typeItem->{
			 TypeDto typeDto=new TypeDto();
			 typeDto.setId(typeItem.getId());
			 typeDto.setKey(typeItem.getLookupDataKey());
			 typeDto.setValue(typeItem.getLookupDataValue());
			 typeLookupData.add(typeDto);
		 });
		 
		 workDao.findAll().forEach(workItem->{
			 WorkDto workDto=new WorkDto();
			 workDto.setId(workItem.getId());
			 workDto.setKey(workItem.getLookupDataKey());
			 workDto.setValue(workItem.getLookupDataValue());
			 WorkLookupData.add(workDto);
		 });
		 lookUpDataDto.setIndustryLookupData(industryLookupData);
		 lookUpDataDto.setTypeLookupData(typeLookupData);
		 lookUpDataDto.setWorkLookupData(WorkLookupData);

		return lookUpDataDto;
	}

	
}
