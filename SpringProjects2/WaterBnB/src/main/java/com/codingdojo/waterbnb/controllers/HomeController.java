package com.codingdojo.waterbnb.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.waterbnb.models.LoginUser;
import com.codingdojo.waterbnb.models.Pool;
import com.codingdojo.waterbnb.models.Review;
import com.codingdojo.waterbnb.models.User;
import com.codingdojo.waterbnb.services.MainService;

@Controller
public class HomeController {

	@Autowired
	MainService mainServ;
	
	@GetMapping("/")
	public String welcome(Model model) {
		model.addAttribute("pools", mainServ.allPools());
		return "index.jsp";
	}
	
	
	 //renders the forms for login and reg
    @GetMapping("/signup")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "login.jsp";
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
            return "login.jsp";
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
            return "login.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/dashboard";
    }
	
	
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user_id");
    	return "redirect:/signup";
    }
    
    
	@RequestMapping("/dashboard")
	public String dashboard(Model model, HttpSession session, @ModelAttribute("newListing") Pool pool,
							RedirectAttributes redirectAttributes) {
		if(session.getAttribute("user_id") != null){
			Long user_id = (Long) session.getAttribute("user_id");
			model.addAttribute("user", mainServ.oneUser(user_id));
			model.addAttribute("pools", mainServ.allPools());
			return "dashboard.jsp";
		} else {
        	redirectAttributes.addFlashAttribute("error", "You gotta login to access this page");
			return "redirect:/login";
		}
	}
	

	@PostMapping("/makeListing")
	public String makeListing(@Valid @ModelAttribute("newListing") Pool pool, Model model,
							   BindingResult result, HttpSession session) {		
		if (result.hasErrors()) {
			Long user_id = (Long) session.getAttribute("user_id");
			model.addAttribute("user", mainServ.oneUser(user_id));
			model.addAttribute("pools", mainServ.allPools());
			return "dashboard.jsp";
		} else {
			mainServ.createPool(pool);
			return "redirect:/dashboard";
		}
	}
	
	
	@RequestMapping("/edit/{id}")
	public String editPool(@PathVariable("id") Long id, @ModelAttribute("editListing") Pool pool,
			Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You can't edit a random listing without logging in, sorry. not.");
    		return "redirect:/login";
    	}
		Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
		Pool thispool = mainServ.findPoolById(id);
		model.addAttribute("pool", thispool);
		return "editListing.jsp";
	}
	
	
	@PutMapping("/updateListing/{id}")
	public String updatePool(@PathVariable("id") Long id, Model model, HttpSession session,
			@Valid @ModelAttribute("editListing") Pool pool, BindingResult result) {
		if(result.hasErrors()) {
			Long user_id = (Long)session.getAttribute("user_id");
	    	model.addAttribute("user", user_id);
			Pool thispool = mainServ.findPoolById(id);
			model.addAttribute("pool", thispool);
			return "editListing.jsp";
		}
		else {
			mainServ.updatePool(pool);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/pool/{id}")
	public String onePool(@PathVariable Long id, Model model, HttpSession session,
			  RedirectAttributes redirectAttributes) {
		User user = mainServ.oneUser((Long) session.getAttribute("user_id"));
		model.addAttribute("user", user);
		model.addAttribute("pool", mainServ.findPoolById(id));
		model.addAttribute("reviews", mainServ.reviewByPool(id));
		return "showLocation.jsp";
	}
	
	
	@RequestMapping("/location/search")
	public String search(@RequestParam("location") String location, Model model,
						 RedirectAttributes error) {
		List<Pool> pools = mainServ.findByAddressContaining(location);
		//if there is no location in the search, stay on page and return an error
		if(location == "") {
			error.addFlashAttribute("error", "Search field empty!");
			return "redirect:/";
		//if the location search returns an empty list of locations, erorr, no such location
		} else if (pools.isEmpty()) {
		error.addFlashAttribute("error", "Sorry, no such location!");
		return "redirect:/";
		//else, just return the locations by address
		} else {
		model.addAttribute("location", location);
		model.addAttribute("pools", pools);
		return "locationList.jsp";
		}
	}
	
	
	@GetMapping("/leaveReview/{id}")
	public String leaveReview(@PathVariable("id") Long id, Model model, HttpSession session,
			@ModelAttribute("newReview") Review review, RedirectAttributes error) {
		User user = mainServ.oneUser((Long) session.getAttribute("user_id"));
		if (session.getAttribute("user_id") == null) {
			error.addFlashAttribute("error", "Please register/log in to leave a review!!");
			return "redirect:/signup";
		} else {
		model.addAttribute("pool", mainServ.findPoolById(id));
		model.addAttribute("user", user);
		model.addAttribute("newReview", new Review());
		return "newReview.jsp";
		}
	}
	
//	@PostMapping("/createReview/{id}")
//	public String createReview(@PathVariable("id") Long id,
//								@Valid @ModelAttribute("newReview") Review review,
//								BindingResult result, HttpSession session,
//								Model model) {
//	
//		if (result.hasErrors()) {
//			return "redirect:/createReview"+id;
//		} else {
//			mainServ.createReview(review);
//			return "redirect:/pool/"+id;
//		}
//		
//	}
//	
	@PostMapping("/createReview/{id}")
	public String createReview(@PathVariable("id") Long id,
								@Valid @ModelAttribute("newReview") Review review,
								BindingResult result, HttpSession session,
								Model model) {
	
		if (result.hasErrors()) {
			return "redirect:/createReview"+id;
		} else {
			User user = mainServ.oneUser((Long) session.getAttribute("user_id"));
			Pool pool = mainServ.findPoolById(id);
			review.setUser(user);
			review.setPool(pool);
//			mainServ.createReview(user_id, id);
			mainServ.createReview(review);
			return "redirect:/pool/"+id;
		}
		
	}
	
}
