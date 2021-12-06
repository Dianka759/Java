package com.codingdojo.counter.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CounterController {
	
	@RequestMapping("/")
	public String index(HttpSession session) {
        // If the count is not already in session
        if (session.getAttribute("count") == null) {
        	session.setAttribute("count", 1);
        }
        else {
            // increment the count by 1 using getAttribute and setAttribute
            Integer currentCount = (Integer) session.getAttribute("count");
            currentCount++;
            session.setAttribute("count", currentCount);
        }
        return "index.jsp";
	}
	
	@RequestMapping("/count")
	public String counter(HttpSession session, Model model) {
		Integer currentCount = (Integer) session.getAttribute("count");
        model.addAttribute("countToShow", currentCount);
        return "showCount.jsp";
	}
	
	@RequestMapping("/addTwo")
	public String addTwo(HttpSession session) {
        if (session.getAttribute("count") == null) {
        	session.setAttribute("count", 2);
        } else {
		Integer currentCount = (Integer) session.getAttribute("count");
		currentCount += 2;
		session.setAttribute("count", currentCount); 
        }
        return "index.jsp";
	}
	
	@RequestMapping(value="/reset", method=RequestMethod.POST)
	public String reset(HttpSession session) {
//        session.setAttribute("count", 0);
		session.invalidate(); 
        return "redirect:/count";
	}
}
