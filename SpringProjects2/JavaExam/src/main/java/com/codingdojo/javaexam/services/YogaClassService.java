package com.codingdojo.javaexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.javaexam.models.YogaClass;
import com.codingdojo.javaexam.repositories.YogaClassRepository;

@Service
public class YogaClassService {
	
	@Autowired
	YogaClassRepository classRepo;
	
	// returns all the classs
    public List<YogaClass> allClasses() {
        return classRepo.findAll();
    }
    
    // creates a class
    public YogaClass createClass(YogaClass c) {
        return classRepo.save(c);
    }
      
    // retrieves a class by id
    public YogaClass findClassById(Long id) {
        Optional<YogaClass> optionalclass = classRepo.findById(id);
        if(optionalclass.isPresent()) {
            return optionalclass.get();
        } else {
            return null;
        }
    }
	
    //updates a class
    public YogaClass updateClass(YogaClass c) {
    	return classRepo.save(c);
    }
    
    //deletes a class
    public void deleteClass(long id) {
    	classRepo.deleteById(id);
    }
    
	

}
