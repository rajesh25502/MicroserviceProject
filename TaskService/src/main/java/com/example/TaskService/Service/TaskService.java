package com.example.TaskService.Service;

import com.example.TaskService.DTO.USER_ROLE;
import com.example.TaskService.Model.Task;
import com.example.TaskService.Model.TaskStatus;
import com.example.TaskService.Repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;
    public Task createTask(Task req, USER_ROLE requestRole)throws Exception
    {
        if(!requestRole.equals(USER_ROLE.ADMIN))
        {
            throw new Exception("Only admin can create Task");
        }

        req.setStatus(TaskStatus.PENDING);
        req.setCreatedAt(LocalDateTime.now());

        return taskRepo.save(req);
    }

    public Task getTaskById(Long id)throws Exception
    {
        return taskRepo.findById(id).orElseThrow(()->new Exception("Task id not found"));
    }
    public List<Task> getllTask(TaskStatus status)throws Exception
    {
        List<Task> tasks=taskRepo.findAll();
        List<Task> filteredTask=tasks.stream().filter(task->status==null || task.getStatus().equals(status)).collect(Collectors.toList());
        return filteredTask;
    }
    public Task updateTask(Long id, Task req,Long userId)throws Exception
    {
        Task existingTask=getTaskById(id);
        if(req.getTitle()!=null)
            existingTask.setTitle(req.getTitle());
        if(req.getDescription()!=null)
            existingTask.setDescription(req.getDescription());
        if(req.getStatus()!=null)
            existingTask.setStatus(req.getStatus());
        if(req.getImage()!=null)
            existingTask.setImage(req.getImage());
        if(req.getDeadLine()!=null)
            existingTask.setDeadLine(req.getDeadLine());

        return taskRepo.save(existingTask);
    }

    public void deleteTask(Long id)throws Exception
    {
        getTaskById(id);
        taskRepo.deleteById(id);
    }

    public Task assignToUser(Long userID,Long taskID)throws Exception
    {
        Task task=getTaskById(taskID);
        task.setAssignedUserId(userID);
        task.setStatus(TaskStatus.ASSIGNED);
        return taskRepo.save(task);
    }

    public List<Task> assignedUserTasks(Long userID, TaskStatus status)throws Exception
    {
        List<Task> tasks=taskRepo.findByAssignedUserId(userID);
        List<Task> filteredTask=tasks.stream().filter(task->status==null || task.getStatus().equals(status)).collect(Collectors.toList());
        return filteredTask;
    }

    public Task completeTask(Long taskId)throws Exception
    {
        Task task=getTaskById(taskId);
        task.setStatus(TaskStatus.DONE);
        return taskRepo.save(task);
    }
}
