package com.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customer_address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;

	@Column(name = "state", length = 50)
	private String state;

	@Column(name = "city", length = 50)
	private String city;

	@Column(name = "area", length = 50)
	private String area;

	@Column(name = "apartment", length = 100)
	private String apartment;

	@Column(name = "pincode")
	private long pincode;

	@Builder
	public Address(String state, String city, String area, String apartment, long pincode) {
		super();
		this.state = state;
		this.city = city;
		this.area = area;
		this.apartment = apartment;
		this.pincode = pincode;
	}

}
