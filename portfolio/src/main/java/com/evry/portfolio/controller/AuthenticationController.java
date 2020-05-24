package com.evry.portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.evry.portfolio.config.TokenProvider;
import com.evry.portfolio.entity.User;
import com.evry.portfolio.model.LoginUser;
import com.evry.portfolio.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(loginUser.getUsername());
		User userData = userService.findOne(loginUser.getUsername());
		Map<Object, Object> model = new HashMap<>();
		if (jwtTokenUtil.validateToken(token, userDetails)) {
			model.put("id", userData.getId());
			model.put("username", userData.getUsername());
			model.put("role", userData.getRole().getName());
			model.put("token", token);
		}

		return ResponseEntity.ok(model);
	}

}
