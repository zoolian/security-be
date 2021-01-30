package com.jmscott.security.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Phone {

	@Id
	private long id;
	
	private String country = "+1";
	private String area;
	
	@Indexed(unique = true)
	private String subscriber;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public String toString() {
		return country + "-" + area + "-" + subscriber.substring(0, 3) + "-" + subscriber.substring(3, subscriber.length());
	}
	
	
}
