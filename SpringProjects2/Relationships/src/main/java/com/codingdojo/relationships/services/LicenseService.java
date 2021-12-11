package com.codingdojo.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.relationships.models.License;
import com.codingdojo.relationships.repositories.LicenseRepository;


@Service
public class LicenseService {

	@Autowired
	LicenseRepository licenseRepository;
    
    // returns all the licenses
    public List<License> allLicenses() {
        return licenseRepository.findAll();
    }
    
    // creates an expense
    public License createLicense(License license) {
        return licenseRepository.save(license);
    }
      
    // retrieves an Expense by Id
    public License findLicenseById(Long id) {
        Optional<License> optionallicense = licenseRepository.findById(id);
        if(optionallicense.isPresent()) {
            return optionallicense.get();
        } else {
            return null;
        }
    }
	
    public License updateLicense(License license) {
    	return licenseRepository.save(license);
    }
    
    public void deleteLicense(long id) {
    	licenseRepository.deleteById(id);
    }
}
