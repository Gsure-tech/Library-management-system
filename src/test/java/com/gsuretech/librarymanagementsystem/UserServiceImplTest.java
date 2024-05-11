package com.gsuretech.librarymanagementsystem;

import com.gsuretech.librarymanagementsystem.dto.UserInfo;
import com.gsuretech.librarymanagementsystem.dto.UserRequest;
import com.gsuretech.librarymanagementsystem.dto.UserResponse;
import com.gsuretech.librarymanagementsystem.entity.User;
import com.gsuretech.librarymanagementsystem.repository.UserRepository;
import com.gsuretech.librarymanagementsystem.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createAccount_ExistingEmail_ReturnsAccountExistsResponse() {
        UserRequest request = new UserRequest();
        request.setEmail("test@example.com");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        UserResponse response = userService.createAccount(request);
        assertNull(response.getUserInfo());
    }

    @Test
    void createAccount_NewUser_ReturnsSuccessResponse() {
        UserRequest request = new UserRequest();
        request.setEmail("newuser@example.com");
        request.setPassword("password");
        request.setFirstName("Test");
        request.setLastName("User");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(modelMapper.map(any(User.class), eq(UserInfo.class))).thenReturn(new UserInfo());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserResponse response = userService.createAccount(request);

        assertNotNull(response.getUserInfo());
    }
}
