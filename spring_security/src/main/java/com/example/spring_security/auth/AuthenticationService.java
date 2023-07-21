package com.example.spring_security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_security.config.JwtService;
import com.example.spring_security.entity.User;
import com.example.spring_security.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	@Autowired
	JwtService jwtService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;

	public AuthenticationResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setEmail(request.getEmail());
		user.setFiratname(request.getFname());
		user.setLastname(request.getLname());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		userRepository.save(user);
		var token = jwtService.genearteToken(user);
		AuthenticationResponse a = new AuthenticationResponse(token);
		return a;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		// TODO Auto-generated method stub
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
		var token = jwtService.genearteToken(user);

		return new AuthenticationResponse(token);
	}

}
