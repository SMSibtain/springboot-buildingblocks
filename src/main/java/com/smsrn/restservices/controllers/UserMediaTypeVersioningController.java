package com.smsrn.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.smsrn.restservices.dtos.UserDtoV1;
import com.smsrn.restservices.dtos.UserDtoV2;
import com.smsrn.restservices.dtos.UserMmDto;
import com.smsrn.restservices.entities.User;
import com.smsrn.restservices.exceptions.UserNotFoundException;
import com.smsrn.restservices.services.UserService;

@RestController
@RequestMapping("/versioning/mediatype/users")
public class UserMediaTypeVersioningController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	// Custom Header Based Versioning V1
	@GetMapping(value = "{id}", produces = "application/vnd.smsrn.app-v1+json")
	public UserDtoV1 getUserByIdV1(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);

			if (!userOptional.isPresent()) {
				throw new UserNotFoundException("User not found");
			}

			User user = userOptional.get();

			UserDtoV1 userDto = modelMapper.map(user, UserDtoV1.class);

			return userDto;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	// Custom Header Based Versioning V2
	@GetMapping(value = "{id}", produces = "application/vnd.smsrn.app-v2+json")
	public UserDtoV2 getUserByIdV2(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);

			if (!userOptional.isPresent()) {
				throw new UserNotFoundException("User not found");
			}

			User user = userOptional.get();

			UserDtoV2 userDto = modelMapper.map(user, UserDtoV2.class);

			return userDto;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
