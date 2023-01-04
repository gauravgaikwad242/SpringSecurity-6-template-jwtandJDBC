package blog.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blog.com.dto.LoginRequest;
import blog.com.dto.RegisterRequest;
import blog.com.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class BlogRestController {
	
	
	@Autowired AuthService authService;
	
	@GetMapping("/")
	public String handler() {
		return "hi";
	}
	
	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
	
	return new ResponseEntity(HttpStatus.OK);
	}
	
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest) {
		
		
		return authService.login(loginRequest);
	}

}
