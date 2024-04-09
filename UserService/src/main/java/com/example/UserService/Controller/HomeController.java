package com.example.UserService.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping()
    public ResponseEntity<String> home()throws Exception
    {
        return new ResponseEntity<>("User Service Up and running", HttpStatus.OK);
    }
}
