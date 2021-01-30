package com.jmscott.security.rest.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jmscott.security.rest.model.Password;
import com.jmscott.security.rest.model.QUser;
import com.jmscott.security.rest.model.Role;
import com.jmscott.security.rest.auth.CustomUserDetailsService;
import com.jmscott.security.rest.exception.ResourceNotFoundException;
import com.jmscott.security.rest.model.User;
import com.jmscott.security.rest.repository.PasswordRepository;
import com.jmscott.security.rest.repository.RoleRepository;
import com.jmscott.security.rest.repository.UserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

//TODO: double check that responses are to industry standard

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins= {"http://localhost:3003", "http://localhost:3001", "http://localhost:3000", "https://security.jmscottnovels.com", "https://blog.jmscottnovels.com"}, allowCredentials = "true")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordRepository passwordRepository;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private RoleRepository roleRespository;
	
	@GetMapping
	@CrossOrigin
	public List<User> getUsersAll(@RequestParam(required = false) boolean showDisabled) {
		QUser qUser = new QUser("user");
		BooleanExpression filterById = qUser.id.ne("5fd907ac51ba6208a6783b0a"); // builtin admin account
//		BooleanExpression filterByEnabled = showDisabled ? null : globalUser.enabled.isTrue();
//		
		List<User> users = (List<User>) userRepository.findAll(filterById);
		
		return users;
	}
	
	@GetMapping(path = "/username/{username}")	// TODO: add url param for %like
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) throws ResourceNotFoundException {
		User user = userRepository.getByUsername(username).orElseThrow(
			() -> new ResourceNotFoundException("User not found: " + username)
		);
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping(path = "/lastName/{lastName}")	// FIX ME: like
	public List<User> getUsersByLastName(@PathVariable String lastName) {
		QUser qUser = new QUser("user");
		BooleanExpression filterByLastName = qUser.lastName.eq(lastName);
		
		List<User> users = (List<User>) this.userRepository.findAll(filterByLastName);
		
		return users;
	}
	
	@GetMapping(path = "/age-range/{ageLow}/{ageHigh}")
	public List<User> getUsersByAgeRange(@PathVariable int ageLow, @PathVariable int ageHigh) {
		QUser qUser = new QUser("user");
		BooleanExpression filterByAge = qUser.age.between(ageLow, ageHigh);
		
		List<User> users = (List<User>) this.userRepository.findAll(filterByAge);
		
		return users;
	}
	
	// qUser.roles.any().role.eq("ADMIN")	// use any() for array in object
	
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id) throws ResourceNotFoundException {
		User user = userRepository.findById(id).orElseThrow(
			() -> new ResourceNotFoundException("User not found with id " + id)
		);
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping(path = "/id0")
	public ResponseEntity<User> getGenericUser() throws ResourceNotFoundException {
		Collection<Role> roles = new ArrayList<>();
		roles.add(roleRespository.findByName("VIEWER"));
		
		User user = new User("John", "Doe", "john@domain.tld", 100, "fakeUserName", true, roles);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<Void> createUser(@Validated @RequestBody User user) {
		user.setId(null); // guarantee that mongo is creating id
		user.setEnabled(true);	// guarantee a new user is enabled. CONSIDER: one might want a new user to be disabled?
		
		User newUser = userRepository.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}")
				.buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<User> updateUser(
			@PathVariable String id,
			@Validated @RequestBody User userDetails) throws ResourceNotFoundException {
		userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User not found with id " + id) );
		User savedUser = userRepository.save(userDetails);
		
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
	
	@PutMapping(path = "/secret/{userId}")
	public ResponseEntity<User> updatePassword(
			@PathVariable String userId,
			@Validated @RequestBody Password passwordDetails) throws ResourceNotFoundException {
		User savedUser = userRepository.findById(userId).orElseThrow( () -> new ResourceNotFoundException("User not found with id " + userId) );
		passwordDetails.setId(passwordRepository.findByPersonId(userId).getId());
		userDetailsService.savePassword(passwordDetails);	// calls bCrypt first
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable String id) throws ResourceNotFoundException {
		User deletedUser = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User not found with id " + id) );
		userRepository.deleteById(id);
		
		return new ResponseEntity<User>(deletedUser, HttpStatus.OK);
	}
}
