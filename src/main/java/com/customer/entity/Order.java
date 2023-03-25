package com.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;

	@Column(name = "order_name")
	private String orderName;

	@Column(name = "order_price")
	private double orderPrice;

	@Column(name = "order_quantity")
	private int orderQuantity;

	@ManyToOne
	@JsonIgnore
	private Customer customer;

	@Builder
	public Order(String orderName, double orderPrice, int orderQuantity, Customer customer) {
		super();
		this.orderName = orderName;
		this.orderPrice = orderPrice;
		this.orderQuantity = orderQuantity;
		this.customer = customer;
	}

}
