package com.customer.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.customer.entity.Order;
import com.customer.model.OrderDTO;

@Component
public class OrderConverter {

	// convert OrderDTO to Order object
	public Order convertToEntity(OrderDTO OrderDTO) {

		// Create Order object
		Order order = new Order();

		// if OrderDTO not null
		if (OrderDTO != null) {

			// copy properties of orderDTO to order
			BeanUtils.copyProperties(OrderDTO, order);
		}

		// return order
		return order;
	}

	// convert Order to OrderDTO object
	public OrderDTO convertToOrderDTO(Order order) {

		// Create OrderDTO object
		OrderDTO orderDTO = new OrderDTO();

		// if order not null
		if (order != null) {

			// copy properties of order to orderDTO
			BeanUtils.copyProperties(order, orderDTO);
		}

		// return orderDTO
		return orderDTO;
	}
}
