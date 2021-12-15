package com.codingdojo.beltreview.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.beltreview.models.Join;

@Repository
public interface JoinRepository extends CrudRepository<Join, Long> {
	Optional<Join> findById(int id);
	Optional<Join> findByUser_idAndMis_id(Long user_id, Long mischief_id);
	
}
