package com.evry.portfolio.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evry.portfolio.entity.ScopeOfWork;



@Repository
public interface ScopeOfWorkDao extends CrudRepository<ScopeOfWork, Long> {
	
}
