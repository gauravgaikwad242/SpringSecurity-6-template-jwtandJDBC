package blog.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blog.com.dto.LoginRequest;
import blog.com.dto.RegisterRequest;
import blog.com.model.User;
import blog.com.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blog.com.dto.LoginRequest;
import blog.com.dto.RegisterRequest;
import blog.com.model.User;
import blog.com.repository.UserRepository;
import blog.com.security.JwtProvider;

@Service
public class AuthService {

	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	public void signup(RegisterRequest registerRequest) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(this.encodePassword(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		userRepository.save(user);

	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	public String login(LoginRequest loginRequest) {

		Authentication authenticate =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		return jwtProvider.generateToken(authenticate);
	}

	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
				getContext().getAuthentication().getPrincipal();
		return Optional.of(principal);
	}

}
