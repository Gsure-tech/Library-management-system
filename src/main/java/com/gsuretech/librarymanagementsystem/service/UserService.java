package com.gsuretech.librarymanagementsystem.service;

import com.gsuretech.librarymanagementsystem.dto.LoginDto;
import com.gsuretech.librarymanagementsystem.dto.UserRequest;
import com.gsuretech.librarymanagementsystem.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createAccount(UserRequest userRequest);
    UserResponse login(LoginDto loginDto);
}
