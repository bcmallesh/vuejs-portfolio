package com.evry.portfolio.service;

import com.evry.portfolio.model.LookUpDataDto;

import java.util.ArrayList;
import java.util.Map;

import com.evry.portfolio.model.FomratLookUpDataDto;
public interface LookupDataService {
	
    LookUpDataDto getAllLookUpData();
    
    Map<String,ArrayList<FomratLookUpDataDto>> getAllFormatLookUpData();
}
