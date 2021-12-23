package com.codingdojo.examtwo.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.examtwo.models.BabyName;
import com.codingdojo.examtwo.models.LoginUser;
import com.codingdojo.examtwo.models.User;
import com.codingdojo.examtwo.services.BabyNameService;
import com.codingdojo.examtwo.services.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userServ;
	@Autowired
	private BabyNameService babynameServ;

//	This route renders the forms for login and reg.
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}

//	This route is the action for submitting the login and registration form.
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {
//		This calls on the register method in service - sends in the newUser info and the results from the binding result.
		userServ.register(newUser, result);
//		If we have any errors, we stay on that page and display the errors. 
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
//		If everything is good, set the userId in session.
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/home";
	}

//	This route is the action for submitting the login form.
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		User user = userServ.login(newLogin, result);
//		If we have any errors we stay on the page and display errors.
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		session.setAttribute("user_id", user.getId());
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String dashboard(HttpSession session, Model model) {
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("user_id");
		User user = userServ.getUserInfo(userId);
		model.addAttribute("user", user);

		model.addAttribute("babynames", babynameServ.allBabyName());
		return "home.jsp";
	}

	@RequestMapping("/babyname/{id}/edit")
	public String editBabyName(@PathVariable("id") Long id, Model model, HttpSession session) {
		model.addAttribute("babyname", babynameServ.getOneBabyName(id));
		Long user_id = (Long) session.getAttribute("user_id");
		model.addAttribute("user", userServ.oneUser(user_id));
		return "edit.jsp";
	}

	@PutMapping("/babyname/update/{id}")
	public String babynameUpdate(@PathVariable("id") Long id, @Valid @ModelAttribute("babyname") BabyName babyname,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("babyname", babyname);
			return "edit.jsp";
		} else {
			babynameServ.updateBabyName(babyname);
			return "redirect:/home";
		}
	}

	@GetMapping("/home/new")
	public String newBabyName(HttpSession session, RedirectAttributes flash, Model model,
			@ModelAttribute("babyname") BabyName babyname) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			flash.addFlashAttribute("login", "Please login or register before entering");
			return "redirect:/";
		}
		session.setAttribute("count", babyname.getVotes());
//		if (session.getAttribute("count") == null) {
//        	session.setAttribute("count", 0);
//        }

		model.addAttribute("userId", userId);
		model.addAttribute("babyname", new BabyName());
		return "new.jsp";
	}

	@PostMapping("/home/create")
	public String createBabyName(@Valid @ModelAttribute("babyname") BabyName babyname, BindingResult result) {
		if (result.hasErrors()) {
			return "new.jsp";

		} else {
			babynameServ.saveBabyName(babyname);
			return "redirect:/home";

		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user_id");
		return "redirect:/";
	}

//	If no errors, set the UserID in session.
	@RequestMapping("/home")
	public String home(HttpSession session, Model model) {
		
		if (session.getAttribute("user_id") != null) {
			Long user_id = (Long) session.getAttribute("user_id");
			model.addAttribute("user", userServ.oneUser(user_id));
			List<BabyName> allBabyNames = babynameServ.allBabyName();
			model.addAttribute("babynames", allBabyNames);
			return "home.jsp";
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/vote/{id}")
	public String count(@PathVariable("id") Long id, Model model, HttpSession session) {
		  
	BabyName babyname = babynameServ.getOneBabyName(id);
    int vote = (int) babyname.getVotes();
    vote = 2;
    babyname.Votes(2);
    babyname.setVotes(2);
	        
		return "redirect:/home";
	}

	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		babynameServ.deleteBabyName(id);
		return "redirect:/home";
	}

	@RequestMapping("/view/{id}")
	public String getOneBabyName(@PathVariable("id") Long id, Model model, HttpSession session) {
		model.addAttribute("babyname", babynameServ.getOneBabyName(id));
		return "view.jsp";
	}
}
