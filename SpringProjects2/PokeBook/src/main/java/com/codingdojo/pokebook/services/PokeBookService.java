package com.codingdojo.pokebook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.pokebook.models.Expense;
import com.codingdojo.pokebook.repositories.PokeBookRepository;

@Service
public class PokeBookService {

//	// adding the book repository as a dependency
//    private final PokeBookRepository pokebookRepository;
//    
//    public PokeBookService(PokeBookRepository pokebookRepository) {
//        this.pokebookRepository = pokebookRepository;
//    }
	
	@Autowired
	PokeBookRepository pokebookRepository;
    
    // returns all the expenses
    public List<Expense> allExpenses() {
        return pokebookRepository.findAll();
    }
    
    
    // creates an expense
    public Expense createExpense(Expense expense) {
        return pokebookRepository.save(expense);
    }
    
    
    // retrieves an Expense by Id
    public Expense findExpense(Long id) {
        Optional<Expense> optionalExpense = pokebookRepository.findById(id);
        if(optionalExpense.isPresent()) {
            return optionalExpense.get();
        } else {
            return null;
        }
    }
    
 
//    public Expense updateExpense(long id, String name, String vendor,  double amount, String description) {
//    	Optional<Expense> optionalExpense = pokebookRepository.findById(id);
//    	if(optionalExpense.isPresent()) {
//    		Expense expense = optionalExpense.get();
//    		expense.setName(name);
//    		expense.setVendor(vendor);
//    		expense.setAmount(amount);
//    		expense.setDescription(description);
//    		pokebookRepository.save(expense);
//    		return expense;
//    	}
//    	else {
//    		return null;
//    	}
//    }
    
    public Expense updateExpense(Expense expense) {
    	return pokebookRepository.save(expense);
    }
    
    public void deleteExpense(long id) {
    	pokebookRepository.deleteById(id);
    }
    
}
