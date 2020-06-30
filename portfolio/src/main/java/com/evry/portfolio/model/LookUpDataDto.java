package com.evry.portfolio.model;

import java.util.List;

public class LookUpDataDto {

	private List<LookupDto> industryLookupData;
	private List<LookupDto> typeLookupData;
	private List<LookupDto> WorkLookupData;
	private List<LookupDto> categoryLookupData;

	private List<LookupDto> scopeOfWorkLookupData;
	public List<LookupDto> getIndustryLookupData() {
		return industryLookupData;
	}
	public void setIndustryLookupData(List<LookupDto> industryLookupData) {
		this.industryLookupData = industryLookupData;
	}
	public List<LookupDto> getTypeLookupData() {
		return typeLookupData;
	}
	public void setTypeLookupData(List<LookupDto> typeLookupData) {
		this.typeLookupData = typeLookupData;
	}
	public List<LookupDto> getWorkLookupData() {
		return WorkLookupData;
	}
	public void setWorkLookupData(List<LookupDto> workLookupData) {
		WorkLookupData = workLookupData;
	}
	public List<LookupDto> getScopeOfWorkLookupData() {
		return scopeOfWorkLookupData;
	}
	public void setScopeOfWorkLookupData(List<LookupDto> scopeOfWorkLookupData) {
		this.scopeOfWorkLookupData = scopeOfWorkLookupData;
	}
	public List<LookupDto> getCategoryLookupData() {
		return categoryLookupData;
	}
	public void setCategoryLookupData(List<LookupDto> categoryLookupData) {
		this.categoryLookupData = categoryLookupData;
	}
	


}
