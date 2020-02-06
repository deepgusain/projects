package com.test.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.rest.EmployeeDTO;

@RestController
public class TestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	@Api
	@GetMapping(path="/find/{id}", produces={"application/xml","application/json"})
	public EmployeeDTO testMethod(@PathVariable("id") String id ){
		EmployeeDTO dto = new EmployeeDTO("Deepak",1);
		LOGGER.info("testing logger");
		return dto;
	}
	
	/*@GetMapping(path="/find", params={"id"})
	public ResponseEntity<EmployeeDTO> findEmployee(int id){
		
		
		ResponseEntity<EmployeeDTO> response = new ResponseEntity<EmployeeDTO>(new EmployeeDTO("test", id), HttpStatus.OK);
		
		return response;
		
	}
	
*/	

}
