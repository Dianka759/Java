package com.codingdojo.studentroster.controllers;

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

import com.codingdojo.studentroster.models.Dorm;
import com.codingdojo.studentroster.models.Student;
import com.codingdojo.studentroster.services.MainService;

@Controller
public class HomeController {

	@Autowired 
	MainService mainService;
	
	///////Root///////
	@GetMapping("/")
	private String index() {
		return "redirect:/students/new";
	}
	
	/////////////////Dashboard of all dojos /////////////////
	@RequestMapping("/dorms")
	public String allDorms(Model model) {
		model.addAttribute("dorms", mainService.allDorms());
		model.addAttribute("students", mainService.allStudents());
		return "Dorms.jsp";
	}
	
	//////////////////CREATING DORMS AND STUDENTS
    @RequestMapping("/dorms/new")
    public String newdojo(@ModelAttribute("dorm") Dorm dorm) {
        return "NewDorm.jsp";
    }
	
	@PostMapping("/newdorm")
	public String dorm(@Valid @ModelAttribute("dorm") Dorm dorm, BindingResult result) {
		if (result.hasErrors()) { 
	    return "NewDorm.jsp";
		 } else {   
		mainService.createDorm(dorm);
	    return "redirect:/dorms/" + dorm.getId();
		 }
	}
	
	@RequestMapping("/dorms/create")
	public String createDorm(@RequestParam(value="name", required=false) String name) {
		Dorm dorm = new Dorm(name);
		mainService.createDorm(dorm);
		return "redirect:/dorms/" + dorm.getId();
	}
	
    @RequestMapping("/students/new")
    public String newstudent(@ModelAttribute("student") Student student) {
        return "NewStudent.jsp";
    }
    
	@PostMapping("/newstudent")
	public String student(Model model,
						 @Valid @ModelAttribute("student") Student student, 
						 BindingResult result) {
		if (result.hasErrors()) {
	    return "NewStudent.jsp";
		 } else {   
		mainService.createStudent(student);
	    return "redirect:/dorms";
		 }
	}
	
	///////////////////////////Showing one dorm with students/////////////////
	@GetMapping("/dorms/{id}")
	public String showStudent(@PathVariable Long id, Model model) { 
		Dorm dorm = mainService.findDormById(id);
		model.addAttribute("dorm", dorm); 
		model.addAttribute("students", mainService.studentsWithNoDorms());
		return "ShowDorm.jsp";
	}
	
	@RequestMapping("/dorms/{id}/add")
	public String addToDorm(@PathVariable("id") Long id, @RequestParam(value="student", required=false) Long studentID) {
		Dorm dorm = mainService.findDormById(id);
		mainService.addStudentByIdToDorm(studentID, dorm);
		return "redirect:/dorms/" + id;
	}
	
	///////////////////////////DELETE////////////////////////
	@RequestMapping("/dorms/{id}/remove")
	public String removeStudentFromDorm(@PathVariable("id") Long dormID, @RequestParam("student") Long studentID) {
		mainService.removeStudentByIdFromDorm(studentID);
		return "redirect:/dorms/" + dormID;
	}
	
	@RequestMapping(value="delete/{id}")
	public void destroy(@PathVariable("id") Long id, 
						  RedirectAttributes redirectAttributes) {
		mainService.deleteStudent(id);
		redirectAttributes.addFlashAttribute("message", "Student has been deleted from the dorm");	
//		return "redirect:/dorms/";
	}
}
