package com.lms.demo.service;

import com.lms.demo.model.Book;
import com.lms.demo.model.Issue;
import com.lms.demo.model.User;
import com.lms.demo.repository.BookRepository;
import com.lms.demo.repository.IssueRepository;
import com.lms.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service

public class IssueService {
    @Autowired
    IssueRepository issueRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    Issue save;

    public Collection<Issue> getAllIssuedBooks(){

        return issueRepository.findAll();
    }
    public Issue issueBook(String email, Long bookId){
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book Not Found"));
        if(book.getAvailableCopies()<=0){
            log.warn("book out of stock");
            throw new RuntimeException("Book out of stock");
        }
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        Issue issue=new Issue();
        issue.setIssueDate(LocalDate.now());
        issue.setStatus("ISSUED");
        issue.setBook(book);
        issue.setUser(user);
        return issueRepository.save(issue);

    }
    public Issue returnBook(String email, Long bookId){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not Found"));
        List<Issue> issues = issueRepository.findByUser(user);

        for(Issue issue:issues){
            if(Objects.equals(issue.getBook().getBookId(), bookId) && issue.getStatus().equals("ISSUED")){
                issue.setStatus("RETURN");
                issue.setReturnDate(LocalDate.now());
                book.setAvailableCopies(book.getAvailableCopies()+1);
                bookRepository.save(book);
                save = issueRepository.save(issue);
            }

        }


       return save;
    }
    public Collection<Issue> getBorrowedHistory(String email){
        log.info("Searching user with email: [{}]", email);

        User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
         return  issueRepository.findByUser(user);

    }
//    public List<User>getAllUsers(){
//        return userRepository.findAll();
//    }

}
