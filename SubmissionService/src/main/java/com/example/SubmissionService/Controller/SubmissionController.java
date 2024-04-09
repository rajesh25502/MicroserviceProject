package com.example.SubmissionService.Controller;

import com.example.SubmissionService.DTO.UserDTO;
import com.example.SubmissionService.Model.Submission;
import com.example.SubmissionService.Service.SubmissionService;
import com.example.SubmissionService.Service.TaskService;
import com.example.SubmissionService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<Submission> submitTask(@RequestParam Long taskId,
                                                 @RequestParam String githubLink,
                                                 @RequestHeader("Authorization") String jwt)throws Exception
    {
        UserDTO user=userService.getUserProfile(jwt);
        Submission submission=submissionService.submitTask(taskId,githubLink,user.getId(),jwt);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/{submissionId}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long submissionId,
                                                 @RequestHeader("Authorization") String jwt)throws Exception
    {
        UserDTO user=userService.getUserProfile(jwt);
        Submission submission=submissionService.getTaskSubmissionById(submissionId);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping("/allSubmission")
    public ResponseEntity<List<Submission>> getAllSubmission(@RequestHeader("Authorization") String jwt)throws Exception
    {
        UserDTO user=userService.getUserProfile(jwt);
        List<Submission> submissions=submissionService.getAllSubmission();
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getSubmissionByTaskId(@PathVariable Long taskId,
                                                        @RequestHeader("Authorization") String jwt)throws Exception
    {
        UserDTO user=userService.getUserProfile(jwt);
        List<Submission> submissions=submissionService.getSubmissionByTaskId(taskId);
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }
    @PutMapping("/{submissionID}/acceptDecline")
    public ResponseEntity<Submission> acceptDeclineSubmission(@PathVariable Long submissionID,
                                                                  @RequestParam("status") String status,
                                                                  @RequestHeader("Authorization") String jwt)throws Exception
    {
        UserDTO user=userService.getUserProfile(jwt);
        Submission submission=submissionService.acceptDeclineSubmission(submissionID,status);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

}
