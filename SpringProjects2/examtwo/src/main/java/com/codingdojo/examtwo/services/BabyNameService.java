package com.codingdojo.examtwo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.examtwo.models.BabyName;
import com.codingdojo.examtwo.repository.BabyNameRepository;



@Service
public class BabyNameService {
	@Autowired
	private BabyNameRepository babynameRepo;

	
	public List<BabyName> allBabyName() {
		return babynameRepo.findAll();
	}
	
	public BabyName saveBabyName(BabyName c) {
		return babynameRepo.save(c);
	}
	
	
	public BabyName getOneBabyName(Long id) {
		Optional<BabyName> optionalBabyName = babynameRepo.findById(id);
		if (optionalBabyName.isPresent()) {
			return optionalBabyName.get();
		} else {
			return null;
		}
	}

	public BabyName updateBabyName(BabyName babyname) {
		return babynameRepo.save(babyname);
	}
	
	public void deleteBabyName(Long id) {
		babynameRepo.deleteById(id);
	}
	
}
