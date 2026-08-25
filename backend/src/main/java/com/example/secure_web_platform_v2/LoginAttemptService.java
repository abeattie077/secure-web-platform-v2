package com.example.secure_web_platform_v2;

import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {
	
	//attributes
	private final LoginAttemptRepository loginAttemptRepository;
	
	
	//constructor
	public LoginAttemptService (LoginAttemptRepository loginAttemptRepository) {
		this.loginAttemptRepository =  loginAttemptRepository;
	}
	
	//save new login attempt
	public LoginAttempt newAttempt(LoginAttempt attempt) {
		return loginAttemptRepository.save(attempt);
	}
}
