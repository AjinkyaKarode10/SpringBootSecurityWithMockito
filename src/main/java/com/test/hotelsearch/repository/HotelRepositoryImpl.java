package com.test.hotelsearch.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.test.hotelsearch.entity.Hotel;

@Repository
public class HotelRepositoryImpl implements HotelRepositoryI{


	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<Hotel> searchHotel(String hotelName, String location,int pageNumber, int pageSize) {
		CriteriaBuilder criteriaBuilder    = entityManager.getCriteriaBuilder();
		CriteriaQuery<Hotel> criteriaQuery =  criteriaBuilder.createQuery(Hotel.class);
		Root<Hotel> root = criteriaQuery.from(Hotel.class);
		criteriaQuery.select(root);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(hotelName != null && !hotelName.isEmpty())
		{
			predicates.add(criteriaBuilder.like(root.<String>get("name"), "%"+hotelName+"%"));
			criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		}
		if(location != null && !location.isEmpty())
		{
			predicates.add(criteriaBuilder.like(root.<String>get("location"), "%"+location+"%"));
			criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		}
		
		return entityManager.createQuery(criteriaQuery).setFirstResult(calculateOffset(pageNumber, pageSize)).setMaxResults(pageSize)
                .getResultList();
	}
	
	protected int calculateOffset(int page, int limit) {
        return ((limit * page) - limit);
    }

}
