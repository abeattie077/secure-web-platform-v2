package com.example.secure_web_platform_v2;

public class RegisterRequest {

	//attributes
	private String firstName;
	private String lastName;
	private String username;
	private String password;
		
		
	//constructors
	public RegisterRequest() {
	}
	public RegisterRequest(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
		
	//getters
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
		
}
