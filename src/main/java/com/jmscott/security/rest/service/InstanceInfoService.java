package com.jmscott.security.rest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InstanceInfoService {
	private static final String HOSTNAME = "HOSTNAME";
	
	private static final String DEFAULT_ENV_INSTANCE_GUID = "LOCAL";
	
	@Value("${" + HOSTNAME + ":" + DEFAULT_ENV_INSTANCE_GUID + "}")
	private String hostName;
	
	public String retrieveInstanceInfo() {
		return hostName.substring(hostName.length()-5);
	}
	
}
