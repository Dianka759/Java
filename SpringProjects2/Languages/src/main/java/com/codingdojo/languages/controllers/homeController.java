package com.codingdojo.languages.controllers;

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

import com.codingdojo.languages.models.Language;
import com.codingdojo.languages.services.languageService;


@Controller
public class homeController {
	
	@Autowired
	languageService langService;
	
   @RequestMapping("/languages")
    public String index(Model model, @ModelAttribute("language") Language language) {
        List<Language> languages = langService.allLanguages();
        model.addAttribute("languages", languages);
        return "index.jsp";
    }
   
   @RequestMapping(value="/makeLanguage", method=RequestMethod.POST)
   public String create(Model model, @Valid @ModelAttribute("language") Language language, 
   									BindingResult result){
       if (result.hasErrors()) {
       	List<Language> languages = langService.allLanguages();
           model.addAttribute("languages", languages);
           return "index.jsp";
       } else {
           langService.createLanguage(language);
           return "redirect:/languages";
       }
   }
   
	@RequestMapping("/edit/{id}")
	public String editLanguage(@PathVariable("id") long id,
			Model model) {
		Language language = langService.findLanguage(id);
		model.addAttribute("language", language);
		return "edit.jsp";
	}
	
	//Another way to edit, less code, yay
	@RequestMapping(value="/updateLanguage/{id}", method=RequestMethod.PUT)
	public String updateLanguage(@PathVariable("id") long id, 
			@Valid @ModelAttribute("language") Language language, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}
		else {
			langService.updateLanguage(language);
			return "redirect:/languages";
		}
	}
	
	@RequestMapping(value="delete/{id}")
	public String destroy(@PathVariable("id") Long id) {
		langService.deleteLanguage(id);
		return "redirect:/languages";
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
        Language language = langService.findLanguage(id);	        
        model.addAttribute("language", language);
        return "show.jsp";
    }
	   
}
