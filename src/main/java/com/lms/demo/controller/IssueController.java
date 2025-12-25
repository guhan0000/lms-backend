package com.lms.demo.controller;

import com.lms.demo.model.Book;
import com.lms.demo.model.Issue;
import com.lms.demo.model.User;
import com.lms.demo.repository.IssueRepository;
import com.lms.demo.service.IssueService;
import com.lms.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
    @PostMapping("issue/")
    public Issue issueBook(@RequestParam String email,@RequestParam Long bookId){
        return issueService.issueBook(email,bookId);
    }
    @GetMapping("return/")
    public Issue returnBook(@RequestParam String email, @RequestParam Long bookId){
        return issueService.returnBook(email, bookId);
//        Issue issue = issues.get(0);
    }
    @GetMapping("history/")
    public Collection<Issue>geBorrowedHistory(@RequestParam String email){
        return issueService.getBorrowedHistory(email);
    }
//    @GetMapping("admin/history")
//    public List<User> getAllUsers(){
//        return issueService.getAllUsers();
//    }

}

