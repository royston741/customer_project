/*	
 	Progtam : Customer management system (Entity : Customer, Order. Address)
	@Author : Royston
	@Date : 19 Dec
*/

package com.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerProjectApplication.class, args);
		System.out.println("Server is running.....");
	}

}
