package com.lms.demo.controller;

import com.lms.demo.model.Book;
import com.lms.demo.model.Issue;
import com.lms.demo.repository.IssueRepository;
import com.lms.demo.service.IssueService;
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
    @GetMapping("get/issue/all")
    public Collection<Issue> getAllIssuedBooks(String status){
        return issueService.getAllIssuedBooks(status);
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

}
