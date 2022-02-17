package com.smsrn.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.smsrn.restservices.entities.Order;
import com.smsrn.restservices.entities.User;
import com.smsrn.restservices.exceptions.UserNotFoundException;
import com.smsrn.restservices.services.UserService;

@RestController
@Validated
@RequestMapping("/hateoas/users")
public class UserHeatoasController {

	@Autowired
	private UserService userService;

	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		List<User> userList = userService.getAllUsers();

		for (User user : userList) {
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(user.getId()).withSelfRel();
			user.add(selfLink);

			CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
					.getAllOrders(user.getId());

			Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
		}

		Link selfLinkForAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<User> finalResources = CollectionModel.of(userList, selfLinkForAllUsers);

		return finalResources;
	}

	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			Long userId = user.getId();

			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);

			EntityModel<User> finalModel = EntityModel.of(user);

			return finalModel;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
