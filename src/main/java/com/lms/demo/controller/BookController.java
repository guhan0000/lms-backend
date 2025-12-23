package com.lms.demo.controller;

import com.lms.demo.model.Book;
import com.lms.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/get/all")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/get/{bookId}")
    public Book getBook(@PathVariable Long bookId){
        return bookService.getBook(bookId);
    }
    @PostMapping("/add")
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }
    @DeleteMapping("/delete/{bookId}")
    public void deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
    }
    @PutMapping("/update/{bookId}")
    public Book updateBook(@PathVariable Long bookId,@RequestBody Book bookDetails){
        return bookService.updateBook(bookId,bookDetails);
    }


}
