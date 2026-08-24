package com.example.secure_web_platform_v2;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	//attributes
	private final UserRepository userRepository;
	
	//constructors
	public UserService (UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//save new user
	public User newUser(User user) {
		return userRepository.save(user);
	}
	
	//find user
	public Optional<User> findByUsername(String username) {
		Optional<User> target = userRepository.findByUsername(username);
		return target;
	}
	
	//login
	public boolean login(String username) {
		Optional <User> target = this.findByUsername(username);
		if (target.isEmpty()){
			return false;
		}
		else {
			String pw = null;
			return true;
		}
	}
	
}
