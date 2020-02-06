package com.gradle.resth2gradle.repository;


import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gradle.resth2gradle.entity.Student;

@Transactional
@Repository
public class StudentRepositoryImpl implements StudentRepository{
	
	/*@Autowired
	private JdbcTemplate jdbcTemplate;*/
	
	/*@Autowired
	private HibernateTemplate hibernateTemplate;*/
	
	@Autowired
    private SessionFactory sessionFactory;
	
	/*public Student findById(long id) {
	    return jdbcTemplate.queryForObject("select * from student where id=?", new Object[] {
	            id
	        },
	        new BeanPropertyRowMapper < Student > (Student.class));
	}

	@Override
	public Student getStudent(String id) {
		return jdbcTemplate.queryForObject("select * from student where id=?", new Object[] {
	            id
	        },
	        new BeanPropertyRowMapper < Student > (Student.class));
	}
	
	public int insert(Student student) {
	    return jdbcTemplate.update("insert into student (id, name) " + "values(?,  ?)",
	        new Object[] {
	            student.getId(), student.getName()
	        });
	}*/
	
	
	@Override
	public Student getStudent(String id) {
		Session session = this.sessionFactory.getCurrentSession();
        return session.get(Student.class, id);
	}

	@Override
	public int insert(Student student) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(student);
		
		return 1;
		
	}

}
