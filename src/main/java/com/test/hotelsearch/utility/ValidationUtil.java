package com.test.hotelsearch.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.hotelsearch.dto.FailureHotelNameInsertion;
import com.test.hotelsearch.dto.HotelDTO;
import com.test.hotelsearch.entity.Hotel;
import com.test.hotelsearch.exception.HotelIdInvalidException;
import com.test.hotelsearch.exception.HotelNameInvalidException;
import com.test.hotelsearch.exception.HotelNameNotFoundException;

@Component
public class ValidationUtil {


	public void validateHotelName(String name) throws HotelNameInvalidException
	{
		if(!checkHotelName(name))
		{
			throw new HotelNameInvalidException("Invalid/Empty Hotel Name");
		}
		
	}
	
	public boolean checkHotelName(String name) 
	{
		
		if(name == null || name.isEmpty())
		{
			return false;
		}
		return true;
	}

	public void validateHotelUpdate(HotelDTO hotelDTO) throws HotelNameInvalidException , HotelIdInvalidException{
		
		if(!checkHotelName(hotelDTO.getName()))
		{
			throw new HotelNameInvalidException("Hotel Name Invalid");
		}
		if(!validateHotelId(hotelDTO.getId()))
		{
			throw new HotelIdInvalidException("Hotel Id Invalid");
		}
		
	}

	private boolean validateHotelId(Long id) {

		if(id == null)
		{
			return false;
		}
		return true;
		
	}

	
	
}
