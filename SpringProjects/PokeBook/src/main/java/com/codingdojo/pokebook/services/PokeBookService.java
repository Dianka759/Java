package com.codingdojo.pokebook.services;

import java.util.List;

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
}
