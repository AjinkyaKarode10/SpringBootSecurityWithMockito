package com.test.hotelsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.hotelsearch.dto.HotelDTO;
import com.test.hotelsearch.dto.HotelSearchResponse;
import com.test.hotelsearch.service.HotelServiceI;

@RestController
@RequestMapping(value="/hotel")
public class HotelController {

	@Autowired
	HotelServiceI hotelServiceI;
	

	@RequestMapping(method=RequestMethod.GET,value = "/searchHotels")
	public ResponseEntity<HotelSearchResponse> searchHotels(@RequestParam String hotelName,@RequestParam String location,
													   @RequestParam Integer pageNumber,@RequestParam Integer pageSize) {
		
		return ResponseEntity.ok(hotelServiceI.getHotels(location, hotelName, pageNumber, pageSize));
	}
	
	
	
	
	
}
