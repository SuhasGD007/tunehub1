package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {               //this controller is used to navigate to perticuler page
										//suppose in indexPage(HTML) if we press login it will redirect to NavController(backEnd)
									   //in backEnd it will return(redirect) to loginPage(HTML).
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
	
	@GetMapping("/newSong")
	public String newSong() {
		return "newSong";
	}
	
	

	
}
