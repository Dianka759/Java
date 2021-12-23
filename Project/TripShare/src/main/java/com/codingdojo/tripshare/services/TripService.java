package com.codingdojo.tripshare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.tripshare.models.Trip;
import com.codingdojo.tripshare.models.User;
import com.codingdojo.tripshare.repositories.TripRepository;

@Service
public class TripService {
	@Autowired
	private TripRepository tripRepo;
	
	// returns all the trips
    public List<Trip> allTripes() {
        return tripRepo.findAll();
    }
    
    // creates a trip
    public Trip createTrip(Trip c) {
        return tripRepo.save(c);
    }
      
    // retrieves a trip by id
    public Trip findTripById(Long id) {
        Optional<Trip> optionaltrip = tripRepo.findById(id);
        if(optionaltrip.isPresent()) {
            return optionaltrip.get();
        } else {
            return null;
        }
    }
	
    //updates a trip
    public Trip updateTrip(Trip c) {
    	return tripRepo.save(c);
    }
    
    //deletes a trip
    public void deleteTrip(long id) {
    	tripRepo.deleteById(id);
    }
    
}
