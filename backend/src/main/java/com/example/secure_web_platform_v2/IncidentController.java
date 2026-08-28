package com.example.secure_web_platform_v2;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "http://localhost:5173")

public class IncidentController {
	//Incident Service
	private final IncidentService incidentService;
	
	//Constructor
	public IncidentController(IncidentService incidentService) {
		this.incidentService = incidentService;
	}
	
	//GetMapping
	@GetMapping("/{username}")
	public List<Incident> getIncidentsByUsername(@PathVariable String username){
		return incidentService.getIncidentsByUsername(username);
	}
}
