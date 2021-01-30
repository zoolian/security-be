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
import com.jmscott.security.rest.model.Page;
import com.jmscott.security.rest.repository.PageRepository;

// TODO: double check that responses are to industry standard

@RestController
@RequestMapping(path = "/pages")
@CrossOrigin(origins= {"http://localhost:3001", "http://localhost:3000", "https://security.jmscottnovels.com", "https://blog.jmscottnovels.com"}, allowCredentials = "true")
public class PageController {
	@Autowired
	private PageRepository pageRepository;
	
	@GetMapping
	public List<Page> getPagesAll() {
		return pageRepository.findAll();
	}
	
	// qPage.pages.any().page.eq("ADMIN")	// page any() for array in object
	
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<Page> getPageById(@PathVariable String id) throws ResourceNotFoundException {
		Page page = pageRepository.findById(id).orElseThrow(
			() -> new ResourceNotFoundException("Page not found with id " + id)
		);
		
		return ResponseEntity.ok(page);	// .ok().build(page)
	}
	
	@PostMapping
	public ResponseEntity<Void> createPage(@Validated @RequestBody Page page) {
		page.setId(null);
		Page newPage = pageRepository.save(page);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}")
				.buildAndExpand(newPage.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Page> updatePage(
			@PathVariable String id,
			@Validated @RequestBody Page pageDetails) throws ResourceNotFoundException {
		pageRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Page not found with id " + id) );
		pageRepository.save(pageDetails);
		
		return new ResponseEntity<Page>(pageDetails, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Page> deletePage(@PathVariable String id) throws ResourceNotFoundException {
		Page deletedPage = pageRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Page not found with id " + id) );
		pageRepository.deleteById(id);
		
		return new ResponseEntity<Page>(deletedPage, HttpStatus.OK);
	}
}
