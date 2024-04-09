package com.example.TaskService.Service;

import com.example.TaskService.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "UserService",url="http://localhost:8001")
public interface UserService {

    @GetMapping("/api/user/profile")
    public UserDTO getUserProfile(@RequestHeader("Authorization") String jwt);
}
