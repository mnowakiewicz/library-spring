package com.example.controller.web;

import com.example.model.Book;
import com.example.repository.BookRepository;
import com.example.repository.service.BookService;
import com.example.utils.TimestampUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    String index(Model model){
        List<Book> books = bookService.getRandomBooks(2);
        model.addAttribute("books", books);
        return "index";
    }


}
