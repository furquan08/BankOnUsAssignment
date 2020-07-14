package com.furquan.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.furquan.rest.model.Customer;

/**
 * @author furquan
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	

}
