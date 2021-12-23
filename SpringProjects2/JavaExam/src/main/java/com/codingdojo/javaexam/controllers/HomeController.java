package com.codingdojo.javaexam.controllers;


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

import com.codingdojo.javaexam.models.Join;
import com.codingdojo.javaexam.models.LoginUser;
import com.codingdojo.javaexam.models.Student;
import com.codingdojo.javaexam.models.User;
import com.codingdojo.javaexam.models.YogaClass;
import com.codingdojo.javaexam.services.JoinService;
import com.codingdojo.javaexam.services.StudentService;
import com.codingdojo.javaexam.services.UserService;
import com.codingdojo.javaexam.services.YogaClassService;

@Controller
public class HomeController {
    
    @Autowired
    private UserService userServ;   
    @Autowired
    private YogaClassService classServ; 
    @Autowired
    private StudentService studentServ; 
    @Autowired
    private JoinService joinServ; 
    
    
    
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
    
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user_id");
    	return "redirect:/";
    }
    
	//////////////DASHBOARD ////////////////////////
	@RequestMapping("/dashboard")
	public String dashboard(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		if(session.getAttribute("user_id") != null){
			Long user_id = (Long) session.getAttribute("user_id");
			model.addAttribute("user", userServ.oneUser(user_id));
			model.addAttribute("classes", classServ.allClasses());
			return "dashboard.jsp";
		} else {
			redirectAttributes.addFlashAttribute("error", "Need to login. Please.");
			return "redirect:/";
		}
	}
	
	
	 //Create new course
    @GetMapping("/class/new")
    public String newclass(@ModelAttribute("class") YogaClass yogaClass, HttpSession session, 
    					  Model model, RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You need to be loggin in!");
    		return "redirect:/";
    	}
    	Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
    	return "new.jsp";
    }
    
	@PostMapping("/newclass")
	public String makeclass(@Valid @ModelAttribute("class") YogaClass yogaClass, BindingResult result, HttpSession session, Model model) {
		if (result.hasErrors() & !yogaClass.isValidTime(yogaClass.getTime())) { 
			Long user_id = (Long)session.getAttribute("user_id");
	    	model.addAttribute("user", user_id);
			return "new.jsp";
		 } else { 
		classServ.createClass(yogaClass);
	    return "redirect:/dashboard";
		 }
	}
	
	///Edit class
	@RequestMapping("/class/edit/{id}")
	public String editClass(@PathVariable("id") long id,@ModelAttribute("class") YogaClass yogaclass,
			Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You can't edit a random class without logging in, sorry. not.");
    		return "redirect:/";
    	}
		Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
		YogaClass yogaClass = classServ.findClassById(id);
		model.addAttribute("yogaclass", yogaClass);
		return "edit.jsp";
	}
	
	@PutMapping(value="/updateclass/{id}")
	public String updateClass(@PathVariable("id") long id, 
			@Valid @ModelAttribute("class") YogaClass yogaclass, BindingResult result, HttpSession session, Model model) {
		if(result.hasErrors()) {
			Long user_id = (Long)session.getAttribute("user_id");
	    	model.addAttribute("user", user_id);
			YogaClass yogaClass = classServ.findClassById(id);
			model.addAttribute("yogaclass", yogaClass);
			return "edit.jsp";
		}
		else {
			classServ.updateClass(yogaclass);
			return "redirect:/class/" +id;
		}
	}
	
	//Show one class
	@GetMapping("/class/{id}")
	public String onebook(@PathVariable Long id, Model model, HttpSession session,@ModelAttribute("student") Student student,
			@ModelAttribute("addStudentToClass") Join join, RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "Can't view a class, but nice try. Now log in.");
    		return "redirect:/";
    	}
		
		model.addAttribute("yogaclass", classServ.findClassById(id));
		model.addAttribute("studentList", studentServ.findByNotClass(classServ.findClassById(id)));
		model.addAttribute("allJoins", joinServ.allJoins());
//		model.addAttribute("join", joinServ.findJoinById(id))

		return "show.jsp";
	}
	
	
	@GetMapping("/class/delete/{id}")
	public String deleteClass(@PathVariable("id") long id, RedirectAttributes redirectAttributes,
							 HttpSession session) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You really think you can delete a class like this? nope.");
    		return "redirect:/";
    	}
		classServ.deleteClass(id);
		redirectAttributes.addFlashAttribute("message", "Your Class has been deleted");	
		return "redirect:/dashboard";
	}

	//	new student
	@PostMapping("/newstudent")
	public String newstudent(@Valid @ModelAttribute("student") Student student,
			BindingResult result, @RequestParam(name="yogaclass") Long class_id,
			HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) { 
			if (session.getAttribute("user_id") == null) {
	        	redirectAttributes.addFlashAttribute("error", "Can't view a class, but nice try. Now log in.");
	    		return "redirect:/";
	    	}
			
			model.addAttribute("yogaclass", classServ.findClassById(class_id));
			model.addAttribute("studentList", studentServ.findByNotClass(classServ.findClassById(class_id)));
			model.addAttribute("allJoins", joinServ.allJoins());
	    return "show.jsp";
		 } else {   
		studentServ.createStudent(student);
	    return "redirect:/student/post/" + class_id + "/" + student.getId();
		 }
	}
	
	///add students to classes
	@PostMapping("/createJoin")
	public String createJoin(@RequestParam(name="student") Long student_id,
							@RequestParam(name="yogaclass") Long class_id) {
		Student student = studentServ.findStudentById(student_id);
		YogaClass yogaclass = classServ.findClassById(class_id);
		
		student.getClasses().add(yogaclass);
		studentServ.createStudent(student);
		return "redirect:/class/" + class_id;	
	}
	
	@RequestMapping("/student/post/{classId}/{studentId}")
    public String createAutoJoin(@PathVariable("studentId") Long studentId, @PathVariable("classId") Long classId) {
        Student newStudent = studentServ.findStudentById(studentId);
        YogaClass newClass = classServ.findClassById(classId);
            newStudent.getClasses().add(newClass);
            studentServ.createStudent(newStudent);
            return "redirect:/class/" + classId;

    }
	
	@GetMapping("/student/delete/{id}/{class_id}")
	public String deleteStudent(@PathVariable("id") Long student_id, @PathVariable("class_id") Long class_id) {
		
		studentServ.deleteStudent(student_id);
		return "redirect:/class/" + class_id;
	}
	
	
	@GetMapping("/deleteJoin/{id}")
	public String deleteJoin(@PathVariable("id") Long id, @RequestParam(name="yogaclass") Long class_id) {
		joinServ.deleteJoin(id);
		return "redirect:/class/" + class_id;
	}
	
	
    
}
