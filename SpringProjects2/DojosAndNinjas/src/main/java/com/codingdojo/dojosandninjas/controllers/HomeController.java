package com.codingdojo.dojosandninjas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.dojosandninjas.models.Dojo;
import com.codingdojo.dojosandninjas.models.Ninja;
import com.codingdojo.dojosandninjas.services.DojosService;
import com.codingdojo.dojosandninjas.services.NinjasService;

@Controller
public class HomeController {
	
	@Autowired
	DojosService dojosService;
	@Autowired
	NinjasService ninjasService;
	
	///////Root///////
	@GetMapping("/")
	private String index(Model model) {
		return "redirect:/dashboard";
	}
	
	/////////////////////////DASHBOARD //////////////////////////
    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
//        model.addAttribute("ninjas", ninjasService.findNinjaById(id));
        model.addAttribute("ninjas", ninjasService.allNinjas());
        model.addAttribute("dojos", dojosService.allDojos());
        return "dashboard.jsp";
    }
	
	///////////////////////Creatin a new dojo ///////////////////
    @RequestMapping("/dojos/new")
    public String newdojo(Model model, @ModelAttribute("dojo") Dojo dojo) {
        List<Dojo> dojos = dojosService.allDojos();
        model.addAttribute("dojos", dojos);
        return "newDojo.jsp";
    }
    
	@PostMapping("/newdojo")
	public String dojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
		if (result.hasErrors()) { 
	    return "newDojo.jsp";
		 } else {   
		dojosService.createDojo(dojo);
	    return "redirect:/ninjas/new";
		 }
	}
	
	//////////////////////////Creating a new ninja ///////////////////////
    @RequestMapping("/ninjas/new")
    public String newninja(Model model, @ModelAttribute("ninja") Ninja ninja) {
        model.addAttribute("dojos", dojosService.allDojos());
        return "newNinja.jsp";
    }
    
	@PostMapping("/newninja")
	public String ninjas(Model model,
						 @Valid @ModelAttribute("ninja") Ninja ninja, 
						 BindingResult result) {
		if (result.hasErrors()) {
		model.addAttribute("dojos", dojosService.allDojos());
	    return "newNinja.jsp";
		 } else {   
		ninjasService.createNinja(ninja);
		Dojo dojo = ninja.getDojo();
		Long id = dojo.getId();
	    return "redirect:/dojo/" +id;
		 }
	}
	
	///////////////////////////Showing one dojo with ninjas/////////////////
	@GetMapping("/dojo/{id}")
	public String showDojos(@PathVariable Long id, Model model) { 
		Dojo dojo = dojosService.findDojoById(id);
		model.addAttribute("dojo", dojo); 
		return "showDojo.jsp";
	}
	
	///////////////////////////DELETE///////////////////////////////////////
	@RequestMapping(value="delete/{id}")
	public String destroy(@PathVariable("id") Long id, 
						  RedirectAttributes redirectAttributes) {
		ninjasService.deleteNinja(id);
		redirectAttributes.addFlashAttribute("message", "Your Ninja has been deleted");	
		return "redirect:/dashboard";
	}
}
