package com.codingdojo.pokebook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.pokebook.models.Expense;
import com.codingdojo.pokebook.repositories.PokeBookRepository;

@Service
public class PokeBookService {

	// adding the book repository as a dependency
    private final PokeBookRepository pokebookRepository;
    
    public PokeBookService(PokeBookRepository pokebookRepository) {
        this.pokebookRepository = pokebookRepository;
    }
    
    // returns all the expenses
    public List<Expense> allExpenses() {
        return pokebookRepository.findAll();
    }
    
    // creates an expense
    public Expense createExpense(Expense e) {
        return pokebookRepository.save(e);
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
    
 
    public Expense updateExpense(long id, String expensee, String vendor,  double amount, String description) {
    	Optional<Expense> optionalExpense = pokebookRepository.findById(id);
    	if(optionalExpense.isPresent()) {
    		Expense expense = optionalExpense.get();
    		expense.setExpense(expensee);
    		expense.setVendor(vendor);
    		expense.setAmount(amount);
    		expense.setDescription(description);
    		pokebookRepository.save(expense);
    		return expense;
    	}
    	else {
    		return null;
    	}
    }
    
    public void deleteExpense(long id) {
    	pokebookRepository.deleteById(id);
    }
    
}
