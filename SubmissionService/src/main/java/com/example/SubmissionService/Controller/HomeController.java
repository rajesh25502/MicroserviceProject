package com.example.SubmissionService.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String> home()throws Exception
    {
        return new ResponseEntity<>("Submission Service Up and running", HttpStatus.OK);
    }
}
