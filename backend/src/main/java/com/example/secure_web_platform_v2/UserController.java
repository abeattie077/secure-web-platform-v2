package com.example.secure_web_platform_v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping("/post")
	public User createNewUser(@RequestBody RegisterRequest request) {
		return userService.newUser(request);
	}
	
}
