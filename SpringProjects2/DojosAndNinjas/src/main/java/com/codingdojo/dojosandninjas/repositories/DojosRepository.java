package com.codingdojo.dojosandninjas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.dojosandninjas.models.Dojo;

@Repository
public interface DojosRepository extends CrudRepository<Dojo, Long> {
	List<Dojo> findAll();

}
