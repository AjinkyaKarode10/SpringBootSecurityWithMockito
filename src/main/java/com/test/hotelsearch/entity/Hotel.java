package com.test.hotelsearch.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.test.hotelsearch.dto.HotelDTO;

@Entity
@Table(name="hotel")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String location;
	
	@ElementCollection
	@CollectionTable(name="nearByPlaces")
	private List<String> nearByPlaces;
	
	@Column
	private int availableRooms;
	
	@Column
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
	
	
	public HotelDTO convertToDTO()
	{
		HotelDTO responseDTO = new HotelDTO();
		responseDTO.setId(this.id);
		responseDTO.setName(this.name);
		responseDTO.setLocation(this.location);
		responseDTO.setNearByPlaces(this.nearByPlaces);
		responseDTO.setAvailableRooms(this.availableRooms);
		responseDTO.setCost(this.cost);
		
		return responseDTO;
	}
	
}
