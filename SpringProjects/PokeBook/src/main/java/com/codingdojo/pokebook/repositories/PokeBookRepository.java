package com.codingdojo.pokebook.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.pokebook.models.Expense;

@Repository
public interface PokeBookRepository  extends CrudRepository<Expense, Long>{
	List<Expense> findAll();

}
