package com.customer.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.customer.entity.Address;
import com.customer.entity.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.service.AddressService;
import com.customer.service.CustomerService;

@SpringBootTest
public class CustomerTest {

	@Autowired
	private AddressService addressService;

	@Autowired
	private CustomerService customerService;

	@MockBean
	private CustomerRepository customerRepository;

	Customer customer;

	Address address;

	@BeforeEach
	void init() {
		address = address.builder().state("MH").city("Mumbai").area("Chicken ghar").apartment("Namdeo park")
				.pincode(32898).build();

		customer = customer.builder().customerName("roy").email("roy71ston@gamil.com").phone(993783783).address(address)
				.build();
	}

	@Test
	void testCreateCustomer() {
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		assertThat(customerService.createCustomer(customer))
				.isEqualTo("---------- CUSTOMER CREATED SUCCESSFULLY ----------");
	}

}
