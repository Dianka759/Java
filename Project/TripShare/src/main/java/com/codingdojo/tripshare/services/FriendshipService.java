package com.codingdojo.tripshare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.tripshare.models.Friendship;
import com.codingdojo.tripshare.models.User;
import com.codingdojo.tripshare.repositories.FriendshipRepository;
import com.codingdojo.tripshare.repositories.UserRepository;

@Service
public class FriendshipService {
	
	@Autowired
	private FriendshipRepository friendshipRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	// returns all the friendships
    public List<Friendship> allFriendshipes() {
        return friendshipRepo.findAll();
    }
 
    // creates a friendship
    public Friendship createFriendship(Friendship c) {
        return friendshipRepo.save(c);
    }
      
    // retrieves a friendship by id
    public Friendship findFriendshipById(Long id) {
        Optional<Friendship> optionalfriendship = friendshipRepo.findById(id);
        if(optionalfriendship.isPresent()) {
            return optionalfriendship.get();
        } else {
            return null;
        }
    } 
    
    //////////////////////////////////////////////////////////////////////
    // retrieves a friendship by id
    public User findFriendshipByUserId(Long id) {
        Optional<User> optionalfriendship = userRepo.findById(id);
        if(optionalfriendship.isPresent()) {
            return optionalfriendship.get();
        } else {
            return null;
        }
    }  
    
	//////////////////////////////////////////////////////////////////////////////
    //updates a friendship
    public Friendship updateFriendship(Friendship c) {
    	return friendshipRepo.save(c);
    }
    
    //deletes a friendship
    public void deleteFriendship(long id) {
    	friendshipRepo.deleteById(id);
    }
    
    public void deleteByUserAndFriend(Long user_id, Long friend_id) {
    	friendshipRepo.deleteByUserAndFriend(user_id, friend_id);
    }
}
