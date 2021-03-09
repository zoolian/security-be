package com.jmscott.security.rest.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.DBRef;

//@QueryEntity
@Document(collection = "person")
public class User extends Person {
	
	@Indexed(unique = true)
	private String username;
	
	private boolean enabled = true;
	
	// don't cascade save roles. on the front end, there's no reason to hold all the fields ...
	// they should be changed in the role manager
	@org.springframework.data.mongodb.core.mapping.DBRef
	private Collection<Role> roles = new ArrayList<Role>();

	public User() {
		super();
	}

	public User(String firstName, String lastName, String email) {
		super(firstName, lastName, email);
	}

	public User(String firstName, String lastName, String email, LocalDate dob, String username, boolean enabled, Collection<Role> roles) {
		super(firstName, lastName, email, dob);
		this.username = username;
		this.enabled = enabled;
		this.roles = roles;
	}

	public User(String firstName, String lastName, String email, String username, boolean enabled) {
		super(firstName, lastName, email);
		this.username = username;
		this.enabled = enabled;
	}

	@JsonIgnore
	public Collection<DBRef> getDBRefRoles() {
		Collection<DBRef> dbRefRoles = new ArrayList<DBRef>();
		for(Role r : this.getRoles()) {
			dbRefRoles.add(new DBRef("role", new ObjectId(r.getId())));
		}
		return dbRefRoles;
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
		return "User [username=" + username + ", enabled=" + enabled + ", roles=" + roles + ", getId()=" + getId()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getEmail()="
				+ getEmail() + ", getDob()=" + getDob() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}	
	
}
