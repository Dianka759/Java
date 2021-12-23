package com.codingdojo.tripshare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.tripshare.models.Signup;
import com.codingdojo.tripshare.repositories.SignupRepository;

@Service
public class SignupService {
	@Autowired
	private SignupRepository signupRepo;
	
	// returns all the signups
    public List<Signup> allSignupes() {
        return signupRepo.findAll();
    }
 
    // creates a signup
    public Signup createSignup(Signup c) {
        return signupRepo.save(c);
    }
      
    // retrieves a signup by id
    public Signup findSignupById(Long id) {
        Optional<Signup> optionalsignup = signupRepo.findById(id);
        if(optionalsignup.isPresent()) {
            return optionalsignup.get();
        } else {
            return null;
        }
    }
	
    //updates a signup
    public Signup updateSignup(Signup c) {
    	return signupRepo.save(c);
    }
    
    //deletes a signup
    public void deleteSignup(long id) {
    	signupRepo.deleteById(id);
    }



    
    
    
}
