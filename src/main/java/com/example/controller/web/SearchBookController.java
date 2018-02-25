package com.example.controller.web;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchBookController {

    public static final String NO_MATCHES = "No matches";
    public static final String CANT_BE_NULL = "Title can now be empty";

    private BookRepository bookRepository;
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String search(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("foundBooks", new ArrayList<>());
        model.addAttribute("message", null);
        return "search";
    }


    @PostMapping
    public String searchBooks(@ModelAttribute Book book, Model model) {
        String pattern = book.getTitle().trim();
        List<Book> foundBooks = new ArrayList<>();
        if(!pattern.equals("")) {
            foundBooks = bookRepository.findAllByTitleContains(pattern);
            if(foundBooks.isEmpty()) model.addAttribute("message", NO_MATCHES);
        } else {
            model.addAttribute("message", CANT_BE_NULL);
        }
        model.addAttribute("book", new Book());
        model.addAttribute("foundBooks", foundBooks);
        return "search";
    }
}
