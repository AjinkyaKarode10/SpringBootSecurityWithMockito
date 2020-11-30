package com.test.hotelsearch;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.servlet.Filter;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.hotelsearch.controller.HotelController;
import com.test.hotelsearch.dto.HotelDTO;
import com.test.hotelsearch.dto.HotelSearchResponse;
import com.test.hotelsearch.entity.Hotel;
import com.test.hotelsearch.service.HotelServiceI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
@ActiveProfiles("test")
public class HotelSearchTests {

	@InjectMocks
	HotelController controller;
	
	
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private Filter springSecurityFilterChain;

	private MockMvc mockMvc;
	
	@MockBean
	HotelServiceI hotelServiceI;
	
	@Before
	public void setup() {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
	            .addFilters(springSecurityFilterChain).build();
	}
	
	@WithMockUser("ajinkya")
	@Test
	public void testSearchHotelResults() throws Exception {
		
		HotelSearchResponse response = new HotelSearchResponse();
		
		Hotel mockHotel = new Hotel();
		mockHotel.setId(Long.valueOf(2));
		mockHotel.setName("Divine Residency Four");
		mockHotel.setLocation("Ghatkopar");
		mockHotel.setNearByPlaces(Arrays.asList("Ghatkopar Station","Metro"));
		mockHotel.setAvailableRooms(5);
     	mockHotel.setCost(1200);
     
     	response.setHotelList(Arrays.asList(mockHotel.convertToDTO()));
		response.setTotalItems(1);
		
		Mockito.when(
				hotelServiceI.getHotels(Mockito.anyString(), Mockito.anyString(), 
						Mockito.anyInt(), Mockito.anyInt())).thenReturn(response);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/hotel/searchHotels")
				.param("hotelName", "")
				.param("location", "Ghat")
				.param("pageNumber", "1")
				.param("pageSize", "5")
				.accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		String expected = "{\"hotelList\":[{\"id\":2,\"name\":\"Divine Residency Four\",\"location\":\"Ghatkopar\",\"nearByPlaces\":[\"Ghatkopar Station\",\"Metro\"],\"availableRooms\":5,\"cost\":1200}],\"totalItems\":1}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		//assertEquals("Hello World", result.getResponse()
		//		.getContentAsString());
	
		
	}
	
	
	@WithMockUser(username = "Ajinkya", password = "ajinkya", roles = "ADMIN")
	@Test
	public void testAddHotelResults() throws Exception {
		
		Hotel mockHotel = new Hotel();
		mockHotel.setId(Long.valueOf(2));
		mockHotel.setName("Divine Residency Four");
		mockHotel.setLocation("Ghatkopar");
		mockHotel.setNearByPlaces(Arrays.asList("Ghatkopar Station","Metro"));
		mockHotel.setAvailableRooms(5);
     	mockHotel.setCost(1200);
     
     	String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("Ajinkya:ajinkya").getBytes()));
		Mockito.when(
				hotelServiceI.addHotel(Mockito.any(HotelDTO.class))).thenReturn(mockHotel.convertToDTO());
		//String requestStr = "{ "id":2,"name":"Divine Residency Four","location":"Ghatkopar","nearByPlaces":["Ghatkopar Station","Metro"],"availableRooms":5,"cost":1200}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/admin/addHotel")
				.header("Authorization", basicDigestHeaderValue)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(mockHotel))
				.accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(result.getResponse().getStatus());
		String expected = "{\"id\":2,\"name\":\"Divine Residency Four\",\"location\":\"Ghatkopar\",\"nearByPlaces\":[\"Ghatkopar Station\",\"Metro\"],\"availableRooms\":5,\"cost\":1200}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		//assertEquals("Hello World", result.getResponse()
		//		.getContentAsString());
	
		
	}
	
	public  String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
