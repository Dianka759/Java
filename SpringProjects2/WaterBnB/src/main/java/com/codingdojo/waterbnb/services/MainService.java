package com.codingdojo.waterbnb.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.waterbnb.models.LoginUser;
import com.codingdojo.waterbnb.models.Pool;
import com.codingdojo.waterbnb.models.Review;
import com.codingdojo.waterbnb.models.User;
import com.codingdojo.waterbnb.repositories.PoolRepository;
import com.codingdojo.waterbnb.repositories.ReviewRepository;
import com.codingdojo.waterbnb.repositories.UserRepository;

@Service
public class MainService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PoolRepository poolRepo;
	@Autowired
	private ReviewRepository reviewRepo;
	
	
	///////////////////////// USER /////////////////////////
  
	 public User oneUser(Long id) {
	    	Optional<User> optionalUser = userRepo.findById(id);
	    	if (optionalUser.isPresent()) {
	    		return optionalUser.get();
	    	} else {
	    		return null;
	    	}
	    }
	    
	    //// This is the registration method
    public User register(User newUser, BindingResult result) {
        // Checks if the email from a new user is already present in the database
    	if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
            //if that email is in the databse, unique reject value
    		result.rejectValue("email", "Unique", "This email is already in use!");
        }
    	//if the password is not the same as the confirm, reject value
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }
        //if there are errors in bindingresult, leave null - you get nothing.
        if(result.hasErrors()) {
            return null;
        } else {
        	//creates a hashed passsword if all checks pass, and the user is saved in database.
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepo.save(newUser);
        }
    }
    
    // This is for logging in
    public User login(LoginUser newLogin, BindingResult result) {
        if(result.hasErrors()) {
            return null;
        }
        //this will get the email that the logging in user submtted
        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        //if the email doesnt exist in the db, leaves error message.
        if(!potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "Unknown email!");
            return null;
        }
        //get that user based on their email
        User user = potentialUser.get();
        //if password doesn't match, error.
        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }
        //any other errors, you got nothing.
        if(result.hasErrors()) {
            return null;
        } else {
        	//leave the user
            return user;
        }
    }
    
    ///////////////////////////////////POOL/////////////////////////////
    
    public List<Pool> allPools() {
		return poolRepo.findAll();
	}
    
    // creates a pool
    public Pool createPool(Pool pool) {
        return poolRepo.save(pool);
    }
         
    // retrieves a pool by id
    public Pool findPoolById(Long id) {
        Optional<Pool> optionalpool = poolRepo.findById(id);
        if(optionalpool.isPresent()) {
            return optionalpool.get();
        } else {
            return null;
        }
    }
	
    //updates a pool
    public Pool updatePool(Pool pool) {
    	return poolRepo.save(pool);
    }
    
    
    //deletes a pool
    public void deletePool(long id) {
    	poolRepo.deleteById(id);
    }
    
	public List<Pool> findByAddressContaining(String search){
		return poolRepo.findByAddressContaining(search);
	}

    
   ///////////////////////////////REVIEWS //////////////////////////////
    
    
    
    // creates a review
    public Review createReview(Review review) {
        return reviewRepo.save(review);
    }
    
//	public void createReview(Long userId, Long bookId) {
//		Pool pool = findPoolById(bookId);
//		User user = oneUser(userId);
//		pool.setUser(user);
//		poolRepo.save(pool);
//	}
      
    // retrieves a review by id
    public Review findReviewById(Long id) {
        Optional<Review> optionalreview = reviewRepo.findById(id);
        if(optionalreview.isPresent()) {
            return optionalreview.get();
        } else {
            return null;
        }
    }
	
    //updates a review
    public Review updateReview(Review review) {
    	return reviewRepo.save(review);
    }
    
    
    //deletes a review
    public void deleteReview(long id) {
    	poolRepo.deleteById(id);
    }
    
    public List<Review> reviewByPool(Long id){
    	return reviewRepo.findAllBypool_id(id);
    }
    
	
}
