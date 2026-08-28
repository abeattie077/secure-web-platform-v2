package com.example.secure_web_platform_v2;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class IncidentService {
	
	//repository
	private final IncidentRepository incidentRepository;
	
	//controller
	public IncidentService (IncidentRepository incidentRepository) {
		this.incidentRepository = incidentRepository;
	}
	
	//getters
	public List<Incident> getAllIncidents(){
		return this.incidentRepository.findAllByOrderByIncidentIDDesc();
	}
	public List<Incident> getIncidentsByUsername(String username){
		return this.incidentRepository.findByLoginAttemptUserUsernameOrderByIncidentIDDesc(username);
	}
	
	//add new incident
	public Incident newIncident(Incident input) {
		return incidentRepository.save(input);
	}
	
}
