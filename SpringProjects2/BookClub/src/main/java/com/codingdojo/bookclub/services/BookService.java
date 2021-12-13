package com.codingdojo.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.repositories.BookRepository;
import com.codingdojo.bookclub.repositories.UserRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	UserRepository userRepository;
	
	
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
    
	//borrow book
	public void borrowBook(Long userId, Long bookId) {
		Book book = bookRepository.findById(bookId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		book.setBorrower(user);
		bookRepository.save(book);
	}
	
	//return book
	public void returnBook(Long userId, Long bookId) {
		Book book = bookRepository.findById(bookId).orElse(null);
		book.setBorrower(null);
		bookRepository.save(book);		
	}
    
}