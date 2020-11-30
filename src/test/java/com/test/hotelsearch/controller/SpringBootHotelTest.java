package com.test.hotelsearch.controller;


import java.util.Arrays;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.test.hotelsearch.controller.HotelController;
import com.test.hotelsearch.dto.HotelDTO;
import com.test.hotelsearch.dto.HotelSearchResponse;
import com.test.hotelsearch.entity.Hotel;
import com.test.hotelsearch.service.CustomUserDetailsService;
import com.test.hotelsearch.service.HotelServiceI;

//@RunWith(SpringRunner.class)
//@WebMvcTest(value = HotelController.class)
//@WithMockUser
public class SpringBootHotelTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HotelServiceI hotelService;
	
	@Autowired
	CustomUserDetailsService userService;
	
	
//
//	@Autowired
//	private WebApplicationContext context;
//
//	@Autowired
//	private Filter springSecurityFilterChain;
//
//	
//	
//	@Before
//	public void setup() {
//	    this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
//	            .addFilters(springSecurityFilterChain).build();
//	}
//	
	
	
//	@Test
//	@WithMockUser
//	public void retrieveDetailsForCourse() throws Exception {
//		
//		HotelSearchResponse response = new HotelSearchResponse();
//		
//		Hotel mockHotel = new Hotel();
//		mockHotel.setName("Divine Residency Four");
//		mockHotel.setLocation("Ghatkopar");
//		mockHotel.setNearByPlaces(Arrays.asList("Ghatkopar Station","Metro"));
//		mockHotel.setAvailableRooms(5);
//		mockHotel.setCost(1200);
//		
//		response.setHotelList(Arrays.asList(mockHotel.convertToDTO()));
//		response.setTotalItems(1);
//		
//		Mockito.when(
//				hotelService.getHotels(null, "Ghat", 1, 5)).thenReturn(response);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//				"/hotel/searchHotels").accept(
//				MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		System.out.println(result.getResponse());
//		String expected = "{\r\n" + 
//				"    \"hotelList\": [\r\n" + 
//				"        {\r\n" + 
//				"            \"id\": 2,\r\n" + 
//				"            \"name\": \"Divine Residency Four\",\r\n" + 
//				"            \"location\": \"Ghatkopar\",\r\n" + 
//				"            \"nearByPlaces\": [\r\n" + 
//				"                \"Ghatkopar Station\",\r\n" + 
//				"                \"Metro\"\r\n" + 
//				"            ],\r\n" + 
//				"            \"availableRooms\": 5,\r\n" + 
//				"            \"cost\": 1200\r\n" + 
//				"        }\r\n" + 
//				"    ],\r\n" + 
//				"    \"totalItems\": 1\r\n" + 
//				"}";
//
//		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}
//
//		JSONAssert.assertEquals(expected, result.getResponse()
//				.getContentAsString(), false);
//	}

	
}
