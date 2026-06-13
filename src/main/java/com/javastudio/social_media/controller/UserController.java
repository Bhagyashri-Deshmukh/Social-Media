package com.javastudio.social_media.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javastudio.social_media.exception.MyUserNotFoundException;
import com.javastudio.social_media.model.Post;
import com.javastudio.social_media.model.Userr;
import com.javastudio.social_media.repository.PostRepository;
import com.javastudio.social_media.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/get-all-users")
	public List<Userr> getAllUsers() {
		// TODO Auto-generated method stub
		return userService.getAllUsers();
	}

	@GetMapping("/get-user-by-id/{id}")
	public EntityModel<Optional<Userr>> getUserById(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		Optional<Userr> user = userService.getUserById(id);	
		if(user.isPresent()){
		
			EntityModel<Optional<Userr>> entityModel = EntityModel.of(user);
			WebMvcLinkBuilder link =linkTo(methodOn(this.getClass()).getAllUsers());
			entityModel.add(link.withRel("all-users"));
			
			return entityModel;			
		}
		
		throw new MyUserNotFoundException("User with id : "+id+" does not exist in database!");		
	}

	@GetMapping("/get-user-by-name/{name}")
	public Userr getUserByname(@PathVariable String name) {
		// TODO Auto-generated method stub
		return userService.getUserByname(name);
	}

	@PostMapping("/add-user")
	public ResponseEntity<Object> createUser(@Validated @RequestBody Userr user) {
		// TODO Auto-generated method stub
		
		Userr savedUser = userService.createUser(user);
		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedUser.getId())
							.toUri();
		
		return ResponseEntity.created(location).build();
	}

	@PostMapping("/update-user/{id}")
	public Userr updateUser(@RequestBody Userr user, @PathVariable Integer id) {
		// TODO Auto-generated method stub
		return userService.updateUser(user,id);
	}

	@DeleteMapping("/delete-user/{id}")
	public void deleteUser(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		userService.deleteUser(id);
	}
	
	@GetMapping("/user/{id}/posts")
	public List<Post> getPostForUser(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		Optional<Userr> user = userService.getUserById(id);	
		if(user.isEmpty()) {
			throw new MyUserNotFoundException("User with id : "+id+" does not found!");
		}
		return user.get().getPost();
	}
	
	@PostMapping("/user/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable Integer id, @RequestBody Post post) {
		// TODO Auto-generated method stub
		Optional<Userr> user = userService.getUserById(id);	
		if(user.isEmpty()) {
			throw new MyUserNotFoundException("User with id : "+id+" does not found!");
		}
		
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
