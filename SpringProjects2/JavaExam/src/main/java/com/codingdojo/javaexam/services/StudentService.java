package com.codingdojo.javaexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.javaexam.models.Student;
import com.codingdojo.javaexam.models.User;
import com.codingdojo.javaexam.repositories.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepo;
	
	// returns all the students
    public List<Student> allStudents() {
        return studentRepo.findAll();
    }
    
    // creates a student
    public Student createStudent(Student c) {
        return studentRepo.save(c);
    }
      
    // retrieves a student by id
    public Student findStudentById(Long id) {
        Optional<Student> optionalstudent = studentRepo.findById(id);
        if(optionalstudent.isPresent()) {
            return optionalstudent.get();
        } else {
            return null;
        }
    }
	
    //updates a student
    public Student updateStudent(Student c) {
    	return studentRepo.save(c);
    }
    
    //deletes a student
    public void deleteStudent(long id) {
    	studentRepo.deleteById(id);
    }
    
    //this will get the email 
    public Student checkByEmail(Student student) {
    Optional<Student> potentialstudent = studentRepo.findByEmail(student.getEmail());
    //if the email doesnt exist in the db, returns error message.
    if(!potentialstudent.isPresent()) {
        return null;
    }
    return potentialstudent.get();
    }

}
