package com.expensomate.mapper;

import java.time.LocalDate;
import java.util.UUID;

import com.expensomate.dto.UserResponse;
import com.expensomate.dto.UserSignupRequest;
import com.expensomate.entity.User;

public class UserMapper {
	private UserMapper() {}

    public static User toEntity(UserSignupRequest request) {
        if (request == null) {
            return null;
        }
        User user = new User();
        user.setUserId(UUID.randomUUID().toString()); 
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword()); 
        user.setDateCreated(LocalDate.now()); 
        return user;
    }

    public static UserResponse toDto(User user) {
        if (user == null) {
            return null;
        }
        UserResponse dto = new UserResponse();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setDateCreated(user.getDateCreated());
        return dto;
    }
}
