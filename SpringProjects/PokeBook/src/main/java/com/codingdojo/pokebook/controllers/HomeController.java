package com.codingdojo.pokebook.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.pokebook.models.Expense;
import com.codingdojo.pokebook.services.PokeBookService;

@Controller
public class HomeController {
	private final PokeBookService pokebookService;

	public HomeController(PokeBookService pokebookService) {
        this.pokebookService = pokebookService;
    }
	
    @RequestMapping("/expenses")
    public String index(Model model, @ModelAttribute("expense") Expense expense) {
        List<Expense> expenses = pokebookService.allExpenses();
        model.addAttribute("expenses", expenses);
        return "index.jsp";
    }
    
//    @RequestMapping(value="/makeExpense", method=RequestMethod.POST)
//    public String create(@RequestParam(value="expense") String expense, 
//    					@RequestParam(value="vendor") String vendor, 
//    					@RequestParam(value="amount") Double amount) {
//        Expense expenses = new Expense(expense, vendor, amount);
//        pokebookService.createExpense(expenses);
//        return "redirect:/expenses";
//    }
//    
    @PostMapping("/expensez")
    public String create(Model model, @Valid @ModelAttribute("expense") Expense expense, 
    									BindingResult result,
    									@RequestParam(value="expense") String expensee, 
    			    					@RequestParam(value="vendor") String vendor, 
    			    					@RequestParam(value="amount") Double amount) {
        if (result.hasErrors()) {
        	List<Expense> expenses = pokebookService.allExpenses();
            model.addAttribute("expenses", expenses);
            return "index.jsp";
        } else {
          Expense expenses = new Expense(expensee, vendor, amount);
            pokebookService.createExpense(expenses);
            return "redirect:/expenses";
        }
    }

    
}
