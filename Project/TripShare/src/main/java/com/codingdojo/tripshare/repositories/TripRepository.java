package com.codingdojo.tripshare.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.tripshare.models.Trip;
import com.codingdojo.tripshare.models.User;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long>{
	List<Trip> findAll();
	
//	List<User> findByUserTripsNotContains(Trip trip);
	
	

}
