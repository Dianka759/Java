package com.codingdojo.languages.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.languages.models.Language;
import com.codingdojo.languages.repositories.languageRepository;

@Service
public class languageService {
	
	@Autowired
	languageRepository langRepository;
	
	public List<Language> allLanguages() {
		return langRepository.findAll();
	}
	
	public Language findLanguage(Long id) {
		Optional<Language> optionalLanguage = langRepository.findById(id);
		if (optionalLanguage.isPresent()) {
			return optionalLanguage.get();
		} else {
			return null;
		}
	}
	
	// creates an language
    public Language createLanguage(Language language) {
        return langRepository.save(language);
    }
    
	// creates an language
    public Language updateLanguage(Language language) {
        return langRepository.save(language);
    }
    
    public void deleteLanguage(long id) {
    	langRepository.deleteById(id);
    }
	
}
