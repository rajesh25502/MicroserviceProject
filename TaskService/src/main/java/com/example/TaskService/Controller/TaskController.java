package com.example.TaskService.Controller;

import com.example.TaskService.DTO.UserDTO;
import com.example.TaskService.Model.Task;
import com.example.TaskService.Model.TaskStatus;
import com.example.TaskService.Service.TaskService;
import com.example.TaskService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody Task req,
                                           @RequestHeader("Authorization") String jwt)throws Exception
    {
        UserDTO user=userService.getUserProfile(jwt);
        Task task=taskService.createTask(req,user.getRole());
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId,
                                            @RequestHeader("Authorization") String jwt)throws Exception
    {
        UserDTO user=userService.getUserProfile(jwt);
        Task task=taskService.getTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> getAssignedUserTask(@RequestParam(required = false)TaskStatus status,
                                                    @RequestHeader("Authorization") String jwt)throws Exception
    {
        UserDTO user=userService.getUserProfile(jwt);
        List<Task> tasks=taskService.assignedUserTasks(user.getId(),status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false)TaskStatus status,
                                                          @RequestHeader("Authorization") String jwt)throws Exception
    {
        UserDTO user=userService.getUserProfile(jwt);
        List<Task> tasks=taskService.getllTask(status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/user/{userId}")
    public ResponseEntity<Task> assignTaskToUser(@PathVariable Long taskId,
            @PathVariable Long userId,
            @RequestHeader("Authorization") String jwt)throws Exception
    {
        UserDTO user=userService.getUserProfile(jwt);
        Task task=taskService.assignToUser(userId,taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/update")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId,
                                                @RequestBody Task req,
                                                 @RequestHeader("Authorization") String jwt)throws Exception
    {
        UserDTO user=userService.getUserProfile(jwt);
        Task task=taskService.updateTask(taskId,req,user.getId());
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long taskId)throws Exception
    {
        Task task=taskService.completeTask(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/delete")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId)throws Exception
    {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>("Task Successfully deleted", HttpStatus.OK);
    }
}
