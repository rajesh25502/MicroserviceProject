package com.example.SubmissionService.DTO;

import com.example.SubmissionService.Model.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TaskDTO {
    private Long id;

    private String title;

    private String description;

    private String image;

    private Long assignedUserId;

    private List<String> tags=new ArrayList<>();

    private LocalDateTime deadLine;

    private LocalDateTime createdAt;

    private TaskStatus status;
}
