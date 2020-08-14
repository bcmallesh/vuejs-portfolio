package com.evry.portfolio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evry.portfolio.dao.RoleDao;
import com.evry.portfolio.entity.Role;
import com.evry.portfolio.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	public List<Role> findAll() {
		List<Role> list = new ArrayList<>();
		roleDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

}