package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AddressService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RequiredArgsConstructor
@Controller
public class AddressController {
	
//	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
//	UserDetails userDetails = (UserDetails)principal; 
//	String username = ((UserDetails) principal).getUsername(); 
//	String password = ((UserDetails) principal).getPassword();
	
	@Autowired
	private final AddressRepository addressRepository;
	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final AddressService addressService;
	
	
	
	
	@PostMapping("/auth/address/add")
	public ResponseEntity<Address> registerAddress( @RequestBody Address address){
		try {
			
			
			Address _address = this.addressRepository.save(new Address(address.getAname(), address.getAreceipt(), address.getAmobile(), address.getApostcode(), address.getAaddress1(), address.getAaddress2(), address.getUsername()));
		
			return new ResponseEntity<>(_address, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/auth/address/list/{username}")
	public ResponseEntity<List<Address>> addressList(@RequestParam(required = false) String aname, @PathVariable("username") String username){
		try {
			
			List<Address> address = new ArrayList<Address>();
			
				addressRepository.findAllByUsername(username).forEach(address::add);
			  if(address.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} 
			return new ResponseEntity<>(address, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
	@GetMapping("/auth/address/modify/{aid}")
	public ResponseEntity<Address> addressDetail(@PathVariable("aid") Integer aid){
		Optional<Address> address = addressRepository.findById(aid);
		if(address.isPresent()) {
			address.get().setAname(address.get().getAname());
			address.get().setAreceipt(address.get().getAreceipt());
			address.get().setAmobile(address.get().getAmobile());
			address.get().setApostcode(address.get().getApostcode());
			address.get().setAaddress1(address.get().getAaddress1());
			address.get().setAaddress2(address.get().getAaddress2());
			return new ResponseEntity<>(address.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/auth/address/modify/{aid}")
	public ResponseEntity<Address> addressDetail(@PathVariable("aid") Integer aid, @RequestBody Address address){
		Optional<Address> addressData = addressRepository.findById(aid);
		Address newAddress = addressData.get();
		if(addressData.isPresent()) {
			newAddress.setAname(address.getAname());
			newAddress.setAreceipt(address.getAreceipt());
			newAddress.setAmobile(address.getAmobile());
			newAddress.setApostcode(address.getApostcode());
			newAddress.setAaddress1(address.getAaddress1());
			newAddress.setAaddress2(address.getAaddress2());
			return new ResponseEntity<>(addressRepository.save(newAddress), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/auth/address/delete/{aid}")
	public ResponseEntity<HttpStatus> addressDelete(@PathVariable("aid") Integer aid){
		try {
			addressRepository.deleteById(aid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	

	
	
}
