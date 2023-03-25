package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.entity.Order;
import com.customer.model.OrderDTO;
import com.customer.service.OrderService;
import com.customer.util.OrderConverter;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderConverter orderConverter;

	// CREATE
	@PostMapping("/createOrder/{id}")
	public String createOrder(@PathVariable("id") int id, @RequestBody OrderDTO orderDTO) {
		final Order order = orderConverter.convertToEntity(orderDTO);
		return orderService.createOrder(order, id);
	}

	// READ
	@GetMapping("/getOrderById/{id}")
	public OrderDTO getOrderById(@PathVariable("id") int id) {
		return orderService.getOrderById(id);
	}

	// READ ALL -----------> ADMIN
	@GetMapping("/getAllOrder")
	public List<OrderDTO> getAllOrder() {
		return orderService.getAllOrder();
	}

	@GetMapping("/getGreaterOrder/{price}")
	public List<OrderDTO> getAllGreaterOrder(@PathVariable("price") double price) {
		return orderService.getOrderGreaterThanPrice(price);
	}

	@GetMapping("/getLessOrder/{price}")
	public List<OrderDTO> getAllLessOrder(@PathVariable("price") double price) {
		return orderService.getOrderLessThanPrice(price);
	}

	@GetMapping("/getOrderBetweenPrice/{max}/{min}")
	public List<OrderDTO> getOrderBetweenPrice(@PathVariable("max") double maxPrice,
			@PathVariable("min") double minPrice) {
		System.out.println(maxPrice);
		System.out.println(minPrice);
		return orderService.getOrderBetweenPrice(maxPrice, minPrice);
	}

	// UPDATE
	@PutMapping("/updateOrder/{id}")
	public OrderDTO updateOrder(@PathVariable("id") int id, @RequestBody OrderDTO orderDTO) {
		Order order = orderConverter.convertToEntity(orderDTO);
		return orderService.updateOrder(id, order);
	}

	// DELETE
	@DeleteMapping("/deleteOrderById/{id}")
	public String deleteOrderById(@PathVariable("id") int id) {
		return orderService.deleteOrderById(id);
	}

	// DELETE ALL -----------> ADMIN
	@DeleteMapping("/deleteAllOrders")
	public ResponseEntity<String> deleteAllOrders() {
		orderService.deleteAllOrders();
		return new ResponseEntity<>("------- ALL ORDERS DELETED --------", HttpStatus.OK);
	}
}
