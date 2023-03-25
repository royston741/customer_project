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

import com.customer.entity.Customer;
import com.customer.model.CustomerDTO;
import com.customer.service.CustomerService;
import com.customer.util.CustomerConverter;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerConverter customerConverter;

	// CREATE
	@PostMapping("/createCustomer")
	public String createCustomer(@RequestBody CustomerDTO customerDTO) {
		final Customer customer = customerConverter.convertToEntity(customerDTO);
		return customerService.createCustomer(customer);
	}

	// READ
	@GetMapping("/getCustomerById/{id}")
	public CustomerDTO getCustomerById(@PathVariable("id") int id) {
		return customerService.getCustomerById(id);
	}

	// READ ALL -----------> ADMIN
	@GetMapping("/getAllCustomer")
	public List<CustomerDTO> getAllCustomer() {
		return customerService.getAllCustomer();
	}

	// UPDATE
	@PutMapping("/updateCustomerById/{id}")
	public CustomerDTO updateCustomer(@PathVariable("id") int id, @RequestBody CustomerDTO customerDTO) {
		Customer customer = customerConverter.convertToEntity(customerDTO);
		return customerService.updateCustomer(id, customer);
	}

	// DELETE
	@DeleteMapping("/deleteCustomerById/{id}")
	public String deleteCustomerById(@PathVariable("id") int id) {
		return customerService.deleteCustomerById(id);
	}

	// DELETE ALL -----------> ADMIN
	@DeleteMapping("/deleteAllCustomers")
	public ResponseEntity<String> deleteAllCustomers() {
		customerService.deleteAllCustomers();
		return new ResponseEntity<>("---------- ALL CUSTOMERS DELETED ----------", HttpStatus.OK);
	}

}
