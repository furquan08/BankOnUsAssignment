package com.furquan.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.furquan.rest.dao.CustomerRepository;
import com.furquan.rest.model.ContactDetailEntity;
import com.furquan.rest.model.Customer; 

/**
 * @author furquan
 *
 */
@SpringBootApplication 
public class SpringBootCustomerApplication {
	
	 @Autowired
	  private CustomerRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCustomerApplication.class, args);
    }
    
    // spring calls after the initialization of bean properties
@PostConstruct
private void initDb() {
Customer customer = new Customer();
customer.setId(1L);
customer.setName("ahmad");
ContactDetailEntity conStub = new ContactDetailEntity();
conStub.setId(1L);
conStub.setContactNumber(99999994);
List<ContactDetailEntity> contactDetail=new ArrayList<ContactDetailEntity>();
contactDetail.add(conStub);
customer.setContactDetail(contactDetail);
repository.save(customer);
}
}
