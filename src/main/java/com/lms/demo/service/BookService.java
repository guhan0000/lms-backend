package com.lms.demo.service;

import com.lms.demo.model.Book;
import com.lms.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
//    READ
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
//    READ
    public Book getBook(Long bookId){
        return bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book not found"));
    }
//    CREATE
    public Book addBook(Book book){
        book.setAvailableCopies(book.getTotalCopies());
        return bookRepository.save(book);
    }
//    DELETE
    public void deleteBook(Long bookId){
        bookRepository.deleteById(bookId);
    }
//    UPDATE
    public Book updateBook(Long bookId,Book bookDetails){
        Book book = getBook(bookId);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setCategory(bookDetails.getCategory());
        book.setIsbn(bookDetails.getIsbn());
        book.setTotalCopies(bookDetails.getTotalCopies());
        book.setAvailableCopies(bookDetails.getTotalCopies());
        return bookRepository.save(book);
    }
}
