package com.codingdojo.projectmanagment.controllers;


import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.projectmanagment.models.LoginUser;
import com.codingdojo.projectmanagment.models.Project;
import com.codingdojo.projectmanagment.models.User;
import com.codingdojo.projectmanagment.services.MainService;

@Controller
public class HomeController {
	
	@Autowired
	private MainService mainService;
	
	//renders the forms for login and reg
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    //this route is the action for submitting a new user, reg
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        // this calls on the register method in service, and sends new user info, and the results from the binding results.
    	mainService.register(newUser, result);
    	//if there are any errors, stay on index/login/reg page
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        //otherwise set the user ID in session
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/dashboard";
    }
    
    //this route is the action for submitting an existing user, login
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = mainService.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/dashboard";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user_id");
    	return "redirect:/";
    }
    
	@GetMapping("/dashboard")
	public String projectMarket(Model model, HttpSession session) {
		Long user_id = (Long)session.getAttribute("user_id");
		if(user_id == null) {
			return "redirect:/dashboard";
		} else {
			model.addAttribute("allJoins", mainService.allJoins());
			model.addAttribute("user", mainService.oneUser(user_id));
			model.addAttribute("projects", mainService.allProjects());
			 return "dashboard.jsp";
		}
	}
	
    //Create new project
    @GetMapping("/project/new")
    public String newproject(@ModelAttribute("project") Project project, HttpSession session, 
    					  Model model, RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You need to be loggin in!");
    		return "redirect:/";
    	}
    	Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
    	return "newProject.jsp";
    }
    
	@PostMapping("/newproject")
	public String project(@Valid @ModelAttribute("project") Project project, BindingResult result,
							@RequestParam("user") Long user){
		if (result.hasErrors()) { 
	    return "newProject.jsp";
		 } else {  
		User userObject= mainService.oneUser(user);
		mainService.createProject(project);
		userObject.getJoinedProjects().add(project);
		mainService.createUser(userObject);
	    return "redirect:/dashboard";
		 }
	}
	
	@GetMapping("/showProject/{id}")
	public String oneProject(@PathVariable Long id, Model model, HttpSession session,
			  RedirectAttributes redirectAttributes) {
		if (session.getAttribute("user_id") == null) {
		redirectAttributes.addFlashAttribute("error", "Can't view a mischief, but nice try. Now log in.");
		return "redirect:/";
		}
		User user = mainService.oneUser((Long) session.getAttribute("user_id"));
		model.addAttribute("user", user);
		model.addAttribute("project", mainService.findProjectById(id));
		return "showProject.jsp";
	}
	
	
	@RequestMapping("/edit/{id}")
	public String editProject(@PathVariable("id") long id,
			Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You can't edit a random mischief without logging in, sorry. not.");
    		return "redirect:/";
    	}
		Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
		Project project = mainService.findProjectById(id);
		model.addAttribute("project", project);
		return "editProject.jsp";
	}
	
	
	@PostMapping(value="/updateProject/{id}")
	public String updateMProject(@PathVariable("id") long id, 
			@Valid @ModelAttribute("project") Project project, BindingResult result) {
		if(result.hasErrors()) {
			return "editProject.jsp";
		}
		else {
			mainService.updateProject(project);
			return "redirect:/project/" +id;
		}
	}
	
	
	
	@PostMapping("/createJoin")
	public String createJoin(@RequestParam(name="user") Long user_id,
							@RequestParam(name="project") Long project_id) {
		
		User user = mainService.oneUser(user_id);
		Project project = mainService.findProjectById(project_id);
		
		if (!mainService.findBoth(user_id, project_id)){
			user.getJoinedProjects().add(project);
			mainService.createUser(user);
			return "redirect:/dashboard";			
		} else {
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/deleteJoin/{id}")
	public String deleteJoin(@PathVariable("id") Long id) {
		mainService.deleteJoin(id);
		return "redirect:/dashboard";
	}
}
		

	
	
