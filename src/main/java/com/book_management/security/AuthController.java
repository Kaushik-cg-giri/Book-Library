package com.book_management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book_management.entity.AuthRequest;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private final JWTService jwstService;
	
	public AuthController(JWTService jwstService) {
		super();
		this.jwstService = jwstService;
	}
	

	@PostMapping("/api/authentication")
	public String tokenAuthentication(@RequestBody AuthRequest authRequest) {
	
		try {
			
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getUserPassword()));
		
		}
		
		catch (Exception e) {
			
			throw e;
		}
		
		return jwstService.generateToken(authRequest.getUserName());
			
	}

}
