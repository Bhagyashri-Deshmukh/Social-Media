package com.javastudio.social_media.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javastudio.social_media.model.Userr;
import com.javastudio.social_media.repository.UserRepository;
import com.javastudio.social_media.service.UserService;

@Service
public class UserrServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Userr> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Optional<Userr> getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public Userr getUserByname(String name) {
		// TODO Auto-generated method stub
		return userRepository.getUserByname(name);
	}

	@Override
	public Userr createUser(Userr user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public Userr updateUser(Userr user,Integer id) {
		// TODO Auto-generated method stub
		Userr u2 = getUserById(id).get();
		
		u2.setName(user.getName());
		u2.setBirthDate(user.getBirthDate());		
		
		return userRepository.save(u2);
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);;
	}

}
