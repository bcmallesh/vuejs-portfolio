package com.evry.portfolio.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evry.portfolio.entity.Industry;



@Repository
public interface IndustryDao extends CrudRepository<Industry, Long> {
	
}
