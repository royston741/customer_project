package com.customer.service;

import java.util.List;

import com.customer.entity.Address;
import com.customer.model.AddressDTO;

public interface AddressService {

	// Read
	AddressDTO getAddressById(int id);

	// Read All
	List<AddressDTO> getAllAddress();

	// Update
	AddressDTO updateAddress(int id, Address address);

	// Delete
	String deleteAddressById(int id);

	// Delete All
	void deleteAllAddress();

}
