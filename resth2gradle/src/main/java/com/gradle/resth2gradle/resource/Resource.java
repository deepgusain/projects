package com.gradle.resth2gradle.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gradle.resth2gradle.entity.Student;
import com.gradle.resth2gradle.repository.StudentRepository;


@RestController
@RequestMapping("/check")
public class Resource {
	
	@Autowired
	StudentRepository respository;
	
	
	 @GetMapping(path="/student/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Student> getStudent(@PathVariable("id") String id ) {
		 System.out.println("id valie :"+id);

	        return new ResponseEntity<Student>(respository.getStudent(id), HttpStatus.OK);
	    }
	 
	 @PostMapping(path="/student", consumes=MediaType.APPLICATION_JSON_VALUE, 
	    		produces=MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> createStudent(@RequestBody Student request) {
		 
		 respository.insert(request);
		 return new ResponseEntity<String>("Success", HttpStatus.OK);
		 
	 }

	
	

}
