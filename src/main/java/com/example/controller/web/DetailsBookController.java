package com.example.controller.web;

import com.example.model.Book;
import com.example.repository.BookRepository;
import com.example.utils.TimestampUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DetailsBookController {

    private BookRepository bookRepository;
    private TimestampUtility timestampUtility;

    @Autowired
    public void setTimestampUtility(TimestampUtility timestampUtility) {
        this.timestampUtility = timestampUtility;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        Book book = bookRepository.findOne(id);
        model.addAttribute("book", book);
        model.addAttribute("date", timestampUtility.getDate(book.getTimestamp()));
        return "details";
    }
}
