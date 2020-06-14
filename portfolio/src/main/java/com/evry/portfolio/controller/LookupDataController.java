package com.evry.portfolio.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.evry.portfolio.model.FomratLookUpDataDto;
import com.evry.portfolio.model.LookUpDataDto;
import com.evry.portfolio.service.LookupDataService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LookupDataController {

	@Autowired
	private LookupDataService lookupDataService;
	
	@RequestMapping(value = "/lookupdata", method = RequestMethod.GET)
	public LookUpDataDto getAllLookUpData() {
		return lookupDataService.getAllLookUpData();
	}
	@RequestMapping(value = "/formatedlookupdata", method = RequestMethod.GET)
	public Map<String,ArrayList<FomratLookUpDataDto>> getAllFormatedLookUpData() {
		return lookupDataService.getAllFormatLookUpData();
	}

}
