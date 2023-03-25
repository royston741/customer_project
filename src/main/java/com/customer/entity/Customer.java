package com.customer.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "customer_details")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "customer_name", nullable = false)
	private String customerName;

	@Column(name = "customer_phoneNo", nullable = false)
	private long phone;

	@Column(name = "customer_email")
	private String email;

	// One to one relation ship between Customer and address
	@OneToOne(cascade = CascadeType.ALL) // one table to another table
	private Address address;

	// One to many relation ship between Customer and orders
//	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
//	@JsonIgnore

	@OneToMany(cascade = CascadeType.ALL) // one table to many table
	@JoinColumn(name = "customer_id") // this column will act as a foreign key
	private List<Order> order;

	@Builder
	public Customer(String customerName, long phone, String email, Address address) {
		super();
		this.customerName = customerName;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

}
