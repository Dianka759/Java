package com.codingdojo.waterbnb.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.waterbnb.models.Review;


@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
	List<Review> findAll();
	List<Review> findAllBypool_id(Long id);
}
