package com.deep.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep.study.config.TokenService;
import com.deep.study.entity.Student;
import com.deep.study.request.JwtRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserDetailsService userDetailsService;

	@GetMapping(path = "/publicApi", produces = { "application/text" })
	public ResponseEntity<String> publicAccess() {

		return new ResponseEntity<String>("login", HttpStatus.OK);

	}

	@GetMapping(path = "/secureApi", produces = { "application/json" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Student> secureApi() {

		return new ResponseEntity<Student>(new Student(), HttpStatus.OK);

	}

	@PostMapping(path = "/login", consumes={"application/json"}, produces = { "application/text" })
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = tokenService.generateWebToken("Test");
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
