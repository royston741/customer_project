package com.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.customer.entity.Order;

//Interface Order extends JPA Interfaace
public interface OrderRepository extends JpaRepository<Order, Integer> {

	// find all the orders greater than price
	@Query("from Order where orderPrice >:s")
	List<Order> findGreaterPrice(@Param("s") double price);

	// find all the orders less than price
	@Query("from Order where orderPrice <:s")
	List<Order> findLessPrice(@Param("s") double price);

	// find all the orders less than price
//	@Query("from Order where orderPrice between :p and :s")
//	List<Order> findBetweenPrice(@Param("s") double maxPrice, @Param("p") double minPrice);

	List<Order> findByOrderPriceBetween(@Param("s") double maxPrice, @Param("p") double minPrice);

}
