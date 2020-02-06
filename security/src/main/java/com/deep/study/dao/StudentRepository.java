package com.deep.study.dao;

import org.springframework.data.repository.CrudRepository;

import com.deep.study.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{

}
