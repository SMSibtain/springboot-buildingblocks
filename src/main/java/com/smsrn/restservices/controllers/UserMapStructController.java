package com.smsrn.restservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsrn.restservices.dtos.UserMsDto;
import com.smsrn.restservices.mappers.UserMapper;
import com.smsrn.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping
	public List<UserMsDto> getAllUsersDtos() {
		return userMapper.userToUserMsDtos(userRepository.findAll());
	}
	
//	@GetMapping("/{id}")
//	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
//		return userRepository.findById(id);
//	}

}