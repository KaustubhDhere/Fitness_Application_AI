package com.fitness.userservice.service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exist");
        }

       /*
       Normal way of setting the values
        */

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getFirstName());

        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());

        /*
        Using the Builder Pattern
         */
//        User user = User.builder()
//                .email(request.getEmail())
//                .password(request.getPassword())
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .build();
//        User savedUser = userRepository.save(user);
//
//        UserResponse userResponse = UserResponse.builder()
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .firstName(savedUser.getFirstName())
//                .lastName(savedUser.getLastName())
//                .createdAt(savedUser.getCreatedAt())
//                .updatedAt(savedUser.getUpdatedAt())
//                .build();

        return userResponse;

    }

    /*
      @UserId :This is userId
      return the User specific details
     */
    public UserResponse getUserProfile(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new RuntimeException("Invalid userId in request");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserResponse.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public UserResponse deleteUserProfile(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new RuntimeException("Invalid userId in request");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        userRepository.deleteById(userId);

        return UserResponse.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
