package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entities.Song;
import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

//@CrossOrigin("*")         //helps to connect with react port(3000) and spring port(8080)
//@RestController

@Controller
public class UsersController {
	
	//github.com/deep473/tuneHub(GitHub link)
	
	@Autowired
	UsersService service;
	
	@Autowired
	SongService songService;

	@PostMapping("/registration")
	public String addUsers(@ModelAttribute Users user) {           //modelAttribute is used instead of requestParam because
		                                                           //in requestParam we need to pass every variable separately whereas in ModelAttribute 
		                                                           //every variable is grouped as one object(user) 
		boolean userStatus=service.emailExist(user.getEmail());
		if(userStatus==false) {
			service.addUser(user);
			System.out.println("User Added!");
		}else {
			System.out.println("Email Already Exist!");
		}
		return "home";
	}
	
	@PostMapping("/validate")
	public String validate(@RequestParam String email , @RequestParam String password, HttpSession session, Model model) {   //RequestBody is used it takes the object of LoginData(which
																							//consists of email & password)

		
		if(service.validateUser(email,password)==true) {
			String role=service.getRole(email);
			
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "adminHome";
			}else {
				Users user= service.getUsers(email);
				boolean userStatus=user.isPremium();
				List<Song> songsList =songService.fetchAllSongs();
				model.addAttribute("songs", songsList);
				model.addAttribute("isPremium", userStatus);		
				return "customerHome";
			}
		}
		return "login";
	}
	
	
		
		
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "login";
	}
		
	
}
