package com.codingdojo.tripshare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.tripshare.models.Rating;
import com.codingdojo.tripshare.repositories.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepo;
	
	// returns all the ratings
    public List<Rating> allRatinges() {
        return ratingRepo.findAll();
    }
 
    // creates a rating
    public Rating createRating(Rating c) {
        return ratingRepo.save(c);
    }
      
    // retrieves a rating by id
    public Rating findRatingById(Long id) {
        Optional<Rating> optionalrating = ratingRepo.findById(id);
        if(optionalrating.isPresent()) {
            return optionalrating.get();
        } else {
            return null;
        }
    }
	
    //updates a rating
    public Rating updateRating(Rating c) {
    	return ratingRepo.save(c);
    }
    
    //deletes a rating
    public void deleteRating(long id) {
    	ratingRepo.deleteById(id);
    }
}
