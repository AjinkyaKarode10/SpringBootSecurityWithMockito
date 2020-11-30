package com.test.hotelsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.hotelsearch.entity.Hotel;

@Repository
public interface HotelJpaRepository extends JpaRepository<Hotel, Long>{

	
	Hotel findByName(String name);
}
