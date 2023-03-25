package com.customer.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.entity.Customer;
import com.customer.exception.ResourseNotFoundException;
import com.customer.model.CustomerDTO;
import com.customer.repository.AddressRepository;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;
import com.customer.util.CustomerConverter;

@Service
public class CustomerServiceImpl implements CustomerService {

	// get the HQL methods of customerRepository
	@Autowired
	private CustomerRepository customerRepository;

	// get the HQL methods of addressRepository
	@Autowired
	private AddressRepository addresRepository;

	// Converter of customer
	@Autowired
	private CustomerConverter customerConverter;

	// Create customer
	@Override
	public String createCustomer(Customer customer) {

		// save customer address
		addresRepository.save(customer.getAddress());

		// save customer
		customerRepository.save(customer);

		// if customer not null
		if (customer != null) {
			// success
			return "---------- CUSTOMER CREATED SUCCESSFULLY ----------";
		}

		// fail
		return "SOMETHING WENT WRONG! CUSTOMER NOT CREATED";
	}

	// get customer by id
	@Override
	public CustomerDTO getCustomerById(int id) {

		// get the customer
		Customer foundCustomer = customerRepository.findById(id).orElseThrow(() -> {
			return new ResourseNotFoundException("Customer", "ID", id);
		});

		// return the customer after converting into DTO
		return customerConverter.convertToCustomerDTO(foundCustomer);
	}

	// get all customers
	@Override
	public List<CustomerDTO> getAllCustomer() {

		// store all the customer in the list
		List<Customer> allCustomerList = customerRepository.findAll();

		List<CustomerDTO> allCustomerDTOs = new ArrayList<>();

		// for every customer
		for (Customer customer : allCustomerList) {
			// convert all the customer into customerDTO and add into allCustomerDTOs
			allCustomerDTOs.add(customerConverter.convertToCustomerDTO(customer));
		}

		// return allCustomerDTOs
		return allCustomerDTOs;
	}

	// Update customer
	@Override
	public CustomerDTO updateCustomer(int id, Customer customer) {

		// find the customer by id
		Customer foundCustomer = customerRepository.findById(id).orElseThrow(() -> {
			return new ResourseNotFoundException("Customer", "ID", id);
		});

		// set all the updates
		foundCustomer.setCustomerName(customer.getCustomerName());
		foundCustomer.setPhone(customer.getPhone());
		foundCustomer.setEmail(customer.getEmail());

		// save the changes
		customerRepository.save(foundCustomer);

		// return all the changes
		return customerConverter.convertToCustomerDTO(foundCustomer);

	}

	// delete customer
	@Override
	public String deleteCustomerById(int id) {

		// find the customer
		Optional<Customer> customer = customerRepository.findById(id);

		// if customer present
		if (customer.isPresent()) {
			// delete customer
			customerRepository.deleteById(id);
			// success
			return "---------- CUSTOMER DELETED SUCCESSFULLY ----------";
		}
		// fail
		return "---------- CUSTOMER NOT FOUND ----------";
	}

	// Delete all customers
	@Override
	public void deleteAllCustomers() {
		// delete all
		customerRepository.deleteAll();
	}

}
