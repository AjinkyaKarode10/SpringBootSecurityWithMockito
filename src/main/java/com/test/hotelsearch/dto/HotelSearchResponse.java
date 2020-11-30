package com.test.hotelsearch.dto;

import java.util.List;

public class HotelSearchResponse {

	private List<HotelDTO> hotelList;
	
	private int totalItems;

	public List<HotelDTO> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<HotelDTO> hotelList) {
		this.hotelList = hotelList;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HotelSearchResponse [hotelList=");
		builder.append(hotelList);
		builder.append(", totalItems=");
		builder.append(totalItems);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
