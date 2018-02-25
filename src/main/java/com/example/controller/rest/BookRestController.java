package com.example.controller.rest;

import com.example.model.Book;
import com.example.repository.BookRepository;
import com.example.repository.service.BookService;
import com.example.utils.TimestampUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        if(id > bookRepository.tableSize() || id <= 0) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok(bookRepository.findOne(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveBook(@Valid @RequestBody Book book, BindingResult result) {
        if(!bookRepository.findAll().contains(book) || !result.hasErrors()) {
            book.setTimestamp(timestampUtility.getTimestamp());
            bookRepository.save(book);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(bookRepository.tableSize())
                    .toUri();
            return ResponseEntity.created(location).body(book);
        } else return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
