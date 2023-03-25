package com.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.entity.Customer;

//Interface Customer extends JPA Interfaace
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
