package com.evry.portfolio.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evry.portfolio.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
   
    Role findByName(String roleName);
}
