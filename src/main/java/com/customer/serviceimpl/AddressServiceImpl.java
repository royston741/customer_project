package com.customer.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.entity.Address;
import com.customer.exception.ResourseNotFoundException;
import com.customer.model.AddressDTO;
import com.customer.repository.AddressRepository;
import com.customer.service.AddressService;
import com.customer.util.AddressConverter;

@Service
public class AddressServiceImpl implements AddressService {

	// addressRepositry for HQL methods
	@Autowired
	private AddressRepository addressRepository;

	// Address converter
	@Autowired
	private AddressConverter addressConverter;

	// get address
	@Override
	public AddressDTO getAddressById(int id) {

		// find address
		Address foundAddress = addressRepository.findById(id).orElseThrow(() -> {
			return new ResourseNotFoundException("Address", "ID", id);
		});

		// return address
		return addressConverter.convertToAddressDTO(foundAddress);
	}

	// get all address
	@Override
	public List<AddressDTO> getAllAddress() {

		// find all address
		List<Address> addresses = addressRepository.findAll();

		List<AddressDTO> allAddressDTOs = new ArrayList<>();

		// for every address
		for (Address addr : addresses) {
			// convert into addressDto and add into allAddrssDTOs
			allAddressDTOs.add(addressConverter.convertToAddressDTO(addr));
		}

		// return allAddressDTOs
		return allAddressDTOs;
	}

	// update address
	@Override
	public AddressDTO updateAddress(int id, Address address) {

		// find address to update
		Address foundAddress = addressRepository.findById(id).orElseThrow(() -> {
			return new ResourseNotFoundException("Address", "ID", id);
		});

		// set the changes
		foundAddress.setCity(address.getCity());
		foundAddress.setState(address.getState());
		foundAddress.setPincode(address.getPincode());

		// save the changes
		addressRepository.save(foundAddress);

		// return changes
		return addressConverter.convertToAddressDTO(foundAddress);
	}

	// delete by id
	@Override
	public String deleteAddressById(int id) {

		// find the address
		Optional<Address> address = addressRepository.findById(id);

		// if address is present
		if (address.isPresent()) {
			// delete address
			addressRepository.deleteById(id);
			// success
			return "------- ADDRESS DELETED SUCCESSFULLY -------";
		}
		// fail
		return "------- ADDRESS NOT FOUND -------";
	}

	// delete all address
	@Override
	public void deleteAllAddress() {
		// delete all
		addressRepository.deleteAll();
	}
}
