package com.codingdojo.javaexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.javaexam.models.Join;
import com.codingdojo.javaexam.repositories.JoinRepository;



@Service
public class JoinService {
	@Autowired
	private JoinRepository joinRepo;
	

    public List<Join> allJoins(){
    	return (List<Join>) joinRepo.findAll();
    }
    
	///make a join many to many
	public Join makeJoin(Join join) {
		return joinRepo.save(join);
	}
	
	public boolean findBoth(Long student_id, Long class_id) {
		Optional<Join> optionaljoin = joinRepo.findByStudentAndStudentInClass(student_id,class_id);
		if(optionaljoin.isPresent()) {
			return true;
		} else {
			return false;
		} 
	}
	
	public void deleteJoin(Long id) {
		joinRepo.deleteById(id);
	}
}
