package com.example.SubmissionService.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String password;

    private String email;

    private USER_ROLE role;

    private String fullName;
}
