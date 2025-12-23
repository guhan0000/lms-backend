package com.lms.demo.repository;

import com.lms.demo.model.Issue;
import com.lms.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue,Long> {
   List<Issue> findByUser(User user);
   List<Issue> findByStatus(String status);
}
