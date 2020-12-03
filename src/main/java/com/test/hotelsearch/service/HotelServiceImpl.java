package com.test.hotelsearch.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.test.hotelsearch.dto.FailureHotelNameInsertion;
import com.test.hotelsearch.dto.HotelBulkAddResponse;
import com.test.hotelsearch.dto.HotelBulkUpdateResponse;
import com.test.hotelsearch.dto.HotelDTO;
import com.test.hotelsearch.dto.HotelSearchResponse;
import com.test.hotelsearch.entity.Hotel;
import com.test.hotelsearch.exception.HotelIdInvalidException;
import com.test.hotelsearch.exception.HotelNameNotFoundException;
import com.test.hotelsearch.repository.HotelJpaRepository;
import com.test.hotelsearch.repository.HotelRepositoryI;
import com.test.hotelsearch.utility.ValidationUtil;

@Service
public class HotelServiceImpl implements HotelServiceI {

	@Autowired
	HotelJpaRepository hotelJpaRepo;

	@Autowired
	ValidationUtil validationUtil;

	@Autowired
	HotelRepositoryI hotelRepo;

	@Value("${pageNumber}")
	int defaultPageNumber;

	@Value("${pageSize}")
	int defaultPageSize;

	
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelServiceImpl.class);
	
	@Override
	public HotelSearchResponse getHotels(String location, String hotelName, Integer pageNumber, Integer pageSize) {

		ModelMapper modelMapper = new ModelMapper();
		if (pageNumber == null) {
			pageNumber = defaultPageNumber;
		}
		if (pageSize == null) {
			pageSize = defaultPageSize;
		}
		List<Hotel> hotelList = hotelRepo.searchHotel(hotelName, location, pageNumber, pageSize);
		HotelSearchResponse response = new HotelSearchResponse();
		response.setHotelList(Arrays.asList(modelMapper.map(hotelList, HotelDTO[].class)));
		response.setTotalItems(hotelList.size());

		return response;
	}

	@Override
	public HotelDTO addHotel(HotelDTO hotelDTO) {

		return hotelJpaRepo.save(hotelDTO.convertToEntity()).convertToDTO();
	}

	@Override
	public HotelBulkAddResponse addListOfHotel(List<HotelDTO> hotelDTOList) {

		ModelMapper modelMapper = new ModelMapper();

		List<Hotel> hotelEntityList = new ArrayList<>();
		List<FailureHotelNameInsertion> failureList = new ArrayList<FailureHotelNameInsertion>();
		for (HotelDTO hotelDTO : hotelDTOList) {
			if (validationUtil.checkHotelName(hotelDTO.getName())) {
				hotelEntityList.add(hotelDTO.convertToEntity());
			} else {
				FailureHotelNameInsertion failure = new FailureHotelNameInsertion();
				failure.setHotelname(hotelDTO.getName());
				failure.setReason("Hotel Name Empty/Invalid");
				failureList.add(failure);
			}

		}

		hotelEntityList = hotelJpaRepo.saveAll(hotelEntityList);
		HotelBulkAddResponse response = new HotelBulkAddResponse();
		response.setSuccessfullInserts(Arrays.asList(modelMapper.map(hotelEntityList, HotelDTO[].class)));
		response.setTotalItemsAdded(hotelEntityList.size());
		response.setFailure(failureList);

		return response;
	}

	@Override
	public HotelDTO updateHotel(HotelDTO hotelDTO) throws HotelNameNotFoundException {
		Optional<Hotel> optioanlEntity = hotelJpaRepo.findById(hotelDTO.getId());
		if (!optioanlEntity.isPresent()) {
			throw new HotelNameNotFoundException("Hotel Id Not Found . Cannot Update");
		}

		Hotel hotelEntity = optioanlEntity.get();

		hotelEntity.setName(hotelDTO.getName());
		hotelEntity.setNearByPlaces(hotelDTO.getNearByPlaces());
		hotelEntity.setAvailableRooms(hotelDTO.getAvailableRooms());
		hotelEntity.setCost(hotelDTO.getCost());

		return hotelJpaRepo.save(hotelEntity).convertToDTO();
		
	}

	@Override
	public HotelBulkUpdateResponse updateBulkHotel(List<HotelDTO> hotelDTOList) {
		ModelMapper modelMapper = new ModelMapper();

		List<Hotel> hotelEntityList = new ArrayList<>();
		List<FailureHotelNameInsertion> failureList = new ArrayList<FailureHotelNameInsertion>();
		
		for (HotelDTO hotelDTO : hotelDTOList) {
			if (validationUtil.checkHotelName(hotelDTO.getName())) {
				hotelEntityList.add(hotelDTO.convertToEntity());
			} else {
				FailureHotelNameInsertion failure = new FailureHotelNameInsertion();
				failure.setHotelname(hotelDTO.getName());
				failure.setReason("Hotel Name Empty/Invalid");
				failureList.add(failure);
			}

		}
		
		Iterator<Hotel> iterator = hotelEntityList.iterator();
		while(iterator.hasNext())
		{
			Hotel hoteEntity = iterator.next();
			Optional<Hotel> optioanlEntity = hotelJpaRepo.findById(hoteEntity.getId());
			if (!optioanlEntity.isPresent()) {
				
				FailureHotelNameInsertion failure = new FailureHotelNameInsertion();
				failure.setHotelname(hoteEntity.getName());
				failure.setReason("Hotel Not Found");
				failureList.add(failure);

				iterator.remove();
			}
//			} else {
//				FailureHotelNameInsertion failure = new FailureHotelNameInsertion();
//				failure.setHotelname(hotelDTO.getName());
//				failure.setReason("Hotel Not Found");
//				failureList.add(failure);
//			}
		}
		
		
		

		hotelEntityList = hotelJpaRepo.saveAll(hotelEntityList);
		HotelBulkUpdateResponse response = new HotelBulkUpdateResponse();
		response.setSuccessfullUpdates(Arrays.asList(modelMapper.map(hotelEntityList, HotelDTO[].class)));
		response.setTotalItemsUpdated(hotelEntityList.size());
		response.setFailure(failureList);

		return response;
	}

	@Override
	public boolean deleteHotel(HotelDTO hotelDTO) throws HotelIdInvalidException{
		if(!validationUtil.validateHotelId(hotelDTO.getId()))
		{
			throw new HotelIdInvalidException("Hotel Id Invalid/Empty");
		}
		hotelJpaRepo.deleteById(hotelDTO.getId());
		
		return true;
	}

}


//for(Hotel hotel : hotelEntityList)
//{
//	Optional<Hotel> optioanlEntity = hotelJpaRepo.findById(hotel.getId());
//	if (!optioanlEntity.isPresent()) {
////		Hotel hotelEntity = optioanlEntity.get();
////		hotelEntity.setName(hotelDTO.getName());
////		hotelEntity.setNearByPlaces(hotelDTO.getNearByPlaces());
////		hotelEntity.setAvailableRooms(hotelDTO.getAvailableRooms());
////		hotelEntity.setCost(hotelDTO.getCost());
////		hotelEntityList.add(hotelEntity);
//		ho
//		FailureHotelNameInsertion failure = new FailureHotelNameInsertion();
//		failure.setHotelname(hotel.getName());
//		failure.setReason("Hotel Not Found");
//		failureList.add(failure);
//	}
////	} else {
////		FailureHotelNameInsertion failure = new FailureHotelNameInsertion();
////		failure.setHotelname(hotelDTO.getName());
////		failure.setReason("Hotel Not Found");
////		failureList.add(failure);
////	}
//}
