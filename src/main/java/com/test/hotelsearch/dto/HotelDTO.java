package com.test.hotelsearch.dto;

import java.util.List;

import com.test.hotelsearch.entity.Hotel;

public class HotelDTO {

	
   private Long id;
	
	private String name;
	
	private String location;
	
	private List<String> nearByPlaces;
	
	private int availableRooms;
	
	private int cost;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getNearByPlaces() {
		return nearByPlaces;
	}

	public void setNearByPlaces(List<String> nearByPlaces) {
		this.nearByPlaces = nearByPlaces;
	}

	public int getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(int availableRooms) {
		this.availableRooms = availableRooms;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
	public Hotel convertToEntity()
	{
		Hotel hotelEntity = new Hotel();
		hotelEntity.setId(this.id);
		hotelEntity.setName(this.name);
		hotelEntity.setLocation(this.location);
		hotelEntity.setNearByPlaces(this.nearByPlaces);
		hotelEntity.setAvailableRooms(this.availableRooms);
		hotelEntity.setCost(this.cost);
		
		return hotelEntity;
	}
	
}
