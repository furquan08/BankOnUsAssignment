package com.furquan.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furquan.rest.dao.ContactRepository;
import com.furquan.rest.dao.CustomerRepository;
import com.furquan.rest.model.ContactDetailEntity;
import com.furquan.rest.model.Customer;
import com.furquan.rest.model.CustomerDTO;

/**
 * @author furquan
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repository;
	
	@Autowired
	ContactRepository contactRepository;

	@Override
	public List<Customer> getAllCustomers() {
		return repository.findAll();
	}

	@Override
	public Customer getCustomer(Long id) {

		Optional<Customer> customerEntity = repository.findById(id);

		if (customerEntity.isPresent()) {

			return customerEntity.get();

		} else {

			return null;
		}
	}
	@Override
	public ContactDetailEntity activateNumber(CustomerDTO cust ) {
		Optional<ContactDetailEntity> entity = Optional.ofNullable(contactRepository.findByContact(cust.getContactNumber(),cust.getStatus()));
		if (entity.isPresent()) {
			entity.get().setStatus(1);
			return contactRepository.save(entity.get());
		}
		return null;
		
	}

}