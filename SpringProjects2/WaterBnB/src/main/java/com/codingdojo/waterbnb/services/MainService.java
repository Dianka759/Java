package com.codingdojo.waterbnb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.waterbnb.repositories.PoolRepository;
import com.codingdojo.waterbnb.repositories.UserRepository;

@Service
public class MainService {
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	PoolRepository poolRepo;
	
	
	
}
