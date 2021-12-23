package com.codingdojo.javaexam.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.javaexam.models.Student;
import com.codingdojo.javaexam.models.YogaClass;


@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
	List<Student> findAll();
//	List<Student> findAllByClass_id(Long class_id);
//	Optional<Student> findByEmail(String email);
	List<Student> findByClassesNotContains(YogaClass yogaClass);

}
