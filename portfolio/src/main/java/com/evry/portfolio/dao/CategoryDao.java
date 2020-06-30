package com.evry.portfolio.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evry.portfolio.entity.Category;



@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {
	
}
