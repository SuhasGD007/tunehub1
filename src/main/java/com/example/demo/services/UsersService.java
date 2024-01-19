package com.example.demo.services;

import com.example.demo.entities.Users;

public interface UsersService {
	
	public String addUser(Users user);
	public boolean emailExist(String email);
	public boolean validateUser(String email, String password);
	public String getRole(String email);
	
	public Users getUsers(String email);  //using parameter email we get a Users Object.
	
	public void updateUser(Users user);

}
