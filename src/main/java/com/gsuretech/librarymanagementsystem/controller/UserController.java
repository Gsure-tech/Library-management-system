package com.gsuretech.librarymanagementsystem.controller;


import com.gsuretech.librarymanagementsystem.dto.LoginDto;
import com.gsuretech.librarymanagementsystem.dto.UserRequest;
import com.gsuretech.librarymanagementsystem.dto.UserResponse;
import com.gsuretech.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")

public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public UserResponse createAccount(@RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);

    }
}
