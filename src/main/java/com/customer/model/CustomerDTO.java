package com.customer.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.customer.entity.Address;
import com.customer.entity.Order;

import lombok.Data;

@Data
public class CustomerDTO {
	private int id;

	@NotNull
	@Size(min = 1)
	private String customerName;

	@NotNull
	@Size(min = 10, max = 10)
	private long phone;

	@NotNull
	@Size(min = 1)
	private String email;

	private Address address;

	private List<Order> order;
}
