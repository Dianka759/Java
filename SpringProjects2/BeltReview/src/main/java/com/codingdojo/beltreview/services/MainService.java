package com.codingdojo.beltreview.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.beltreview.models.Join;
import com.codingdojo.beltreview.models.LoginUser;
import com.codingdojo.beltreview.models.Mischief;
import com.codingdojo.beltreview.models.User;
import com.codingdojo.beltreview.repositories.JoinRepository;
import com.codingdojo.beltreview.repositories.MischiefRepository;
import com.codingdojo.beltreview.repositories.UserRepository;

@Service
public class MainService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MischiefRepository mischiefRepo;
	
	@Autowired
	private JoinRepository joinRepo;
	
	
	///make a join many to many
	public Join makeJoin(Join join) {
		return joinRepo.save(join);
	}
	
	public boolean findBoth(Long user_id, Long mischief_id) {
		Optional<Join> optionaljoin = joinRepo.findByUser_idAndMis_id(user_id,mischief_id);
		if(optionaljoin.isPresent()) {
			return true;
		} else {
			return false;
		} 
	}
	
	public void deleteJoin(Long id) {
		joinRepo.deleteById(id);
	}
	
	public User oneUser(Long id) {
		Optional<User> optionaluser = userRepo.findById(id);
		if(optionaluser.isPresent()) {
			return optionaluser.get();
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
        //if there are errors in bindingresult, return null - you get nothing.
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
        //if the email doesnt exist in the db, returns error message.
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
        	//return the user
            return user;
        }
    }
    
    
    public User createUser(User user_id) {
    	return userRepo.save(user_id);
    }
    
    public List<User> allUsers() {
    	return userRepo.findAll();
    }
    
    public List<Join> allJoins(){
    	return (List<Join>) joinRepo.findAll();
    }
    
    public Mischief create(Mischief mis) {
    	return mischiefRepo.save(mis);
    }
	
	public Mischief oneMischief(Long id) {
		Optional<Mischief> optionalmisf = mischiefRepo.findById(id);
		if(optionalmisf.isPresent()) {
			return optionalmisf.get();
		} else {
			return null;
		}
	}
	
	public List<Mischief> alllMischiefs() {
		return mischiefRepo.findAll();
	}
	
	
    public Mischief updateMischief(Mischief misf) {
    	return mischiefRepo.save(misf);
    }
    
    public void deleteMischief(long id) {
    mischiefRepo.deleteById(id);
    }
}
