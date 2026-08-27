package com.example.secure_web_platform_v2;

public class LoginRequest {
	//attributes
	private String username;
	private String password;
	
	//constructors
	public LoginRequest() {
	}
	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	//getters
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
}
