package com.example.SubmissionService.Service;

import com.example.SubmissionService.DTO.TaskDTO;
import com.example.SubmissionService.DTO.UserDTO;
import com.example.SubmissionService.Model.Submission;
import com.example.SubmissionService.Repository.SubmissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepo submissionRepo;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;
    public Submission submitTask(Long taskId,String githubLink,Long userId, String jwt)throws Exception
    {
        TaskDTO task=taskService.getTaskById(taskId,jwt);
        //UserDTO user=userService.getUserProfile(jwt);
        if(task!=null)
        {
            Submission submission=new Submission();
            submission.setTaskId(taskId);
            submission.setUserId(userId);
            submission.setGithubLink(githubLink);
            submission.setSubmissionTime(LocalDateTime.now());
            return submissionRepo.save(submission);
        }
        throw new Exception("Task not found with id");
    }

    public Submission getTaskSubmissionById(Long submissionId)throws Exception
    {
        return submissionRepo.findById(submissionId).orElseThrow(()->
                new Exception("Task submission not found with id"));
    }

    public List<Submission> getAllSubmission()throws Exception
    {
        return submissionRepo.findAll();
    }

    public List<Submission> getSubmissionByTaskId(Long taskId)throws Exception
    {
        return submissionRepo.findByTaskId(taskId);
    }

    public Submission acceptDeclineSubmission(Long id,String status)throws Exception
    {
        Submission submission=getTaskSubmissionById(id);

        if(status.equals("ACCEPT"))
        {
            submission.setStatus("ACCEPTED");
            taskService.completeTask(submission.getTaskId());
        }

        return submissionRepo.save(submission);
    }
}
