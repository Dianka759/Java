package com.codingdojo.pokebook.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.pokebook.models.Expense;

@Repository
public interface PokeBookRepository  extends CrudRepository<Expense, Long>{
	List<Expense> findAll();

//	 // this method finds expense with names containing the search string
//	 List<Expense> findByNameContaining(String search);
//	 
//	 // this method counts how many expenses for a specific Vendor
//	 Long countByVendor(String vendor);
//	 
//	 // this method deletes an expense
//	 Long deleteById(long id);
}
