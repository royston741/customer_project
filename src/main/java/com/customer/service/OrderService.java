package com.customer.service;

import java.util.List;

import com.customer.entity.Order;
import com.customer.model.OrderDTO;

public interface OrderService {

	// Create
	String createOrder(Order order, int id);

	// Read
	OrderDTO getOrderById(int id);

	// Read All
	List<OrderDTO> getAllOrder();

	// Read greater price order
	List<OrderDTO> getOrderGreaterThanPrice(double price);

	// Read less price order
	List<OrderDTO> getOrderLessThanPrice(double price);

	// Read between price order
	List<OrderDTO> getOrderBetweenPrice(double maxPrice, double minPrice);

	// Update
	OrderDTO updateOrder(int id, Order order);

	// Delete
	String deleteOrderById(int id);

	// Delete All
	void deleteAllOrders();

}
