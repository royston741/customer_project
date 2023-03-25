package com.customer.service;

import java.util.List;

import com.customer.entity.Customer;
import com.customer.model.CustomerDTO;

public interface CustomerService {

	// Create
	String createCustomer(Customer customer);

	// Read
	CustomerDTO getCustomerById(int id);

	// Read All
	List<CustomerDTO> getAllCustomer();

	// Update
	CustomerDTO updateCustomer(int id, Customer customer);

	// Delete
	String deleteCustomerById(int id);

	// Delete All
	void deleteAllCustomers();
}
