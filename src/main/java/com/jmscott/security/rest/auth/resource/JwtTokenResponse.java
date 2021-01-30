package com.jmscott.security.rest.auth.resource;

import java.io.Serializable;
import java.util.Date;

public class JwtTokenResponse implements Serializable {

  private static final long serialVersionUID = 8317676219297719109L;

  private final String token;
  private final Date date;

    public JwtTokenResponse(String token, Date date) {
        this.token = token;        
        this.date = date;
    }

    public String getToken() {
        return this.token;
    }
    
    public Date getDate() {
    	return this.date;
    }
}