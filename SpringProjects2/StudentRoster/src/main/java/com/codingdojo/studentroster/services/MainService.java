package com.codingdojo.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.studentroster.models.Dorm;
import com.codingdojo.studentroster.models.Student;
import com.codingdojo.studentroster.repositories.DormRepository;
import com.codingdojo.studentroster.repositories.StudentRepository;

@Service
public class MainService {
	@Autowired
	DormRepository dormRepo;
	@Autowired
	StudentRepository studentRepo;

	// returns all the dorms
	public List<Dorm> allDorms() {
		return dormRepo.findAll();
	}
	
	public List<Student> studentsWithNoDorms() {
		return studentRepo.findByDormIsNull();
	}

	// returns all the students
	public List<Student> allStudents() {
		return studentRepo.findAll();
	}

	// creates a dorm
	public Dorm createDorm(Dorm dorm) {
		return dormRepo.save(dorm);
	}

	// creates a student
	public Student createStudent(Student student) {
		return studentRepo.save(student);
	}

	// retrieves a dorm by id
	public Dorm findDormById(Long id) {
		Optional<Dorm> optionaldorm = dormRepo.findById(id);
		if (optionaldorm.isPresent()) {
			return optionaldorm.get();
		} else {
			return null;
		}
	}

	// retrieves a student by id
	public Student findStudentById(Long id) {
		Optional<Student> optionalstudent = studentRepo.findById(id);
		if (optionalstudent.isPresent()) {
			return optionalstudent.get();
		} else {
			return null;
		}
	}

	public Dorm updateDorm(Dorm dorm) {
		return dormRepo.save(dorm);
	}

	public void deleteDorm(long id) {
		dormRepo.deleteById(id);
	}
	
	public Student updateStudent(Student student) {
		return studentRepo.save(student);
	}

	public void deleteStudent(long id) {
		studentRepo.deleteById(id);
	}
	
	
	public Student addStudentByIdToDorm(Long id, Dorm dorm) {
		Student student = findStudentById(id);
		student.setDorm(dorm);
		return studentRepo.save(student);
		
	}
	
	public Student removeStudentByIdFromDorm(Long id) {
		Student student = findStudentById(id);
		student.setDorm(null);
		return studentRepo.save(student);
	}
}
