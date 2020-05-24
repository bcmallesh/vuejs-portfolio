package com.evry.portfolio.service;


import  com.evry.portfolio.entity.User;
import  com.evry.portfolio.model.UserDto;

import java.util.List;

public interface UserService {
	UserDto save(UserDto user);
    List<UserDto> findAll();
    void delete(long id);
    User findOne(String username);
    UserDto findById(Long id);
}
