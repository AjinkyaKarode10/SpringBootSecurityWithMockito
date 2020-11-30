package com.test.hotelsearch.service;

import java.util.List;

import com.test.hotelsearch.dto.HotelBulkAddResponse;
import com.test.hotelsearch.dto.HotelBulkUpdateResponse;
import com.test.hotelsearch.dto.HotelDTO;
import com.test.hotelsearch.dto.HotelSearchResponse;
import com.test.hotelsearch.exception.HotelNameNotFoundException;

public interface HotelServiceI {

	public HotelSearchResponse getHotels(String location, String hotelName,Integer pageNumber, Integer pageSize);
	
	public HotelDTO addHotel(HotelDTO hotelDTO);
	
	public HotelBulkAddResponse addListOfHotel(List<HotelDTO> hotelDTO);
	
	public HotelDTO updateHotel(HotelDTO hotelDTO) throws HotelNameNotFoundException;
	
	public HotelBulkUpdateResponse updateBulkHotel(List<HotelDTO> hotelDTO);
	
	
	
}
