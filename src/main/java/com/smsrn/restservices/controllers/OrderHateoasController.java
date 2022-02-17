package com.smsrn.restservices.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsrn.restservices.entities.Order;
import com.smsrn.restservices.entities.User;
import com.smsrn.restservices.exceptions.UserNotFoundException;
import com.smsrn.restservices.repositories.UserRepository;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/hateoas/users")
public class OrderHateoasController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/{userId}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found");
		}

		CollectionModel<Order> finalResources = CollectionModel.of(user.get().getOrders());
//		Link selfLink = WebMvcLinkBuilder.linkTo(OrderHateoasController.class).slash(userId).withSelfRel();
//		finalResources.add(selfLink);
		
		return finalResources;
	}
}
