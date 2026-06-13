package com.javastudio.social_media.service;

import java.util.List;
import java.util.Optional;

import com.javastudio.social_media.model.Userr;

public interface UserService {

	public List<Userr> getAllUsers();
	
	public Optional<Userr> getUserById(Integer id);
	
	public Userr getUserByname(String name);
	
	public Userr createUser(Userr user);
	
	public Userr updateUser(Userr user, Integer id);
	
	public void deleteUser(Integer id);

}
