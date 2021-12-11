package com.codingdojo.relationships.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.relationships.models.License;
import com.codingdojo.relationships.models.Person;
import com.codingdojo.relationships.services.LicenseService;
import com.codingdojo.relationships.services.PersonService;

@Controller
public class HomeController {
	
	@Autowired
	PersonService personService;
	@Autowired
	LicenseService licenseService;
	
    @RequestMapping("/persons")
    public String index(Model model, @ModelAttribute("person") Person person) {
        List<Person> persons = personService.allPersons();
        model.addAttribute("persons", persons);
        return "index.jsp";
    }
	
	@GetMapping("/persons/{person_id}")
	public String showPerson(@PathVariable Long person_id, Model model) {
	    
	    Person someAwesomePerson = personService.findPersonById(person_id);
	    model.addAttribute("person", someAwesomePerson);
	    
	    return "index.jsp";
	}
	
	@PostMapping("/licenses")
	public String licenses(@Valid @ModelAttribute("license") License license) {
	    // error handling with binding result omitted    
	    licenseService.createLicense(license); // Already has the person!
	        
	    return "redirect:/persons";
	}

}
