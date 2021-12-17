package com.codingdojo.projectmanagment.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.projectmanagment.models.Join;

@Repository
public interface JoinRepository extends CrudRepository<Join, Long>{
	Optional<Join> findByUser_idAndProject_id(Long user_id, Long project_id);

}
