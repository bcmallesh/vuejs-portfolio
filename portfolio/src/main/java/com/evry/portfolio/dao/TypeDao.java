package com.evry.portfolio.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evry.portfolio.entity.Type;



@Repository
public interface TypeDao extends CrudRepository<Type, Long> {
	
}
