package com.test.hotelsearch.dto;

import java.util.List;

public class HotelBulkAddResponse {

	private List<HotelDTO> successfullInserts;
	
	private int totalItemsAdded;
	
	private List<FailureHotelNameInsertion> failure;

	public List<HotelDTO> getSuccessfullInserts() {
		return successfullInserts;
	}

	public void setSuccessfullInserts(List<HotelDTO> successfullInserts) {
		this.successfullInserts = successfullInserts;
	}

	public int getTotalItemsAdded() {
		return totalItemsAdded;
	}

	public void setTotalItemsAdded(int totalItemsAdded) {
		this.totalItemsAdded = totalItemsAdded;
	}

	public List<FailureHotelNameInsertion> getFailure() {
		return failure;
	}

	public void setFailure(List<FailureHotelNameInsertion> failure) {
		this.failure = failure;
	}
	
	
	
}
