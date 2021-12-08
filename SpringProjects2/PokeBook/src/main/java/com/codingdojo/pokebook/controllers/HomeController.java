package com.codingdojo.pokebook.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.pokebook.models.Expense;
import com.codingdojo.pokebook.services.PokeBookService;

@Controller
public class HomeController {
//	private final PokeBookService pokebookService;
//
//	public HomeController(PokeBookService pokebookService) {
//        this.pokebookService = pokebookService;
//    }
	
	@Autowired
	PokeBookService pokebookService;
	
    @RequestMapping("/expenses")
    public String index(Model model, @ModelAttribute("expense") Expense expense) {
        List<Expense> expenses = pokebookService.allExpenses();
        model.addAttribute("expenses", expenses);
        return "index.jsp";
    }
    
    
    @RequestMapping(value="/makeExpense", method=RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("expense") Expense expense, 
    									BindingResult result){
        if (result.hasErrors()) {
        	List<Expense> expenses = pokebookService.allExpenses();
            model.addAttribute("expenses", expenses);
            return "index.jsp";
        } else {
            pokebookService.createExpense(expense);
            return "redirect:/expenses";
        }
    }
    
    
	@RequestMapping("/edit/{id}")
	public String editExpense(@PathVariable("id") long id,
			Model model) {
		Expense expense = pokebookService.findExpense(id);
		model.addAttribute("expense", expense);
		return "edit.jsp";
	}
	
//	@RequestMapping("/update_expense")
//	public String updateExpense(@RequestParam("id") long id, 
//			@RequestParam("name") String name, 
//			@RequestParam("vendor") String vendor,
//			@RequestParam("amount") double amount,
//			@RequestParam("description") String description,
//			@Valid @ModelAttribute("expense") Expense expense, BindingResult result) {
//		if(result.hasErrors()) {
//			return "edit.jsp";
//		}
//		else {
//			pokebookService.updateExpense(id, name, vendor, amount, description);
//			return "redirect:/expenses";
//		}
//	}
	
	//Another way to edit, less code, yay
	@RequestMapping(value="/update_expense/{id}", method=RequestMethod.PUT)
	public String updateExpense(@PathVariable("id") long id, 
			@Valid @ModelAttribute("expense") Expense expense, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}
		else {
			pokebookService.updateExpense(expense);
			return "redirect:/expenses";
		}
	}
	
	
	@RequestMapping(value="delete/{id}")
	public String destroy(@PathVariable("id") Long id) {
		pokebookService.deleteExpense(id);
		return "redirect:/expenses";
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
        Expense expense = pokebookService.findExpense(id);	        
        model.addAttribute("expense", expense);
        return "show.jsp";
    }
	    
    
}
