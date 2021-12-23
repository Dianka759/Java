package com.codingdojo.tripshare.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.tripshare.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
	
	List<Comment> findAll();
	List<Comment> findByTrip_id(Long id);
	
	

}
