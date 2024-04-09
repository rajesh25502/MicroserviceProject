package com.example.SubmissionService.Repository;

import com.example.SubmissionService.Model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepo extends JpaRepository<Submission,Long> {
    public List<Submission> findByTaskId(Long taskId);
}
