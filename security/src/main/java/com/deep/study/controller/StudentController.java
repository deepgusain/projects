package com.deep.study.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep.study.entity.Student;
import com.deep.study.service.CustomUserDetailsService;

@RestController
@RequestMapping("/user")
public class StudentController {
	
	@Autowired
	CustomUserDetailsService service;

	@GetMapping(path="/publicApi", produces={"application/text"})
	public ResponseEntity<String> publicAccess(){
		
		return new ResponseEntity<String>("login",HttpStatus.OK);
		
	}
	
	@GetMapping(path="/secureApi", produces={"application/json"})
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Student> secureApi(){
		
		return new ResponseEntity<Student>(new Student(),HttpStatus.OK);
		
	}
	
	@GetMapping(path="/secureApiForAll", produces={"application/json"})
	public ResponseEntity<Student> secureApiforAll(){
		
		return new ResponseEntity<Student>(new Student(),HttpStatus.OK);
		
	}
	
	@PutMapping(path="/updatePassword/{name}", produces={"application/text"})
	public ResponseEntity<String> updateUserPass(@PathVariable("name") String name){
		
		service.updatePassword(name);
		return new ResponseEntity<String>("Success",HttpStatus.OK);
		
	}

}
