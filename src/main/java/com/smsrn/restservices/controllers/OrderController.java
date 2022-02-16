package com.smsrn.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsrn.restservices.entities.Order;
import com.smsrn.restservices.entities.User;
import com.smsrn.restservices.exceptions.OrderNotFoundException;
import com.smsrn.restservices.exceptions.UserNotFoundException;
import com.smsrn.restservices.repositories.OrderRepository;
import com.smsrn.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class OrderController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		return user.get().getOrders();
	}

	@PostMapping("{userId}/orders")
	public Order createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		User user = optionalUser.get();
		order.setUser(user);
		return orderRepository.save(order);
	}

	@GetMapping("{userId}/orders/{orderId}")
	public Optional<Order> getOrderByOrderId(@PathVariable Long userId, @PathVariable Long orderId)
			throws UserNotFoundException, OrderNotFoundException {
		Optional<User> optionalUser = userRepository.findById(userId);

		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found");
		}

		Optional<Order> order = optionalUser.get().getOrders().stream()
				.filter(item -> item.getOrderId().equals(orderId)).findFirst();

		if (!order.isPresent()) {
			throw new OrderNotFoundException("Order not found");
		}

		return order;
	}
}
