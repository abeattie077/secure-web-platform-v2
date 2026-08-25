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
	
	//constructors
	public UserService (UserRepository userRepository, BCryptPasswordEncoder encoder, LoginAttemptService loginAttemptService) {
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.loginAttemptService = loginAttemptService;
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
	public boolean login(String username, String password) {
		boolean validLogin = false;
		boolean isValidUser = false;
		User target = null;
		Optional <User> optionalTarget = this.findByUsername(username);
		if (optionalTarget.isPresent()) {
			target = optionalTarget.get();
			isValidUser = true;
		}
		if (isValidUser) {
			validLogin = encoder.matches(password, target.getPassword());
		}
		LoginAttempt newAttempt = new LoginAttempt(target, validLogin, username);
		loginAttemptService.newAttempt(newAttempt);
		return validLogin;
	}
	
}
