package com.example.secure_web_platform_v2;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "login_attempts")

public class LoginAttempt {
	
	//attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attempt_id", unique = true, nullable = false)
	private long attemptId;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	private boolean loginSuccessful;
	private String ipAddress;
	private LocalDateTime attemptTime;
	
	//constructors
	public LoginAttempt() {
	}
	public LoginAttempt(User user, boolean wasSuccessful) {
		this.user = user;
		this.loginSuccessful = wasSuccessful;
		this.attemptTime = LocalDateTime.now();
	}
	
	//getters
	public long getAttemotId() {
		return this.attemptId;
	}
	public User getUserId() {
		return this.user;
	}
	public boolean wasSuccessful() {
		return this.loginSuccessful;
	}
	public String getIPAddress() {
		return this.ipAddress;
	}
	public LocalDateTime getDateTime() {
		return this.attemptTime;
	}
	
	//setters
	
	
}
