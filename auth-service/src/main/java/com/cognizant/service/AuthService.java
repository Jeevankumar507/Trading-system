package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.config.CustomUserDetailsService;
import com.cognizant.entity.UserCredential;
import com.cognizant.repository.UserCredentialRepository;

@Service
public class AuthService {

	@Autowired
	private UserCredentialRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	

	
	public String saveUser(UserCredential credential) {
		boolean userExists = repository.existsByName(credential.getName());
		 
	    if (userExists) {
	        return "User already registered";
	    }
 
		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
		repository.save(credential);
		return "User added to the system";
	}
	
	public String generateToken(String username) {
		
		return jwtService.generateToken(username);
	}
	
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
}
