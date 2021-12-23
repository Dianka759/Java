package com.codingdojo.javaexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.javaexam.models.Join;
import com.codingdojo.javaexam.models.Student;
import com.codingdojo.javaexam.models.YogaClass;
import com.codingdojo.javaexam.repositories.JoinRepository;



@Service
public class JoinService {
	@Autowired
	private JoinRepository joinRepo;
	

    public List<Join> allJoins(){
    	return (List<Join>) joinRepo.findAll();
    }
    
	///make a join many to man
	public Join makeJoin(Join join) {
		return joinRepo.save(join);
	}
//	
//	public boolean findBoth(Long student_id, Long class_id) {
//		Optional<Join> optionaljoin = joinRepo.findByStudentAndStudentInClass(student_id,class_id);
//		if(optionaljoin.isPresent()) {
//			return true;
//		} else {
//			return false;
//		} 
//	}
//	
	
//	public Join findBoth2(Long student_id, Long class_id) {
//		Optional<Join> optionaljoin = joinRepo.findByStudent_idAndClass_id(student_id,class_id);
//		if(optionaljoin.isPresent()) {
//			return  optionaljoin.get();
//		} else {
//			return null;
//		} 
//	}
//	
	
	
//	public boolean findBoth3(YogaClass yogaclass, Student student) {
//		Optional<Join> optionaljoin = joinRepo.findByClassAndStudent(yogaclass,student);
//		if(optionaljoin.isPresent()) {
//			return true;
//		} else {
//			return false;
//		} 
//	}
	
	public void deleteJoin(Long id) {
		joinRepo.deleteById(id);
	}
	
	
	  // retrieves a join by id
  public Join findJoinById(Long id) {
      Optional<Join> optionaljoin = joinRepo.findById(id);
      if(optionaljoin.isPresent()) {
          return optionaljoin.get();
      } else {
          return null;
      }
  }
	
	
}
