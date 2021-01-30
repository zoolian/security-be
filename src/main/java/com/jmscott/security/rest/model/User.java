package com.jmscott.security.rest.model;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//@QueryEntity
@Document(collection = "person")
public class User extends Person {
	
	@Indexed(unique = true)
	private String username;
	
	private boolean enabled;
	
	// don't cascade save roles. on the front end, there's no reason to hold all the fields ...
	// they should be changed in the role manager
	@DBRef
	private Collection<Role> roles = new ArrayList<Role>();

	public User() {
		super();
	}

	public User(String firstName, String lastName, String email, int age) {
		super(firstName, lastName, email, age);
	}

	public User(String firstName, String lastName, String email, int age, String username, boolean enabled, Collection<Role> roles) {
		super(firstName, lastName, email, age);
		this.username = username;
		this.enabled = enabled;
		this.roles = roles;
	}

	public User(String firstName, String lastName, String email, int age, String username, boolean enabled) {
		super(firstName, lastName, email, age);
		this.username = username;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User2 [username=" + username + ", enabled=" + enabled + ", roles=" + roles + ", getId()=" + getId()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getEmail()="
				+ getEmail() + ", getAge()=" + getAge() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}	
	
}