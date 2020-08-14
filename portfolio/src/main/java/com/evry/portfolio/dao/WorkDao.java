package com.evry.portfolio.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evry.portfolio.entity.Work;
@Repository
public interface WorkDao extends CrudRepository<Work, Long> {
	
}
