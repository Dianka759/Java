package com.codingdojo.tripshare.controllers;

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

import com.codingdojo.tripshare.models.Comment;
import com.codingdojo.tripshare.models.Friendship;
import com.codingdojo.tripshare.models.Location;
import com.codingdojo.tripshare.models.LoginUser;
import com.codingdojo.tripshare.models.Rating;
import com.codingdojo.tripshare.models.Signup;
import com.codingdojo.tripshare.models.Trip;
import com.codingdojo.tripshare.models.User;
import com.codingdojo.tripshare.services.CommentService;
import com.codingdojo.tripshare.services.FriendshipService;
import com.codingdojo.tripshare.services.LocationService;
import com.codingdojo.tripshare.services.RatingService;
import com.codingdojo.tripshare.services.SignupService;
import com.codingdojo.tripshare.services.TripService;
import com.codingdojo.tripshare.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private CommentService commentServ;
	@Autowired
	private FriendshipService friendshipServ;
	@Autowired
	private LocationService locationServ;
	@Autowired
	private RatingService ratingServ;
	@Autowired
	private SignupService signupServ;
	@Autowired
	private TripService tripServ;
	@Autowired
	private UserService userServ;
	
///////////////////////////////////////////////////////////////////////////////////////////////////////
	// Login and reg
