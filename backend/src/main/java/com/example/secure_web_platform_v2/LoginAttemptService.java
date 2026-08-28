package com.example.secure_web_platform_v2;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {
	
	//attributes
	private final LoginAttemptRepository loginAttemptRepository;
	private final IncidentService incidentService;
	
	//constructor
	public LoginAttemptService (LoginAttemptRepository loginAttemptRepository, IncidentService incidentService) {
		this.loginAttemptRepository =  loginAttemptRepository;
		this.incidentService = incidentService;
	}
	
	//save new login attempt
	public LoginAttempt newAttempt(LoginAttempt attempt) {
		LoginAttempt savedAttempt = loginAttemptRepository.save(attempt);
		if (!(savedAttempt.wasSuccessful())) {
			String severityLevel = "high";
			if (this.isValidAttempt(savedAttempt.getUsernameUsed())) {
				severityLevel = "low";
			}
			Incident newIncident = new Incident(savedAttempt, severityLevel);
			incidentService.newIncident(newIncident);
		}
		return savedAttempt;
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
