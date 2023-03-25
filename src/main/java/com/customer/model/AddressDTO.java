package com.customer.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AddressDTO {
	private int addressId;

	@NotNull
	@Size(min = 1)
	private String state;

	@NotNull
	@Size(min = 1)
	private String city;

	@NotNull
	@Size(min = 1)
	private String area;

	@NotNull
	@Size(min = 1)
	private String apartment;

	@NotNull
	@Size(min = 6)
	private long pincode;

}
