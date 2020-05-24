package com.evry.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.evry.portfolio.entity.Role;
import com.evry.portfolio.model.UserDto;
import com.evry.portfolio.service.RoleService;
import com.evry.portfolio.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public List<Role> listRoles() {
		return roleService.findAll();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserDto> listUser() {
		return userService.findAll();
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('LORRYDRIVER') or hasRole('BIKEDRIVER')")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public UserDto getOne(@PathVariable(value = "id") Long id) {
		return userService.findById(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public UserDto saveUser(@RequestBody UserDto user) {
		return userService.save(user);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/updateuser/{id}", method = RequestMethod.PUT)
	public UserDto updateUser(@PathVariable(value = "id") Long id, @RequestBody UserDto user) {
		user.setId(id);
		return userService.save(user);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable(value = "id") Long id) {
		userService.delete(id);

	}

}
