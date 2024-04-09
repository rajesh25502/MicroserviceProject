package com.example.SubmissionService.Service;


import com.example.SubmissionService.DTO.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TaskService",url="http://localhost:8002")
public interface TaskService {
    @GetMapping("/api/task/{taskId}")
    public TaskDTO getTaskById(@PathVariable Long taskId,
                               @RequestHeader("Authorization") String jwt)throws Exception;

    @PutMapping("/api/task/{taskId}/complete")
    public TaskDTO completeTask(@PathVariable Long taskId);
}


