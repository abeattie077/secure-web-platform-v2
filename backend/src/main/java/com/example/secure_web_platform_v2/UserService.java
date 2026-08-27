package com.example.secure_web_platform_v2;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	//attributes
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	private final LoginAttemptService loginAttemptService;
	private boolean usernameLockedStatus;
	
	//constructors
	public UserService (UserRepository userRepository, BCryptPasswordEncoder encoder, LoginAttemptService loginAttemptService) {
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.loginAttemptService = loginAttemptService;
		this.usernameLockedStatus = false;
	}
	
	//save new user
	public User newUser(RegisterRequest request) {
		String hashedPW = encoder.encode(request.getPassword());
		User user = new User(request.getFirstName(), request.getLastName(), request.getUsername());
		user.setHashedPW(hashedPW);
		return userRepository.save(user);
	}
	
	//find user
	public Optional<User> findByUsername(String username) {
		Optional<User> target = userRepository.findByUsername(username);
		return target;
	}
	
	//login
	public boolean login(LoginRequest request, String addressIP) {
		boolean validLogin = false;
		boolean isValidUser = false;
		boolean validAttempt = loginAttemptService.isValidAttempt(request.getUsername());
		this.usernameLockedStatus = !validAttempt;
		User target = null;
		Optional <User> optionalTarget = this.findByUsername(request.getUsername());
		if (optionalTarget.isPresent()) {
			target = optionalTarget.get();
			isValidUser = true;
		}
		if (isValidUser && validAttempt) {
			validLogin = encoder.matches(request.getPassword(), target.getPassword());
		}
		LoginAttempt newAttempt = new LoginAttempt(target, validLogin, request.getUsername(), addressIP);
		loginAttemptService.newAttempt(newAttempt);
		return validLogin;
	}
	
	//login lockout
	public boolean getIsUsernameLocked() {
		return usernameLockedStatus;
	}
	
}
