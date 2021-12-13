package com.codingdojo.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.models.LoginUser;
import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.services.BookService;
import com.codingdojo.bookclub.services.UserService;

@Controller
public class HomeController {
    
    @Autowired
    private UserService userServ;
    
    @Autowired
    private BookService bookServ;
    
    
    //renders the forms for login and reg
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    //this route is the action for submitting a new user, reg
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        // this calls on the register method in service, and sends new user info, and the results from the binding results.
    	userServ.register(newUser, result);
    	//if there are any errors, stay on index/login/reg page
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        //otherwise set the user ID in session
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/dashboard";
    }
    
    //this route is the action for submitting an existing user, login
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/dashboard";
    }
    
    @RequestMapping("/dashboard")
    public String dashboard(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") != null) {
    	Long user_id = (Long) session.getAttribute("user_id");
    	model.addAttribute("user", userServ.oneUser(user_id));
    	model.addAttribute("books", bookServ.allBooks());
    	return "dashboard.jsp";
    } else {
    	redirectAttributes.addFlashAttribute("error", "You need to be loggin in!");
    	return "redirect:/";
    	}
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user_id");
    	return "redirect:/";
    }
    
    
    //Create new book
    @GetMapping("/books/new")
    public String newbook(@ModelAttribute("book") Book book, HttpSession session, 
    					  Model model, RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You need to be loggin in!");
    		return "redirect:/";
    	}
    	Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
    	return "newBook.jsp";
    }
    
	@PostMapping("/newbook")
	public String book(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if (result.hasErrors()) { 
	    return "newBook.jsp";
		 } else {   
		bookServ.createBook(book);
	    return "redirect:/dashboard";
		 }
	}
	
	//Show one book
	@GetMapping("/books/{id}")
	public String onebook(@PathVariable Long id, Model model, HttpSession session,
						  RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "Can't view a book, but nice try. Now log in.");
    		return "redirect:/";
    	}
		User user = userServ.oneUser((Long) session.getAttribute("user_id"));
    
    	model.addAttribute("user", user);
		model.addAttribute("book", bookServ.findBookById(id));
		return "showBook.jsp";
	}
	
	///Edit book
	@RequestMapping("/books/edit/{id}")
	public String editBook(@PathVariable("id") long id,
			Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You can't edit a random book without logging in, sorry. not.");
    		return "redirect:/";
    	}
		Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
		Book book = bookServ.findBookById(id);
		model.addAttribute("book", book);
		return "editBook.jsp";
	}
	
	@PostMapping(value="/updatebook/{id}")
	public String updateBook(@PathVariable("id") long id, 
			@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "editBook.jsp";
		}
		else {
			bookServ.updateBook(book);
			return "redirect:/books/" +id;
		}
	}
	
	@GetMapping("/books/delete/{id}")
	public String deleteBook(@PathVariable("id") long id, RedirectAttributes redirectAttributes,
							 HttpSession session) {
    	if (session.getAttribute("user_id") == null) {
        	redirectAttributes.addFlashAttribute("error", "You really think you can delete a book like this? nope.");
    		return "redirect:/";
    	}
		bookServ.deleteBook(id);
		redirectAttributes.addFlashAttribute("message", "Your Book has been deleted");	
		return "redirect:/dashboard";
	}
	
	///////////////////The book market///////////////////
	@GetMapping("/bookmarket")
	public String bookMarket(Model model, HttpSession session) {
		Long user = (Long)session.getAttribute("user_id");
		if(user == null) {
			return "redirect:/dashboard";
		} else {
			model.addAttribute("user", userServ.oneUser(user));
			model.addAttribute("books", bookServ.allBooks());
			 return "bookBroker.jsp";
		}
	}

	/////////////borrow a book //////////////
	@GetMapping("/book/{id}/borrow")
	public String borrowBook(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";
		} else {
			User user = userServ.oneUser(userId);
			Book book = bookServ.findBookById(id);
			if(book.getUser().getId()!=user.getId() && book.getBorrower()==null) {
				bookServ.borrowBook(userId, id);
			}
			return "redirect:/bookmarket";
		}
	}
	
	///////////////return a book/////////////////
	@GetMapping("/book/{id}/return")
	public String returnBook(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";
		} else {
			User user = userServ.oneUser(userId);
			Book book = bookServ.findBookById(id);
			if(book.getBorrower().getId()==user.getId()) {
				bookServ.returnBook(userId, id);
			}
			return "redirect:/bookmarket";
		}
	}
}
