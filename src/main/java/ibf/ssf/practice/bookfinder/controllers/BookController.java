package ibf.ssf.practice.bookfinder.controllers;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ibf.ssf.practice.bookfinder.models.Book;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
public class BookController {


    // Load session
    // If no books, show message
    @GetMapping("/")
    public ModelAndView getIndexPage(HttpSession session) {

        ModelAndView mav = new ModelAndView("library");
        List<Book> bookList = getBooks(session);
        mav.addObject("bookList", bookList);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> GETINDEXPAGE: " + bookList);
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView getFormPage() {

        ModelAndView mav = new ModelAndView("add-book");
        mav.addObject("newBook", new Book());
        return mav;

    }

    @PostMapping("/add")
    public ModelAndView postAddBook(HttpSession session, 
    @RequestBody @ModelAttribute("newBook") @Valid Book newBook, BindingResult bindings) {

        // Set newBook id
        Random rand = new SecureRandom();
        newBook.setId(newBook.getAuthor() + Integer.toString(10000 + rand.nextInt(9999))); 
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> NEWBOOK: " + newBook);

        ModelAndView mav = new ModelAndView("add-book");
        List<Book> bookList = getBooks(session);
        mav.addObject("bookList", bookList);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> BEFORE VALIDATION BOOKLIST: " + bookList);

        // Validation
        if (bindings.hasErrors()) {
            // Return form field
            mav.addObject("newBook", newBook);

        } else {
            // Add newBook to bookList
            bookList.add(newBook);
            // Return form fields as blank
            mav.addObject("newBook", new Book());
            // POST-redirect-GET
            mav.setViewName("redirect:/");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>> SUCCESSFUL VALIDATION BOOKLIST: " + bookList);
        }

        return mav;
    }

    @GetMapping("{id}")
    public ModelAndView getBookDetails(
            HttpSession session, Book newBook,
            @PathVariable String id) {

        ModelAndView mav = new ModelAndView("book-details");
        List<Book> bookList = getBooks(session);
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                newBook = book;
            }
        }       
        mav.addObject("newBook", newBook);
        return mav;

    }

    // Set session if it does not exist
    // Retrieve session if it exist
    private List<Book> getBooks(HttpSession session) {
        List<Book> bookList = (List<Book>) session.getAttribute("bookList");
        if (bookList == null) {
            bookList = new LinkedList<>();
            session.setAttribute("bookList", bookList);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> GETBOOKLIST: " + bookList);
        
        return bookList;
    }
}