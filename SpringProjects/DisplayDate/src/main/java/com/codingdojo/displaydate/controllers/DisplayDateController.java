package com.codingdojo.displaydate.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DisplayDateController {
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/date")
	public String date(Model model) {
		String pattern = "EEEE, 'the' d 'of' MMM, yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(new Date()); 
		model.addAttribute("date",date);

		return "date.jsp";
	}
	@RequestMapping("/time")
	public String time(Model model) {
		String pattern = "h:mm a";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String time = simpleDateFormat.format(new Date()); 
		model.addAttribute("time",time);
		
		return "time.jsp";
	}

}
