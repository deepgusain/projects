package com.gradle.resth2gradle.repository;

import com.gradle.resth2gradle.entity.Student;

public interface StudentRepository {
	
	Student getStudent(String id);
	
	public int insert(Student student) ;

}
