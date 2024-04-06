package com.gsuretech.librarymanagementsystem.service.serviceImpl;

import com.gsuretech.librarymanagementsystem.config.JwtTokenProvider;
import com.gsuretech.librarymanagementsystem.dto.LoginDto;
import com.gsuretech.librarymanagementsystem.dto.UserInfo;
import com.gsuretech.librarymanagementsystem.dto.UserRequest;
import com.gsuretech.librarymanagementsystem.dto.UserResponse;
import com.gsuretech.librarymanagementsystem.entity.Role;
import com.gsuretech.librarymanagementsystem.entity.User;
import com.gsuretech.librarymanagementsystem.repository.UserRepository;
import com.gsuretech.librarymanagementsystem.service.UserService;
import com.gsuretech.librarymanagementsystem.utils.LibraryUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserResponse createAccount(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
           UserResponse userResponse = new UserResponse();
           userResponse.setUserInfo(null);
           userResponse.setResponseCode(LibraryUtils.ACCOUNT_EXISTS_CODE);
           userResponse.setResponseCode(LibraryUtils.ACCOUNT_EXISTS_CODE);
       return userResponse;
        }
        User newUser = new User();
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setOtherName(userRequest.getOtherName());
        newUser.setGender(userRequest.getGender());
        newUser.setAddress(userRequest.getAddress());
        newUser.setStateOfOrigin(userRequest.getStateOfOrigin());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setPhoneNumber(userRequest.getPhoneNumber());
        newUser.setRole(Role.valueOf("ROLE_ADMIN"));
        User savedUser = userRepository.save(newUser);

        UserInfo userInfo = modelMapper.map(savedUser, UserInfo.class);
        UserResponse userResponse = new UserResponse();
        userResponse.setResponseMessage(LibraryUtils.ACCOUNT_CREATION_MESSAGE);
        userResponse.setResponseCode(LibraryUtils.ACCOUNT_CREATION_SUCCES);
        userResponse.setUserInfo(userInfo);
        return userResponse;
    }

    @Override
    public UserResponse login(LoginDto loginDto) {
        Authentication authentication = null;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        UserResponse userResponse = new UserResponse();
        userResponse.setResponseMessage(jwtTokenProvider.generateToken(authentication));
        userResponse.setResponseCode("Login Success");
        return userResponse;
    }
}
