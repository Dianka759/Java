package com.codingdojo.examtwo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.examtwo.models.BabyName;




@Repository
public interface BabyNameRepository extends CrudRepository<BabyName, Long>{
	Optional<BabyName> findById(Long id);
	
	List<BabyName> findAll();
	void deleteById(Long id);
}
