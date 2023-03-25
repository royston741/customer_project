package com.customer.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.customer.entity.Customer;

import lombok.Data;

@Data
public class OrderDTO {
	private int orderId;

	@NotNull
	@Size(min = 1)
	private String orderName;

	@NotNull
	@Size(min = 1)
	private double orderPrice;

	@NotNull
	@Size(min = 1)
	private int orderQuantity;

	private Customer customer;
}
