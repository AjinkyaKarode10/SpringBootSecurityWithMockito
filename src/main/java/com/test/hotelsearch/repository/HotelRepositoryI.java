package com.test.hotelsearch.repository;

import java.util.List;

import com.test.hotelsearch.entity.Hotel;

public interface HotelRepositoryI {

	public List<Hotel> searchHotel(String hotelName, String location,int pageNumber, int offset);
	
}
