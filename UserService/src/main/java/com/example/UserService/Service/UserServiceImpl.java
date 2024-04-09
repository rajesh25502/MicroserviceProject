package com.example.UserService.Service;

import com.example.UserService.Config.JWTProvider;
import com.example.UserService.Model.User;
import com.example.UserService.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTProvider jwtProvider;
    public User findUserByJwtToken(String jwt) throws Exception {
        String email=jwtProvider.getEmailFromJWTToken(jwt);
        User user=findUserByEmail(email);
        return user;
    }

    public User findUserByEmail(String email) throws Exception {
        User user=userRepo.findByEmail(email);
        if(user==null)
        {
            throw new Exception("User not found");
        }
        return user;
    }
    public List<User> getAllUsers()throws Exception
    {
        return userRepo.findAll();
    }
}
