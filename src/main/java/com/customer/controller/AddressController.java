package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.entity.Address;
import com.customer.model.AddressDTO;
import com.customer.service.AddressService;
import com.customer.util.AddressConverter;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private AddressConverter addressConverter;

	// READ
	@GetMapping("/getAddressById/{id}")
	public AddressDTO getAddressById(@PathVariable("id") int id) {
		return addressService.getAddressById(id);
	}

	// READ ALL -----------> ADMIN
	@GetMapping("/getAllAddress")
	public List<AddressDTO> getAllAddress() {
		return addressService.getAllAddress();
	}

	// UPDATE
	@PutMapping("/updateAddress/{id}")
	public AddressDTO updateAddress(@PathVariable("id") int id, @RequestBody AddressDTO addressDTO) {
		Address address = addressConverter.convertToAddressEntity(addressDTO);
		return addressService.updateAddress(id, address);

	}

	// DELETE
	@DeleteMapping("/deleteAddressById/{id}")
	public String deleteAddressById(@PathVariable("id") int id) {
		return addressService.deleteAddressById(id);
	}

	// DELETE ALL -----------> ADMIN
	@DeleteMapping("/deleteAllAddress")
	public ResponseEntity<String> deleteAllAddress() {
		addressService.deleteAllAddress();
		return new ResponseEntity<>("------- ALL ADDRESS DELETED -------", HttpStatus.OK);
	}
}
