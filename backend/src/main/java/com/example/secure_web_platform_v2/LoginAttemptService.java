package com.example.secure_web_platform_v2;

import java.time.LocalDateTime;
import java.util.List;

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
	
	//check to see if is valid login attempt
	public boolean isValidAttempt(String username) {
		boolean result = true;
		int failedAttempCount = 0;
		List<LoginAttempt> loginAttemptsInLastFifteenMinutes = loginAttemptRepository.findByUsernameUsedAndAttemptTimeAfterOrderByAttemptTimeDesc(username, (LocalDateTime.now()).minusMinutes(15));
		int listLength = loginAttemptsInLastFifteenMinutes.size();
		for (int i = 0; i<listLength; i++) {
			if (!(loginAttemptsInLastFifteenMinutes.get(i).wasSuccessful())) {
				failedAttempCount++;
				if (failedAttempCount>=5) {
					result = false;
					break;
				}
			}
			else {
				break;
			}
		}
		return result;
	}
}
