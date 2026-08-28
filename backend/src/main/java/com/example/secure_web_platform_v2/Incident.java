package com.example.secure_web_platform_v2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "incidents")

public class Incident {
	//attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "incident_id", unique = true, nullable = false)
	private long incidentID;
	@OneToOne
	@JoinColumn(name = "login_attempt_id")
	private LoginAttempt loginAttempt;
	private String severityLevel;
	
	//constructors
	public Incident() {
	}
	public Incident (LoginAttempt loginAttempt, String severityLevel) {
		this.loginAttempt = loginAttempt;
		this.severityLevel = severityLevel;
	}
	
	//getters
	public long getIncidentID() {
		return this.incidentID;
	}
	public LoginAttempt getLoginAttempt() {
		return this.loginAttempt;
	}
	public String getSeverityLevel() {
		return this.severityLevel;
	}
	//setters
}
