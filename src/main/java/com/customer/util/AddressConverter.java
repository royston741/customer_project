package com.customer.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.customer.entity.Address;
import com.customer.model.AddressDTO;

@Component
public class AddressConverter {

	// Convert AddressDTO to Address object
	public Address convertToAddressEntity(AddressDTO addressDTO) {

		// Create Address object
		Address address = new Address();

		// if addressDTO not null
		if (addressDTO != null) {
			// copy the properties of addressDTO to address
			BeanUtils.copyProperties(addressDTO, address);
		}

		// return address
		return address;
	}

	// Convert Address to AddressDTO object
	public AddressDTO convertToAddressDTO(Address address) {

		// Create AddressDTO object
		AddressDTO addressDTO = new AddressDTO();

		// if address not null
		if (address != null) {
			// copy the properties of address to addressDTO
			BeanUtils.copyProperties(address, addressDTO);
		}

		// return addressDTO
		return addressDTO;
	}
}
