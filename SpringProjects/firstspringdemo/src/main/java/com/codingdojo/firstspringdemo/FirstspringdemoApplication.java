package com.codingdojo.firstspringdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

@Controller
public class FirstspringdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstspringdemoApplication.class, args);
		}
	
		@RequestMapping("/")
		public String index() {
			return "index.jsp";
		}
			
		@RequestMapping("/buttseat")
		public String booty() {
			return "yass booty booty booty booty";
	}

}
