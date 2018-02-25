package com.example.repository.service;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Returns random {@link Book} objects from database
     * @param value number of random objects
     */
    public List<Book> getRandomBooks(int value){
        Integer tableSize = bookRepository.tableSize();
        List<Integer> numbers = Stream.iterate(1, x -> x+1).limit(tableSize)
                                                                .collect(Collectors.toList());
        Collections.shuffle(numbers);
        List<Book> books = new ArrayList<>();
        for(int i=0; i<value; i++){
            books.add(bookRepository.findOne(Long.valueOf(numbers.get(i))));
        }
        return books;
    }
}