///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//renders the forms for login and reg
    @GetMapping("/")
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
    	userServ.register(newUser, result);
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
        User user = userServ.login(newLogin, result);
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
    	return "redirect:/";
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////////
// Dashboard
///////////////////////////////////////////////////////////////////////////////////////////////////////
	
    @RequestMapping("/dashboard")
	public String dashboard(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "Please register or log in.");
    		return "redirect:/";
    	}
		Long user_id = (Long) session.getAttribute("user_id");
		User user = userServ.oneUser(user_id);
		model.addAttribute("user", user);
		model.addAttribute("locations", locationServ.allLocationes());
		model.addAttribute("potentialFriends", userServ.findAllUsers());
		model.addAttribute("allJoins", friendshipServ.allFriendshipes());
		return "dashboard.jsp";
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////
// New Trip 
///////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	// New page render
	@RequestMapping("/new/tripshare")
	public String newTripShare(@ModelAttribute("newTripshare") Trip trip, 
							   @ModelAttribute("newLocation") Location location, HttpSession session, 
							   RedirectAttributes redirectAttributes, Model model) {
		if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You need to be logged in!");
    		return "redirect:/";
    	}
		Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
		return "newTrip.jsp";
	}
	
	// New page post
	@PostMapping("/maketripshare")
	public String create(@Valid @ModelAttribute("newTripshare") Trip trip, BindingResult result,
						 Model model, HttpSession session) {
		if (result.hasErrors()) { 
			Long user_id = (Long)session.getAttribute("user_id");
	    	model.addAttribute("user", user_id);
		    return "newTrip.jsp";
			 } else {
			Long user_id = (Long)session.getAttribute("user_id");
			User user = userServ.oneUser(user_id);
		    model.addAttribute("user", user_id);
		    Trip newTrip = tripServ.createTrip(trip);
		    Long id = (Long) newTrip.getId();	
		    
		    Signup signup = new Signup();
		    signup.setUser(user);
		    signup.setTrip(newTrip);
		    signupServ.createSignup(signup);
		    
		    model.addAttribute("trip", tripServ.findTripById(id));
		    return "redirect:/create/tripshare/" + id;
			}
	}
		
	/////// INITIAL CREATION 
	@RequestMapping("/create/tripshare/{id}")
	public String initialEdit(@PathVariable("id") Long id, Model model, @ModelAttribute("newTripshare") Trip trip,
							  @ModelAttribute("newLocation") Location location,
							  @ModelAttribute("addToTrip") User friend,
							  HttpSession session, RedirectAttributes redirectAttributes) {
		if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You need to be logged in!");
    		return "redirect:/";
    	}
		Long user_id = (Long)session.getAttribute("user_id");
		User user = userServ.oneUser(user_id);
    	model.addAttribute("user", user);
		model.addAttribute("trip", tripServ.findTripById(id));
		model.addAttribute("friendsNot", userServ.findByNotOnTrip(trip));
		model.addAttribute("participants", signupServ.allSignupes());
		return "newTrip.jsp";
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////
// Add and delete location from trip
///////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value="/delete/location/{location_id}/{trip_id}")
	public String deleteLocation(@PathVariable("location_id") Long location_id, 
								@PathVariable("trip_id") Long trip_id,
								@ModelAttribute("newTripshare") Trip trip, HttpSession session, 
								Model model, RedirectAttributes redirectAttributes) {
		locationServ.deleteLocation(location_id);
		if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You need to be logged in!");
    		return "redirect:/";
    	}
		Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
		return "redirect:/create/tripshare/" + trip_id;
	}

	@PostMapping("/addLocation/{trip_id}")
	public String addLocation(@Valid @ModelAttribute("newLocation") Location location, BindingResult result,
							  @PathVariable("trip_id") Long trip_id, 
							  @ModelAttribute("addToTrip") User friend,
							  Model model, HttpSession session,
							  @ModelAttribute("updateTripshare") Trip trip2,
							  @ModelAttribute("addToTrip") User friend2) {
		if (result.hasErrors()) { 
			Long user_id = (Long)session.getAttribute("user_id");
			User user = userServ.oneUser(user_id);
			model.addAttribute("user", user);
			model.addAttribute("trip", tripServ.findTripById(trip_id));
			model.addAttribute("friendsNot", userServ.findByNotOnTrip(tripServ.findTripById(trip_id)));
			model.addAttribute("participants", signupServ.allSignupes());
		    return "newTrip.jsp";
			} else {		
			Trip trip = tripServ.findTripById(trip_id);
			Long user_id = (Long)session.getAttribute("user_id");		
		    model.addAttribute("user", user_id); 
		    location.setTrip(trip);
		    locationServ.createLocation(location);
		    return "redirect:/create/tripshare/" + trip_id;
			}
	}
	
	///////////////////for the edit page ////////////////////////
	@RequestMapping(value="/delete2/location/{location_id}/{trip_id}")
	public String deleteLocation2(@PathVariable("location_id") Long location_id, 
								@PathVariable("trip_id") Long trip_id,
								@ModelAttribute("newTripshare") Trip trip, HttpSession session, 
								Model model, RedirectAttributes redirectAttributes) {
		locationServ.deleteLocation(location_id);
		if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You need to be logged in!");
    		return "redirect:/";
    	}
		Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
		return "redirect:/trip/edit/" + trip_id;
	}

	@PostMapping("/addLocation2/{trip_id}")
	public String addLocation2(@Valid @ModelAttribute("newLocation") Location location, BindingResult result,
							  @PathVariable("trip_id") Long trip_id, 
							  @ModelAttribute("addToTrip") User friend,
							  Model model, HttpSession session,
							  @ModelAttribute("updateTripshare") Trip trip2,
							  @ModelAttribute("addToTrip") User friend2) {
		if (result.hasErrors()) { 
			Long user_id = (Long)session.getAttribute("user_id");
			User user = userServ.oneUser(user_id);
			model.addAttribute("user", user);
			model.addAttribute("trip", tripServ.findTripById(trip_id));
			model.addAttribute("friendsNot", userServ.findByNotOnTrip(tripServ.findTripById(trip_id)));
			model.addAttribute("participants", signupServ.allSignupes());
			return "editTrip.jsp";
			
			} else {		
			Trip trip = tripServ.findTripById(trip_id);
			Long user_id = (Long)session.getAttribute("user_id");		
		    model.addAttribute("user", user_id); 
		    location.setTrip(trip);
		    locationServ.createLocation(location);
		    return "redirect:/trip/edit/" + trip_id;
			}
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////
// adding a friend to a trip
///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// on the create page
	@PostMapping("/addToTrip/{friend_id}/{trip_id}")
	public String addFriendToTrip(@PathVariable("friend_id") Long friend_id,
								  @PathVariable("trip_id") Long trip_id,
								  @ModelAttribute("addToTrip") Signup signup,
								  HttpSession session, Model model, RedirectAttributes redirectAttributes,
								  BindingResult result) {
		Long user_id = (Long)session.getAttribute("user_id");
		model.addAttribute("user", user_id);
		if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You need to be logged in!");
    		return "redirect:/";
    	}
		
		User user = userServ.oneUser(friend_id);
		Trip trip = tripServ.findTripById(trip_id);
		Signup newsignup = new Signup();
	
		
		newsignup.setTrip(trip);
		newsignup.setUser(user);
		signupServ.createSignup(newsignup);
			
		return "redirect:/create/tripshare/" + trip_id;	
	}
	
	// on the edit page
	@PostMapping("/addToTrip2/{friend_id}/{trip_id}")
	public String addFriendToTrip2(@PathVariable("friend_id") Long friend_id,
								  @PathVariable("trip_id") Long trip_id,
								  @ModelAttribute("addToTrip") Signup signup,
								  HttpSession session, Model model, RedirectAttributes redirectAttributes,
								  BindingResult result) {
		Long user_id = (Long)session.getAttribute("user_id");
		model.addAttribute("user", user_id);
		if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You need to be logged in!");
    		return "redirect:/";
    	}
		
		User user = userServ.oneUser(friend_id);
		Trip trip = tripServ.findTripById(trip_id);
		Signup newsignup = new Signup();
	
		
		newsignup.setTrip(trip);
		newsignup.setUser(user);
		signupServ.createSignup(newsignup);
			
		return "redirect:/trip/edit/" + trip_id;	
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////
// Edit Trip info
///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/////// edit route
	@RequestMapping("/trip/edit/{id}")
	public String editTrip(@PathVariable("id") Long id, Model model, @ModelAttribute("updateTripshare") Trip trip,
			  @ModelAttribute("newLocation") Location location,
			  @ModelAttribute("addToTrip") User friend,
			  HttpSession session, RedirectAttributes redirectAttributes) {
		if (session.getAttribute("user_id") == null) {
		redirectAttributes.addFlashAttribute("error", "You need to be logged in!");
		return "redirect:/";
		}
		Long user_id = (Long)session.getAttribute("user_id");
		User user = userServ.oneUser(user_id);
		model.addAttribute("user", user);
		model.addAttribute("trip", tripServ.findTripById(id));
		model.addAttribute("friendsNot", userServ.findByNotOnTrip(trip));
		model.addAttribute("participants", signupServ.allSignupes());
		
		model.addAttribute("test", userServ.NotParticipatingOrFriend(trip, friend));
		return "editTrip.jsp";
	}
	
	
	////// Edit page post
	@PutMapping("/updatetrip/{id}")
	public String update(@PathVariable("id") Trip trip) {
		
		return "redirect:/dashboard";
	}
	

