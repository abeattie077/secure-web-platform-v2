package com.example.secure_web_platform_v2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

	//attributes
	private final UserService userService;
	
	//constructor
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//POST Method
	@PostMapping("/register")
	public User createNewUser(@RequestBody RegisterRequest request) {
		return userService.newUser(request);
	}
	
	//LOGIN Method
	@PostMapping("/login")
	public String userLogin (@RequestBody LoginRequest loginRequest, HttpServletRequest httpRequest) {
		String result = "";
		String addressIP = httpRequest.getRemoteAddr();
		boolean loginResult = userService.login(loginRequest, addressIP);
		if (loginResult) {
			result = "Login Successful";
		}
		else {
			if (userService.getIsUsernameLocked()) {
				result = "Too many failed attempts. Try again later.";
			}
			else {
				result = "Invalid username or password";
			}
		}
		return result;
	}
}
