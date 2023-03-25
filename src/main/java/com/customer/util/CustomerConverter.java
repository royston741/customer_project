package com.customer.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.customer.entity.Customer;
import com.customer.model.CustomerDTO;

@Component
public class CustomerConverter {

	// CustomerDTO to Customer object
	public Customer convertToEntity(CustomerDTO customerDTO) {
		// create customer object
		Customer customer = new Customer();

		// if customerDTO not null
		if (customerDTO != null) {
			// copy its properties in customer
			BeanUtils.copyProperties(customerDTO, customer);
		}

		// return customer
		return customer;
	}

	// Customer to CustomerDTO object
	public CustomerDTO convertToCustomerDTO(Customer customer) {

		// create customerDTO object
		CustomerDTO customerDTO = new CustomerDTO();

		// if customer not null
		if (customer != null) {
			// copy its properties in customer
			BeanUtils.copyProperties(customer, customerDTO);
		}

		// return customerDTO
		return customerDTO;
	}
}
