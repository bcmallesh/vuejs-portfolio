package com.evry.portfolio.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evry.portfolio.entity.StrongText;



@Repository
public interface StrongTextDao extends CrudRepository<StrongText, Long> {
	
}
