package com.codingdojo.javaexam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.javaexam.models.YogaClass;

@Repository
public interface YogaClassRepository extends CrudRepository<YogaClass, Long> {
	List<YogaClass> findAll();
}
