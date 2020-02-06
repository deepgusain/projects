package com.deep.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.deep.service.model.EmployeeDTO;

@RestController
public class TestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	@GetMapping(path="/find/{id}", produces={"application/json"})
	public EmployeeDTO testMethod(@PathVariable("id") String id ){
		EmployeeDTO dto = new EmployeeDTO("Deepak",1);
		LOGGER.info("testing logger");
		return dto;
	}

}
