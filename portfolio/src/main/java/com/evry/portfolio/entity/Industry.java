package com.evry.portfolio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INDUSTRY")
public class Industry {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "LOOKUPDATA_KEY")
    private String lookupDataKey;

    @Column(name = "LOOKUPDATA_VALUE")
    private String lookupDataValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLookupDataKey() {
		return lookupDataKey;
	}

	public void setLookupDataKey(String lookupDataKey) {
		this.lookupDataKey = lookupDataKey;
	}

	public String getLookupDataValue() {
		return lookupDataValue;
	}

	public void setLookupDataValue(String lookupDataValue) {
		this.lookupDataValue = lookupDataValue;
	}

	

	
	

	
}
