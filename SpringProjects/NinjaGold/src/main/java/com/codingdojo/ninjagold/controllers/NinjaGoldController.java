package com.codingdojo.ninjagold.controllers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NinjaGoldController {
	Random rand = new Random();
	
	@RequestMapping("/")
	public String index(HttpSession session) {
		if(session.getAttribute("gold")==null || session.getAttribute("log")==null) {
			ArrayList<String> log = new ArrayList<String>();
			session.setAttribute("log", log);
			session.setAttribute("gold", 0);
		}
		return "index";
	}
	 
	
	@RequestMapping(value="process", method=RequestMethod.POST)
	public String process(@RequestParam(value="min") int min, 
						  @RequestParam(value="max") int max, 
						  @RequestParam(value="str") String str,
						  @RequestParam(value="reset", defaultValue ="") String reset,
						  HttpSession session,
						  Model model) {
		
		Date dt = new Date();
		String stringDate = DateFormat.getDateTimeInstance().format(dt);
		
		if(reset.equals("reset")||session.getAttribute("gold")==null || session.getAttribute("log")==null) {
			session.invalidate();
			return "redirect:/";
		}
		
		int currentGold = (int) session.getAttribute("gold");
		ArrayList<String> log = (ArrayList<String>) session.getAttribute("log");
		
		int x = rand.nextInt(max)+min;
		currentGold += x;
		String money = "";
		String color = "";
		
		if(x > 0) {
			money="earned "; 
			color= "<p class='green";
		}
		else {
			money="lost "; 
			color= "<p class='red";
		}
		
		String newlog =(color+str + money + x +" gold, balance is now: "+ currentGold +" "+ stringDate);
		log.add(0, newlog);
		
		session.setAttribute("gold", currentGold);
		session.setAttribute("log", log);
		model.addAttribute("gold");
		model.addAttribute("log");
		
		return "redirect:/";
		
	}
	
}
