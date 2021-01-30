package com.jmscott.security.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Password {
	@Id
	private String id;
	
	private String personId;
	
	private String password;

	public Password(String personId, String password) {
		this.personId = personId;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return personId;
	}

	public void setUserId(String personId) {
		this.personId = personId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Password [id=" + id + ", personId=" + personId + ", password=" + password + "]";
	}
	
	
}
