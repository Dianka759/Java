package com.codingdojo.tripshare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.tripshare.models.Location;
import com.codingdojo.tripshare.repositories.LocationRepository;

@Service
public class LocationService {
	@Autowired
	private LocationRepository locationRepo;
	
	// returns all the locations
    public List<Location> allLocationes() {
        return locationRepo.findAll();
    }
 
    // creates a location
    public Location createLocation(Location c) {
        return locationRepo.save(c);
    }
      
    // retrieves a location by id
    public Location findLocationById(Long id) {
        Optional<Location> optionallocation = locationRepo.findById(id);
        if(optionallocation.isPresent()) {
            return optionallocation.get();
        } else {
            return null;
        }
    }
	
    //updates a location
    public Location updateLocation(Location c) {
    	return locationRepo.save(c);
    }
    
    //deletes a location
    public void deleteLocation(long id) {
    	locationRepo.deleteById(id);
    }
}
