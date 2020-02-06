package com.deep.study.repository;

import org.springframework.data.repository.CrudRepository;

import com.deep.study.entity.User;

import java.lang.String;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByName(String name);

}
