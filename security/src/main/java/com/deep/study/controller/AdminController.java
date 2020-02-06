package com.deep.study.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep.study.entity.Student;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping(path="/publicApi", produces={"application/text"})
	public ResponseEntity<String> publicAccess(){
		
		return new ResponseEntity<String>("login",HttpStatus.OK);
		
	}
	
	@GetMapping(path="/secureApi", produces={"application/json"})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Student> secureApi(){
		
		return new ResponseEntity<Student>(new Student(),HttpStatus.OK);
		
	}

}
