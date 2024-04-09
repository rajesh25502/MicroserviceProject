package com.example.TaskService.Controller;

import com.example.TaskService.DTO.UserDTO;
import com.example.TaskService.Model.Task;
import com.example.TaskService.Model.TaskStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String> home()throws Exception
    {
        return new ResponseEntity<>("Task Service Up and running", HttpStatus.OK);
    }
}
