package com.furquan.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furquan.rest.exception.RecordNotFoundException;
import com.furquan.rest.model.ContactDetailEntity;
import com.furquan.rest.model.Customer;
import com.furquan.rest.model.CustomerDTO;
import com.furquan.rest.service.CustomerService;

/**
 * @author furquan
 *
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/customer/getAllPhone", produces = "application/json")
	public ResponseEntity<List<Customer>> getAllPhone() {
		List<Customer> list = customerService.getAllCustomers();
		if (list == null || list.isEmpty()) {
			throw new RecordNotFoundException("No data present:");
		}
		return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(path = "/customer/{id}", produces = "application/json")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
		Customer entity = customerService.getCustomer(id);
		if (entity == null) {
			throw new RecordNotFoundException("Invalid customer id : " + id);
		}

		return new ResponseEntity<Customer>(entity, HttpStatus.OK);

	}

	@PostMapping(path = "/customer/activateNumnber", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ContactDetailEntity> activateCustomerNumber(@Valid @RequestBody CustomerDTO customerDTO) {
		ContactDetailEntity entity = customerService.activateNumber(customerDTO);
		if (entity == null) {
			throw new RecordNotFoundException("Invalid customer id : " + customerDTO.getCustId() +"or number "+customerDTO.getContactNumber());
		}
		return new ResponseEntity<ContactDetailEntity>(entity, HttpStatus.OK);
	}
}
