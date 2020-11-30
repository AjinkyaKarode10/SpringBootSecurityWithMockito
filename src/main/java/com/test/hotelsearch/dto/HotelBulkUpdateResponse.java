package com.test.hotelsearch.dto;

import java.util.List;

public class HotelBulkUpdateResponse {

	
    private List<HotelDTO> successfullUpdates;
	
	private int totalItemsUpdated;
	
	private List<FailureHotelNameInsertion> failure;

	public List<HotelDTO> getSuccessfullUpdates() {
		return successfullUpdates;
	}

	public void setSuccessfullUpdates(List<HotelDTO> successfullUpdates) {
		this.successfullUpdates = successfullUpdates;
	}

	public int getTotalItemsUpdated() {
		return totalItemsUpdated;
	}

	public void setTotalItemsUpdated(int totalItemsUpdated) {
		this.totalItemsUpdated = totalItemsUpdated;
	}

	public List<FailureHotelNameInsertion> getFailure() {
		return failure;
	}

	public void setFailure(List<FailureHotelNameInsertion> failure) {
		this.failure = failure;
	}
	
	

	
}
