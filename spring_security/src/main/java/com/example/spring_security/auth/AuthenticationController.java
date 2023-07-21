package com.example.spring_security.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService = new AuthenticationService();

	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> regsiter(@RequestBody RegisterRequest request){
		
		return ResponseEntity.ok(authenticationService.register(request));
	}
	
	

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> regsiter(@RequestBody AuthenticationRequest request){
		
		return ResponseEntity.ok(authenticationService.authenticate(request));

	}
}
