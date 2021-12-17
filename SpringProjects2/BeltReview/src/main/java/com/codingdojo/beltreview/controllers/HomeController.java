package com.codingdojo.beltreview.controllers;

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

import com.codingdojo.beltreview.models.Join;
import com.codingdojo.beltreview.models.LoginUser;
import com.codingdojo.beltreview.models.Mischief;
import com.codingdojo.beltreview.models.User;
import com.codingdojo.beltreview.services.MainService;

@Controller
public class HomeController {

	@Autowired
	private MainService mainServ;
	
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
    	mainServ.register(newUser, result);
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
        User user = mainServ.login(newLogin, result);
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
    
	
	@RequestMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		if(session.getAttribute("user_id") != null){
			Long user_id = (Long) session.getAttribute("user_id");
			model.addAttribute("user", mainServ.oneUser(user_id));
			model.addAttribute("users", mainServ.allUsers());
			model.addAttribute("allMis", mainServ.alllMischiefs());
			model.addAttribute("allJoins", mainServ.allJoins());
			return "dashboard.jsp";
		} else {
			return "redirect:/";
		}
	}
	
	
	@PostMapping("/createJoin")
	public String createJoin(@RequestParam(name="user") Long user_id,
							@RequestParam(name="mischief") Long mischief_id) {
		User user = mainServ.oneUser(user_id);
		Mischief mischief = mainServ.oneMischief(mischief_id);
		
		if (!mainServ.findBoth(user_id, mischief_id)){
			user.getMichiefs().add(mischief);
			mainServ.createUser(user);
			return "redirect:/dashboard";			
		} else {
			return "redirect:/dashboard";
		}
		
	}
	
	@GetMapping("/deleteJoin/{id}")
	public String deleteJoin(@PathVariable("id") Long id) {
		mainServ.deleteJoin(id);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/newMischief")
	public String newMischief(@ModelAttribute("mischief") Mischief mis, Model model, HttpSession session) {
		if(session.getAttribute("user_id") == null){
			return "redirect:/";
		}
    	Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
		return "newMischief.jsp";
	}
	
	@PostMapping("/makeMischief")
	public String makeMischief(@Valid @ModelAttribute("mischief") Mischief mis,
							   BindingResult result, HttpSession session) {		
		if (result.hasErrors()) {
			return "newMischief.jsp";
		} else {
			mainServ.create(mis);
			return "redirect:/dashboard";
		}
	}
	
	
	@RequestMapping("/edit/{id}")
	public String editMischief(@PathVariable("id") long id,
			Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You can't edit a random mischief without logging in, sorry. not.");
    		return "redirect:/";
    	}
		Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
		Mischief mischief = mainServ.oneMischief(id);
		model.addAttribute("mischief", mischief);
		return "editMischief.jsp";
	}
	
	
	@PostMapping(value="/updateMischief/{id}")
	public String updateMischief(@PathVariable("id") long id, 
			@Valid @ModelAttribute("book") Mischief mischief, BindingResult result) {
		if(result.hasErrors()) {
			return "editMischief.jsp";
		}
		else {
			mainServ.updateMischief(mischief);
			return "redirect:/mischief/" +id;
		}
	}
	
	
	@GetMapping("/mischief/{id}")
	public String oneMischief(@PathVariable Long id, Model model, HttpSession session,
			  RedirectAttributes redirectAttributes) {
		if (session.getAttribute("user_id") == null) {
		redirectAttributes.addFlashAttribute("error", "Can't view a mischief, but nice try. Now log in.");
		return "redirect:/";
		}
		User user = mainServ.oneUser((Long) session.getAttribute("user_id"));
		model.addAttribute("user", user);
		model.addAttribute("mischief", mainServ.oneMischief(id));
		return "showMischief.jsp";
	}
	
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("user_id") == null){
			return "redirect:/";
		}
		mainServ.deleteMischief(id);
		return "redirect:/dashboard";
	}
	
//	///making a join between a user and mischief
//	@PostMapping("/join")
//	public String makeJoin(@ModelAttribute("makeJoin") Join join) {
//		mainServ.makeJoin(join);
//		return "redirect:/dashboard";
//	}

}
