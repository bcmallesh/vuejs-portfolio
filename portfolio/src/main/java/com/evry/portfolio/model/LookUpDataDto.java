package com.evry.portfolio.model;

import java.util.List;

public class LookUpDataDto {

	private List<IndustryDto> industryLookupData;
	private List<TypeDto> typeLookupData;
	private List<WorkDto> WorkLookupData;
	public List<IndustryDto> getIndustryLookupData() {
		return industryLookupData;
	}
	public void setIndustryLookupData(List<IndustryDto> industryLookupData) {
		this.industryLookupData = industryLookupData;
	}
	public List<TypeDto> getTypeLookupData() {
		return typeLookupData;
	}
	public void setTypeLookupData(List<TypeDto> typeLookupData) {
		this.typeLookupData = typeLookupData;
	}
	public List<WorkDto> getWorkLookupData() {
		return WorkLookupData;
	}
	public void setWorkLookupData(List<WorkDto> workLookupData) {
		WorkLookupData = workLookupData;
	}


}
