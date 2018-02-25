package com.example.controller.web;

import com.example.model.Book;
import com.example.model.User;
import com.example.repository.BookRepository;
import com.example.repository.UserRepository;
import com.example.utils.TimestampUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/add")
public class AddBookController {
    public static final String SUCCESS_MSG = "Book added successfuly";

    private BookRepository bookRepository;
    private UserRepository userRepository;
    private TimestampUtility timestampUtility;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setTimestampUtility(TimestampUtility timestampUtility) {
        this.timestampUtility = timestampUtility;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping
    public String add(Model model){
        Book book = new Book();
        model.addAttribute(book);
        return "add";
    }

    @PostMapping
    public String addBookToDB(@Valid @ModelAttribute Book book, BindingResult result,
                              RedirectAttributes redirectAttributes, Principal principal) {
       User user = userRepository.findByUsername(principal.getName());
       if(!result.hasErrors()){
           book.setTimestamp(timestampUtility.getTimestamp());
           book.setUser(user);
           bookRepository.save(book);
           redirectAttributes.addFlashAttribute("message", SUCCESS_MSG);
           return "redirect:/add";
       }
        return "add";
    }

}
