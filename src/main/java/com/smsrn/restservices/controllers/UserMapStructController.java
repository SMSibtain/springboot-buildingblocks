package com.smsrn.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsrn.restservices.dtos.UserMsDto;
import com.smsrn.restservices.entities.User;
import com.smsrn.restservices.exceptions.UserNotFoundException;
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

	@GetMapping("/{id}")
	public UserMsDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");

		User user = userOptional.get();
		return userMapper.userToUserMsDto(user);
	}
}