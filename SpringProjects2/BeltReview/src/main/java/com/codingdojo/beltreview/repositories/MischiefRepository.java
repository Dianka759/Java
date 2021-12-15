package com.codingdojo.beltreview.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.beltreview.models.Mischief;
import com.codingdojo.beltreview.models.User;

@Repository
public interface MischiefRepository extends CrudRepository<Mischief, Long> {
	List<Mischief> findAll();
}
