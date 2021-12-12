package com.codingdojo.dojosandninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.dojosandninjas.models.Ninja;
import com.codingdojo.dojosandninjas.repositories.NinjasRepository;

@Service
public class NinjasService {
	
	@Autowired
	NinjasRepository ninjasRepository;
	
	 // returns all the ninjas
    public List<Ninja> allNinjas() {
        return ninjasRepository.findAll();
    }
    
    // creates a ninja
    public Ninja createNinja(Ninja ninjas) {
        return ninjasRepository.save(ninjas);
    }
      
    // retrieves a ninja by id
    public Ninja findNinjaById(Long id) {
        Optional<Ninja> optionalninjas = ninjasRepository.findById(id);
        if(optionalninjas.isPresent()) {
            return optionalninjas.get();
        } else {
            return null;
        }
    }
	
    public Ninja updateNinja(Ninja ninjas) {
    	return ninjasRepository.save(ninjas);
    }
    
    public void deleteNinja(long id) {
    	ninjasRepository.deleteById(id);
    }
}

