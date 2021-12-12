package com.codingdojo.dojosandninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.dojosandninjas.models.Dojo;
import com.codingdojo.dojosandninjas.repositories.DojosRepository;

@Service
public class DojosService {
	
	@Autowired
	DojosRepository dojosRepository;

	 // returns all the dojos
    public List<Dojo> allDojos() {
        return dojosRepository.findAll();
    }
    
    // creates a dojo
    public Dojo createDojo(Dojo dojo) {
        return dojosRepository.save(dojo);
    }
      
    // retrieves a dojo by id
    public Dojo findDojoById(Long id) {
        Optional<Dojo> optionaldojo = dojosRepository.findById(id);
        if(optionaldojo.isPresent()) {
            return optionaldojo.get();
        } else {
            return null;
        }
    }
	
    public Dojo updateDojo(Dojo dojo) {
    	return dojosRepository.save(dojo);
    }
    
    public void deleteDojo(long id) {
    	dojosRepository.deleteById(id);
    }
}
