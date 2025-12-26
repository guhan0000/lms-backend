package com.lms.demo.controller;

import com.lms.demo.model.Book;
import com.lms.demo.model.Issue;
import com.lms.demo.model.User;
import com.lms.demo.repository.IssueRepository;
import com.lms.demo.service.IssueService;
import com.lms.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/issues/")
public class IssueController {
    @Autowired
    private IssueService issueService;
    @Autowired
    private UserService userService;

    @GetMapping("admin/history")
    public Collection<Issue> getAllIssuedBooks(){
        return issueService.getAllIssuedBooks();
    }
    @PostMapping("issue/{bookId}")
    public Issue issueBook(@PathVariable Long bookId){
        String email= Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        log.info("email{email}",email);
        return issueService.issueBook(email,bookId);
    }
    @PostMapping("return/{bookId}")
    public Issue returnBook(@PathVariable Long bookId){
        String email= Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        return issueService.returnBook(email, bookId);
//        Issue issue = issues.get(0);
    }
    @GetMapping("history/")
    public Collection<Issue>geBorrowedHistory(){
        String email= Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        return issueService.getBorrowedHistory(email);
    }
//    @GetMapping("admin/history")
//    public List<User> getAllUsers(){
//        return issueService.getAllUsers();
//    }

}

