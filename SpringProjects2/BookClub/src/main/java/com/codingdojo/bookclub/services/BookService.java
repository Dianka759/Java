package com.codingdojo.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	
	// returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    
    // creates a book
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
      
    // retrieves a book by id
    public Book findBookById(Long id) {
        Optional<Book> optionalbook = bookRepository.findById(id);
        if(optionalbook.isPresent()) {
            return optionalbook.get();
        } else {
            return null;
        }
    }
	
    //updates a book
    public Book updateBook(Book book) {
    	return bookRepository.save(book);
    }
    
    //deletes a book
    public void deleteBook(long id) {
    	bookRepository.deleteById(id);
    }
}