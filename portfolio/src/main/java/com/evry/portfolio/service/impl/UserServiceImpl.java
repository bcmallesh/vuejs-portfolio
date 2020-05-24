package com.evry.portfolio.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.evry.portfolio.dao.RoleDao;
import com.evry.portfolio.dao.UserDao;
import com.evry.portfolio.entity.Role;
import com.evry.portfolio.entity.User;
import com.evry.portfolio.model.UserDto;
import com.evry.portfolio.service.UserService;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
		return authorities;
	}

	public List<UserDto> findAll() {
		List<UserDto> list = new ArrayList<UserDto>();
		for (User user : userDao.findAll()) {
			UserDto uto = new UserDto();
			uto.setUsername(user.getUsername());
			uto.setRole(user.getRole().getName());
			uto.setId(user.getId());
			list.add(uto);

		}
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public UserDto findById(Long id) {

		User user = userDao.findById(id).get();
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setRole(user.getRole().getName());
		return userDto;
	}

	@Override
	public UserDto save(UserDto user) {
		User newUser = new User();
		if (null != user.getUsername() && null != user.getId()) {

			newUser = userDao.findById(user.getId()).get();
			newUser.setUsername(user.getUsername());
			Role role = roleDao.findByName(user.getRole());
			newUser.setRole(role);
			System.out.println("bcryptEncoder===>"+bcryptEncoder+" :: "+user +" :: "+user.getPassword());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			
		} else {
			newUser.setUsername(user.getUsername());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			Role role = roleDao.findByName(user.getRole());
			newUser.setRole(role);
		}
		User newCreatedUser = userDao.save(newUser);
		UserDto udto = new UserDto();
		udto.setId(newCreatedUser.getId());
		udto.setUsername(newCreatedUser.getUsername());
		udto.setRole(newCreatedUser.getRole().getName());
		return udto;
	}

	

}
