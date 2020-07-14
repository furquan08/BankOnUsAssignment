package com.furquan.rest.service;

import java.util.List;

import com.furquan.rest.model.ContactDetailEntity;
import com.furquan.rest.model.Customer;
import com.furquan.rest.model.CustomerDTO;


/**
 * @author furquan
 *
 */
public interface CustomerService {


	public List<Customer> getAllCustomers();
	
	public Customer getCustomer(Long id);
	
	public ContactDetailEntity activateNumber(CustomerDTO cust );
}