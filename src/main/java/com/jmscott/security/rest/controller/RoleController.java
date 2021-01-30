package com.jmscott.security.rest.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jmscott.security.rest.exception.ResourceNotFoundException;
import com.jmscott.security.rest.model.Role;
import com.jmscott.security.rest.repository.RoleRepository;

// TODO: double check that responses are to industry standard

@RestController
@RequestMapping(path = "/roles")
@CrossOrigin(origins= {"http://localhost:3001", "http://localhost:3000", "https://security.jmscottnovels.com", "https://blog.jmscottnovels.com"}, allowCredentials = "true")
public class RoleController {
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping
	public List<Role> getRolesAll() {
		return roleRepository.findAll();
	}
	
	// qRole.roles.any().role.eq("ADMIN")	// role any() for array in object
	
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable String id) throws ResourceNotFoundException {
		Role role = roleRepository.findById(id).orElseThrow(
			() -> new ResourceNotFoundException("Role not found with id " + id)
		);
		
		return ResponseEntity.ok(role);	// .ok().build(role)
	}
	
	@PostMapping
	public ResponseEntity<Void> createRole(@Validated @RequestBody Role role) {
		role.setId(null);
		Role newRole = roleRepository.save(role);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}")
				.buildAndExpand(newRole.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Role> updateRole(
			@PathVariable String id,
			@Validated @RequestBody Role roleDetails) throws ResourceNotFoundException {
		roleRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Security role not found with id " + id) );
		roleRepository.save(roleDetails);
		
		return new ResponseEntity<Role>(roleDetails, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Role> deleteUser(@PathVariable String id) throws ResourceNotFoundException {
		Role deletedRole = roleRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Security role not found with id " + id) );
		roleRepository.deleteById(id);
		
		return new ResponseEntity<Role>(deletedRole, HttpStatus.OK);
	}
}
