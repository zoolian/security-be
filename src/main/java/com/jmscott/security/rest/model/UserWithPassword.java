package com.jmscott.security.rest.model;

public class UserWithPassword extends User {

	//private String username;
	
	private String password;
	
//	private boolean enabled;
//
//	private Collection<Role> roles = new ArrayList<Role>();

//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}

	public UserWithPassword(String firstName, String lastName, String email, String username, String password, boolean enabled) {
		super(firstName, lastName, email, username, enabled);
		this.password = password;
	}
	
	public UserWithPassword() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserWithPassword [password=" + password + ", getUsername()=" + getUsername() + ", isEnabled()="
				+ isEnabled() + ", getRoles()=" + getRoles() + ", toString()=" + super.toString() + ", getId()="
				+ getId() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getEmail()="
				+ getEmail() + ", getDob()=" + getDob() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

//	public boolean isEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}
//
//	public Collection<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Collection<Role> roles) {
//		this.roles = roles;
//	}
	
}
