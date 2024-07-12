package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	private final PostDaoService postDaoService;
	private UserRepository userRepository;
	
	private PostRepository postRepository;

	public UserJpaResource(UserRepository userRepository, PostRepository postRepository, PostDaoService postDaoService) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
		this.postDaoService = postDaoService;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	
	//http://localhost:8080/users
	
	//EntityModel
	//WebMvcLinkBuilder
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		return user.get().getPosts();

	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/jpa/users/{uid}/posts/{pid}")
	public Post retrievePostsForUser(@PathVariable int uid, @PathVariable int pid) {
		Optional<User> user = userRepository.findById(uid);
		Optional<Post> dbPost = postRepository.findById(pid);

		if(user.isEmpty())
			throw new UserNotFoundException("user id:"+uid);
		if(dbPost.isEmpty())
			throw new UserNotFoundException("post id:"+pid);

		return dbPost.get();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();   

		return ResponseEntity.created(location).build();

	}

	@PatchMapping("/jpa/users/{uid}/posts/{pid}")
	public ResponseEntity<Object> UpdatePostForUser(@PathVariable int uid, @PathVariable int pid, @Valid @RequestBody PostUpdateDTO postUpdateDto) {
		Optional<User> user = userRepository.findById(uid);
		Optional<Post> dbPost = postRepository.findById(pid);

		if(user.isEmpty())
			throw new UserNotFoundException("id:"+uid);
		if(dbPost.isEmpty())
			throw new UserNotFoundException("post id:"+pid);

		Post updatedPost = postDaoService.updatePost(pid, postUpdateDto);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

		return ResponseEntity.created(location).build();

	}


}
