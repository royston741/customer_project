package com.customer.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.entity.Customer;
import com.customer.entity.Order;
import com.customer.exception.ResourseNotFoundException;
import com.customer.model.OrderDTO;
import com.customer.repository.CustomerRepository;
import com.customer.repository.OrderRepository;
import com.customer.service.OrderService;
import com.customer.util.OrderConverter;

@Service
public class OrderServiceImpl implements OrderService {

	// orderRepository contain JPA interface for HQL methods
	@Autowired
	private OrderRepository orderRepository;

	// get the HQL methods of customerRepository
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderConverter orderConverter;

	// Create order
	@Override
	public String createOrder(Order order, int id) {

		// get price
		double price = order.getOrderPrice();
		// get quantity
		int quantity = order.getOrderQuantity();

		// calculate total price
		double totalPrice = price * quantity;

		// set the order price
		order.setOrderPrice(totalPrice);

		// find the customer
		Customer foundCustomer = customerRepository.findById(id).orElseThrow(() -> {
			return new ResourseNotFoundException("Customer", "ID", id);
		});

		// get all orders
		List<Order> allOrders = foundCustomer.getOrder();
		// save the new order to all existing orders
		allOrders.add(order);

		// set order changes
		foundCustomer.setOrder(allOrders);

		// set customer changes
		order.setCustomer(foundCustomer);

		// save changes
		customerRepository.save(foundCustomer);

		// if order not null
		if (order != null) {
			// success
			return "------- ORDER SAVED -------";
		}
		// fail
		return "-------- SOMETHING WENT WROG! ORDEERED NOT SAVED -------";
	}

	// get order by id
	@Override
	public OrderDTO getOrderById(int id) {

		// find the order
		Order foundOrder = orderRepository.findById(id).orElseThrow(() -> {
			return new ResourseNotFoundException("Order", "ID", id);
		});

		// return the order after converting to DTO format
		return orderConverter.convertToOrderDTO(foundOrder);
	}

	// get all order
	@Override
	public List<OrderDTO> getAllOrder() {

		// find all orders
		List<Order> allOrderList = orderRepository.findAll();

		List<OrderDTO> allOrderDTOs = new ArrayList<>();

		// for each order
		for (Order order : allOrderList) {
			// convert into OrderDTO and save into allOrderDTOs
			allOrderDTOs.add(orderConverter.convertToOrderDTO(order));
		}

		// return allOrderDTOs
		return allOrderDTOs;
	}

	// get order greater than provided by price
	@Override
	public List<OrderDTO> getOrderGreaterThanPrice(double price) {

		// find the order greater than price
		List<Order> allOrderList = orderRepository.findGreaterPrice(price);

		List<OrderDTO> allOrderDTOs = new ArrayList<>();

		// for each order
		for (Order order : allOrderList) {
			// convert into OrderDTO and save into allOrderDTOs
			allOrderDTOs.add(orderConverter.convertToOrderDTO(order));
		}

		// return allOrderDTOs
		return allOrderDTOs;
	}

	// get order less than provided by price
	@Override
	public List<OrderDTO> getOrderLessThanPrice(double price) {

		// find the order less than price
		List<Order> allOrderList = orderRepository.findLessPrice(price);

		List<OrderDTO> allOrderDTOs = new ArrayList<>();

		// for each order
		for (Order order : allOrderList) {
			// convert into OrderDTO and save into allOrderDTOs
			allOrderDTOs.add(orderConverter.convertToOrderDTO(order));
		}

		// return allOrderDTOs

		return allOrderDTOs;
	}

	// get order between provided price
	@Override
	public List<OrderDTO> getOrderBetweenPrice(double maxPrice, double minPrice) {

		// find the order between than price
		List<Order> allOrderList = orderRepository.findByOrderPriceBetween(maxPrice, minPrice);

		List<OrderDTO> allOrderDTOs = new ArrayList<>();

		// for each order
		for (Order order : allOrderList) {
			// convert into OrderDTO and save into allOrderDTOs
			allOrderDTOs.add(orderConverter.convertToOrderDTO(order));
		}

		// return allOrderDTOs
		return allOrderDTOs;
	}

	// update order
	@Override
	public OrderDTO updateOrder(int id, Order order) {

		// find order by id
		Order foundOrder = orderRepository.findById(id).orElseThrow(() -> {
			return new ResourseNotFoundException("Order", "ID", id);
		});

		// get price
		double price = order.getOrderPrice();
		// get quantity
		int quantity = order.getOrderQuantity();
		// calculate total price
		double totalPrice = price * quantity;
		// set order price
		order.setOrderPrice(totalPrice);

		// set changes
		foundOrder.setOrderName(order.getOrderName());
		foundOrder.setOrderPrice(order.getOrderPrice());
		foundOrder.setOrderQuantity(order.getOrderQuantity());

		// save changes
		orderRepository.save(foundOrder);
		// return update order
		return orderConverter.convertToOrderDTO(foundOrder);

	}

	// delete by id
	@Override
	public String deleteOrderById(int id) {

		// find by order
		Optional<Order> order = orderRepository.findById(id);

		// if present
		if (order.isPresent()) {
			// delete by id
			orderRepository.deleteById(id);
			// success
			return "------- ORDER DELETED SUCCESSFULLY -------";
		}

		// fail
		return "------- ORDER NOT FOUND -------";
	}

	// delete all orders
	@Override
	public void deleteAllOrders() {
		// delete all order
		orderRepository.deleteAll();
	}

}
