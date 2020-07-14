package com.furquan.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.furquan.rest.dao.ContactRepository;
import com.furquan.rest.model.ContactDetailEntity;
import com.furquan.rest.model.Customer;
import com.furquan.rest.service.CustomerService;
import com.furquan.test.TestUtils;
import com.google.gson.reflect.TypeToken;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * 
 * 
 * 
 * @author furqaun
 * 
 * 
 */

@RunWith(SpringRunner.class)

@WebMvcTest(CustomerControllerTest.class)

public class CustomerControllerTest {

	@Autowired

	private MockMvc mockMvc;

	@MockBean
	CustomerService customerService;
	
	@MockBean
	ContactRepository contactRepository;
	

	private final String URL = "/api/customer/";
	
	  @Test
	    public void getAllPhone() throws Exception {
	        // given
			Customer empStub = new Customer();
			ContactDetailEntity conStub = new ContactDetailEntity();
			conStub.setId(1L);
			conStub.setContactNumber(99999994L);
			empStub.setName("ahmad");
			List<ContactDetailEntity> contactDetail=new ArrayList<ContactDetailEntity>();
			contactDetail.add(conStub);
			empStub.setContactDetail(contactDetail);	

	        List<Customer> stocks = Arrays.asList(empStub);
	        given(customerService.getAllCustomers()).willReturn(stocks);

	        // when + then
	        this.mockMvc.perform(get("/api/customer/getAllPhone"))
	                .andExpect(status().isOk())
	                .andExpect(content().json("{'id':1,'name':'ahmad','contactDetail':[{'id':1,'contactNumber':99999994,'status':0}]}"));
	    }
	
	
	@Test
	public void testGetEmployee() throws Exception {

		// prepare data and mock's behaviour
		Customer empStub = new Customer();
		ContactDetailEntity conStub = new ContactDetailEntity();
		conStub.setId(1L);
		conStub.setContactNumber(9887665544L);
		empStub.setName("furquan");
		List<ContactDetailEntity> contactDetail=new ArrayList<ContactDetailEntity>();
		contactDetail.add(conStub);
		empStub.setContactDetail(contactDetail);
		when(customerService.getCustomer(any(Long.class))).thenReturn(empStub);

		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "{id}", new Long(1)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify

		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
		// verify that service method was called once
		verify(customerService).getCustomer(any(Long.class));
		Customer resultCustomer = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Customer.class);
		assertNotNull(resultCustomer);
		assertEquals(1l, resultCustomer.getId().longValue());

	}

	@Test

	public void testGetCustomerNotExist() throws Exception {

		// prepare data and mock's behaviour

		// Not Required as employee Not Exist scenario
		// execute

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "{id}", new Long(1)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		// verify

		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND.value(), status);

		// verify that service method was called once

		verify(customerService).getCustomer(any(Long.class));
		Customer resultEmployee = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Customer.class);
		assertNull(resultEmployee);

	}

	@Test

	public void testGetAllCustomer() throws Exception {

		// prepare data and mock's behaviour

		List<Customer> cusList = buildCustomers();
		when(customerService.getAllCustomers()).thenReturn(cusList);

		// execute

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))

				.andReturn();

		// verify

		int status = result.getResponse().getStatus();

		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once

		verify(customerService).getAllCustomers();

		// get the List<Customer> from the Json response

		TypeToken<List<Customer>> token = new TypeToken<List<Customer>>() {

		};

		@SuppressWarnings("unchecked")

		List<Customer> cusListResult = TestUtils.jsonToList(result.getResponse().getContentAsString(), token);
		assertNotNull("Customer not found", cusListResult);
		assertEquals("Incorrect Customer List", cusList.size(), cusListResult.size());

	}

	@Test
	public void testActivateNumber() throws Exception {

		// prepare data and mock's behaviour

		// here the stub is the updated Customer object with ID equal to ID of

		// Customer need to be updated

		Customer cusStub = new Customer();
		ContactDetailEntity conStub = new ContactDetailEntity();
		conStub.setId(1L);
		conStub.setContactNumber(9887665544L);
		cusStub.setName("furquan");
		List<ContactDetailEntity> contactDetail=new ArrayList<ContactDetailEntity>();
		cusStub.setContactDetail(contactDetail);
		
		when(customerService.getCustomer(any(Long.class))).thenReturn(cusStub);

		// execute

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)

				.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(cusStub))).andReturn();

		// verify
		int status = result.getResponse().getStatus();

		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once

		verify(contactRepository).save(any(ContactDetailEntity.class));

	}

	private List<Customer> buildCustomers() {

		Customer e1 = new Customer();
		e1.setId(1L);
		e1.setName("customer1");
		ContactDetailEntity cont=new ContactDetailEntity();
		cont.setContactNumber(999765428L);
		cont.setId(1L);
		cont.setStatus(1);
		List<ContactDetailEntity> contactDetailList=new ArrayList<ContactDetailEntity>();
		contactDetailList.add(cont);
		e1.setContactDetail(contactDetailList);
		Customer e2 = new Customer();
		e2.setId(1L);
		e2.setName("customer1");
		ContactDetailEntity cont2=new ContactDetailEntity();
		cont2.setContactNumber(999765428L);
		cont2.setId(1L);
		cont2.setStatus(1);
		List<ContactDetailEntity> contactDetailList2=new ArrayList<ContactDetailEntity>();
		contactDetailList2.add(cont2);
		List<Customer> cusList = Arrays.asList(e1, e2);
		return cusList;

	}

}