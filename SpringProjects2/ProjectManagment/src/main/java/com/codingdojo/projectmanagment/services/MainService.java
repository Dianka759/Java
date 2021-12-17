package com.codingdojo.projectmanagment.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.projectmanagment.models.Join;
import com.codingdojo.projectmanagment.models.LoginUser;
import com.codingdojo.projectmanagment.models.Project;
import com.codingdojo.projectmanagment.models.User;
import com.codingdojo.projectmanagment.repositories.JoinRepository;
import com.codingdojo.projectmanagment.repositories.ProjectRepository;
import com.codingdojo.projectmanagment.repositories.UserRepository;


@Service
public class MainService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProjectRepository projectRepo;
	@Autowired
	private JoinRepository joinRepo;
	

    public List<Join> allJoins(){
    	return (List<Join>) joinRepo.findAll();
    }
	
    public User createUser(User user_id) {
    	return userRepo.save(user_id);
    }
    
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
	    
	 // leaves all the projects
	    public List<Project> allProjects() {
	       return projectRepo.findAll();
	    }
	    
	    // creates a project
	    public Project createProject(Project project) {
	        return projectRepo.save(project);
	    }
	      
	    // retrieves a project by id
	    public Project findProjectById(Long id) {
	        Optional<Project> optionalproject = projectRepo.findById(id);
	        if(optionalproject.isPresent()) {
	            return optionalproject.get();
	        } else {
	            return null;
	        }
	    }
		
	    //updates a project
	    public Project updateProject(Project project) {
	    	return projectRepo.save(project);
	    }
	    
	    
	    //deletes a project
	    public void deleteProject(long id) {
	    	projectRepo.deleteById(id);
	    }
	    

		//finds both user and project id, to avoid duplicates in the joined table
		public boolean findBoth(Long user_id, Long project_id) {
			Optional<Join> optionaljoin = joinRepo.findByUser_idAndProject_id(user_id,project_id);
			if(optionaljoin.isPresent()) {
				return true;
			} else {
				return false;
			} 
		}
		
		//creates a join
		public boolean joinProject(Long user_id, Long project_id) {
			Optional<Join> optionaljoin = joinRepo.findByUser_idAndProject_id(user_id,project_id);
			if(optionaljoin.isPresent()) {
				return true;
			} else {
				return false;
			} 
		}
		
		//deletes a join
		public void deleteJoin(Long id) {
			joinRepo.deleteById(id);
		}
		
}
