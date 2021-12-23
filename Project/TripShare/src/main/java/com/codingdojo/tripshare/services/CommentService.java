package com.codingdojo.tripshare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.tripshare.models.Comment;
import com.codingdojo.tripshare.models.Trip;
import com.codingdojo.tripshare.repositories.CommentRepository;
import com.codingdojo.tripshare.repositories.TripRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private TripRepository tripRepo;
	
	// returns all the comments
    public List<Comment> allCommentes() {
        return commentRepo.findAll();
    }
 
    // creates a comment
    public Comment createComment(Comment c) {
        return commentRepo.save(c);
    }
      
    // retrieves a comment by id
    public Comment findCommentById(Long id) {
        Optional<Comment> optionalcomment = commentRepo.findById(id);
        if(optionalcomment.isPresent()) {
            return optionalcomment.get();
        } else {
            return null;
        }
    }
	
    //updates a comment
    public Comment updateComment(Comment c) {
    	return commentRepo.save(c);
    }
    
    //deletes a comment
    public void deleteComment(long id) {
    	commentRepo.deleteById(id);
    }
    
    // retrieves a comment by trip id
    public Trip findCommentByTripId(Long id) {
        Optional<Trip> optionalcomment = tripRepo.findById(id);
        if(optionalcomment.isPresent()) {
            return optionalcomment.get();
        } else {
            return null;
        }
    }
}
