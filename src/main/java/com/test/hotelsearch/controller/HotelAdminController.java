package com.test.hotelsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.test.hotelsearch.dto.HotelBulkAddResponse;
import com.test.hotelsearch.dto.HotelBulkUpdateResponse;
import com.test.hotelsearch.dto.HotelDTO;
import com.test.hotelsearch.exception.HotelIdInvalidException;
import com.test.hotelsearch.exception.HotelNameInvalidException;
import com.test.hotelsearch.exception.HotelNameNotFoundException;
import com.test.hotelsearch.service.HotelServiceI;
import com.test.hotelsearch.utility.ValidationUtil;

@RestController
@RequestMapping(value="/admin")
public class HotelAdminController {


	@Autowired
	HotelServiceI hotelServiceI;
	
	@Autowired
	ValidationUtil validationUtil;
	

	@PostMapping(value = "/addHotel")
	@PreAuthorize("hasAuthority('CREATE')")
	public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelDTO hotelDTO) throws HotelNameInvalidException{
		
		validationUtil.validateHotelName(hotelDTO.getName());
		
		return ResponseEntity.ok(hotelServiceI.addHotel(hotelDTO));
	}
	
	@PostMapping(value = "/addBulkHotel")
	@PreAuthorize("hasAuthority('CREATE')")
	public ResponseEntity<HotelBulkAddResponse> addBulkHotel(@RequestBody List<HotelDTO> hotelDTO) {
		System.out.println("Inside hasAdminPermission");
		
		return ResponseEntity.ok(hotelServiceI.addListOfHotel(hotelDTO));
	}
	
	@PostMapping(value = "/updateHotel")
	@PreAuthorize("hasAuthority('CREATE')")
	public ResponseEntity<HotelDTO> updateHotel(@RequestBody HotelDTO hotelDTO) throws HotelNameInvalidException,HotelIdInvalidException, HotelNameNotFoundException{
		
		validationUtil.validateHotelUpdate(hotelDTO);
		
		return ResponseEntity.ok(hotelServiceI.updateHotel(hotelDTO));
	}
	
	@PostMapping(value = "/updateBulkHotel")
	@PreAuthorize("hasAuthority('CREATE')")
	public ResponseEntity<HotelBulkUpdateResponse> updateBulkHotel(@RequestBody List<HotelDTO> hotelDTO) {
		
		return ResponseEntity.ok(hotelServiceI.updateBulkHotel(hotelDTO));
	}
	
}
