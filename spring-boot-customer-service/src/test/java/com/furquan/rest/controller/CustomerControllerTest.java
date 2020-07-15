package com.furquan.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.furquan.rest.model.Customer;
import com.furquan.rest.model.CustomerDTO;
import com.furquan.test.AbstractTest;

public class CustomerControllerTest extends AbstractTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   @Test
   public void getAllPhone() throws Exception {
      String uri = "/api/customer/getAllPhone";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Customer[] productlist = super.mapFromJson(content, Customer[].class);
      assertTrue(productlist.length > 0);
   }
   
   @Test
   public void getCustomer() throws Exception {
      String uri = "/api/customer/1";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Customer customer = super.mapFromJson(content, Customer.class);
      assertEquals(200, status);
      assertEquals(content, "{\"id\":1,\"name\":\"ahmad\",\"contactDetail\":[{\"id\":1,\"contactNumber\":99999994,\"status\":0}]}");
   }
   @Test
   public void activateNumber() throws Exception {
      String uri = "/api/customer/activateNumnber";
      CustomerDTO customerDTO=new CustomerDTO();
      customerDTO.setCustId(1L);
      customerDTO.setContactNumber(99999994);
      String inputJson = super.mapToJson(customerDTO);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, "{\"id\":1,\"contactNumber\":99999994,\"status\":1}");
   }
}