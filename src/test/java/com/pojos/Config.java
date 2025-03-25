package com.pojos;

import java.util.Map;

public class Config {
	private Map<String , Environment> environment;

	public Map<String , Environment> getEnvironment() {
		return environment;
	}

	public void setEnvironment(Map<String , Environment> environment) {
		this.environment = environment;
	}
}
