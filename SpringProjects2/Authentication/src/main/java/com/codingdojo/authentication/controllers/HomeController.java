package com.codingdojo.authentication.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.authentication.models.LoginUser;
import com.codingdojo.authentication.models.User;
import com.codingdojo.authentication.services.UserService;

@Controller
public class HomeController {
    
    @Autowired
    private UserService userServ;
    
    
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
    	userServ.register(newUser, result);
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
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/dashboard";
    }
    
    @RequestMapping("/dashboard")
    public String dashboard(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") != null) {
    	Long user_id = (Long) session.getAttribute("user_id");
    	model.addAttribute("user", userServ.oneUser(user_id));
    	return "dashboard.jsp";
    } else {
    	redirectAttributes.addFlashAttribute("error", "You need to be loggin in!");
    	return "redirect:/";
    	}
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user_id");
    	return "redirect:/";
    }
    
}
