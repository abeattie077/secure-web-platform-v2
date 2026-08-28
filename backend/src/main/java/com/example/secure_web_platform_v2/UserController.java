package com.example.secure_web_platform_v2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

	//attributes
	private final UserService userService;
	
	//constructor
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//POST Method
	@PostMapping("/register")
	public ResponseEntity<String> createNewUser(@RequestBody RegisterRequest request) {
		if (userService.usernameExists(request.getUsername())) {
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("The requested username is already in use. Please enter a different username.");
		}
		else {
			userService.newUser(request);
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body("Account created successfully.");
		}
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
