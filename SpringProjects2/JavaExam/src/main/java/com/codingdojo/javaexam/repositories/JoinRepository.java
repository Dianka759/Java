package com.codingdojo.javaexam.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.javaexam.models.Join;
import com.codingdojo.javaexam.models.Student;
import com.codingdojo.javaexam.models.YogaClass;

@Repository
public interface JoinRepository extends CrudRepository<Join, Long> {
	Optional<Join> findById(int id);
	
//	Optional<Join> findByUser_idAndMis_id(Long user_id, Long mischief_id);
//	Optional<Join> findByStudentAndStudentInClass(Long student_id, Long class_id);
//	Optional<Join> findByClassAndStudent(YogaClass yogaclass, Student student);
//	
////	@Modifying
//    @Query(value="Select * FROM students_in_class s WHERE s.student_id= ?student_id AND s.class_id= ?class_id", nativeQuery=true)
//    Optional<Join> findByStudent_idAndClass_id(Long student_id, Long class_id);

}