///////////////////////////////////////////////////////////////////////////////////////////////////////
// Show one Trip page
///////////////////////////////////////////////////////////////////////////////////////////////////////	

	//////Show one trip 
	@RequestMapping("/trip/{id}")
	public String showOne(@ModelAttribute("addComment") Comment comment, @PathVariable("id") Long id, Model model, HttpSession session) {
		Long user_id = (Long) session.getAttribute("user_id");
		User user = userServ.oneUser(user_id);
		model.addAttribute("user", user);
		model.addAttribute("trip", tripServ.findTripById(id));
		model.addAttribute("comments", commentServ.allCommentes());
		return "trip.jsp";
	}
	
	@PostMapping("/comment/{id}")
	public String makeComment(@Valid @ModelAttribute("addComment") Comment comment, BindingResult result,
							  @PathVariable("id") Long id, Model model, HttpSession session) {
		if (result.hasErrors()) { 
			Long user_id = (Long) session.getAttribute("user_id");
			User user = userServ.oneUser(user_id);
			model.addAttribute("user", user);
			model.addAttribute("trip", tripServ.findTripById(id));
			model.addAttribute("comments", commentServ.allCommentes());
			return "trip.jsp";
		} else {
			commentServ.createComment(comment);
			return "redirect:/trip/" + id;	
		}		
		
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////
// Ratings page
///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//////Ratings page
	@RequestMapping("/rating/{location_id}/{trip_id}")
	public String rating(@PathVariable("location_id") Long location_id,
						 @PathVariable("trip_id") Long trip_id,
						 Model model, @ModelAttribute("postReview") Rating rating ) {
		
		model.addAttribute("location", locationServ.findLocationById(location_id));
		model.addAttribute("trip", tripServ.findTripById(trip_id));

		return "rating.jsp";
	}
	
	/////POST rating
	@PostMapping("/leaverating/{location_id}/{trip_id}")
	public String giverating(@Valid @ModelAttribute("postReview") Rating rating, BindingResult result,
							 @PathVariable("location_id") Long location_id,
			                 @PathVariable("trip_id") Long trip_id,
			                 HttpSession session, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("location", locationServ.findLocationById(location_id));
			model.addAttribute("trip", tripServ.findTripById(trip_id));
			return "rating.jsp";
		} else {
			Location location = locationServ.findLocationById(location_id);
			
			Long user_id = (Long) session.getAttribute("user_id");
			User user = userServ.oneUser(user_id);	
			
			rating.setUser(user);
			rating.setLocation(location);
			ratingServ.createRating(rating);
		return "redirect:/rating/" + location_id + "/" + trip_id;
		}
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////
// Add a new friend 
///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("/addFriend/{id}")
	public String addFriend(@PathVariable("id") Long id, @ModelAttribute("addFriend") Friendship friendship,
							HttpSession session) {
		
		Long user_id = (Long) session.getAttribute("user_id");
		User user = userServ.oneUser(user_id);		
		User friend = userServ.oneUser(id);
		
		friendship.setUser(user);
		friendship.setFriend(friend);
		friendshipServ.createFriendship(friendship);
		
		return "redirect:/dashboard";
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////
// Delete a friendship
///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/deleteFriendship/{id}")
	public String deleteFriendship(@PathVariable("id") Long id) {
		friendshipServ.deleteFriendship(id);
		return "redirect:/dashboard";
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////
// delete people form trip 
///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//on the create page
	@GetMapping("/deleteParticipant/{signup_id}/{trip_id}")
	public String deleteParticipant(@PathVariable("signup_id") Long signup_id,
									@PathVariable("trip_id") Long trip_id) {
		signupServ.deleteSignup(signup_id);
		return "redirect:/trip/edit/" + trip_id;
	}
	
	//on the edit page
	@GetMapping("/deleteParticipant2/{signup_id}/{trip_id}")
	public String deleteParticipant2(@PathVariable("signup_id") Long signup_id,
									@PathVariable("trip_id") Long trip_id) {
		signupServ.deleteSignup(signup_id);
		return "redirect:/create/tripshare/" + trip_id;
	}
}
