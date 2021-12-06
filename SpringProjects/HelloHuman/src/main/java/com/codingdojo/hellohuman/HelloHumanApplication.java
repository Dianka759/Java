package com.codingdojo.hellohuman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloHumanApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloHumanApplication.class, args);
	}
	
	@RequestMapping("/")
	//can also have DefaultValue = "Human" in the name, no need for the loop
	public String index(@RequestParam(value="name", required=false) String name,
						@RequestParam(value="last_name", required=false) String last_name,
						@RequestParam(value="times", required=false) String times){
		
		if (name != null){
			if (last_name != null) {
				if (times != null) {
					int number = Integer.parseInt(times);
					return ("Hello " + name + " " + last_name + ", you come here often? ").repeat(number);
				} 
			return "Hello " + name + " " + last_name + ", you come here often?";
		} else {
			return "Hello " + name + ", you come here often?";
			}
		}
		return "Hello Nameless Person. You uhhh, come here often?";
	}    


}
