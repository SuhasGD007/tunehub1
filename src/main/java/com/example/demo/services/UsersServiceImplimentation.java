package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.repositories.UsersRepositories;

@Service
public class UsersServiceImplimentation implements UsersService{

	@Autowired
	UsersRepositories repo;
	
	@Override
	public String addUser(Users user) {
		repo.save(user);
		return "User Added Successfully";
	}

	@Override
	public boolean emailExist(String email) {
		if(repo.findByEmail(email)==null) {      //repo.findByEmail(email) will fetch the email from the database. If there is no email in database
			                                     // then it return false else true.
			return false;
		}else {
			 return true;
		}
	
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user=repo.findByEmail(email);     //using the email we will fetch the user object from the database
		String db_pass=user.getPassword();
		if(password.equals(db_pass)) {
			return true;
		}else {
			return false;
		}

	}

	@Override
	public String getRole(String email) {
		Users user= repo.findByEmail(email);   
		return user.getRole();
	}

	@Override
	public Users getUsers(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		repo.save(user);	
	}
	
	
	
	
	
	

} 
