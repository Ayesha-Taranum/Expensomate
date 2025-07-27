package com.expensomate.controller;

import com.expensomate.dto.UserLoginRequest;
import com.expensomate.dto.UserResponse;
import com.expensomate.dto.UserSignupRequest;
import com.expensomate.entity.User;
import com.expensomate.exception.UserAlreadyExistsException;
import com.expensomate.mapper.UserMapper;
import com.expensomate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("users")
public class UserController {

    // --- START OF CONSTANTS ---
    // Keys for validation error maps
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHONE_NUMBER = "phoneNumber";

    // Key for general error responses
    private static final String KEY_ERROR = "error"; // CONSTANT ADDED HERE

    // Validation error messages
    private static final String ERROR_USERNAME_EMPTY = "Username cannot be empty";
    private static final String ERROR_USERNAME_LENGTH = "Username must be between 3 and 50 characters";
    private static final String ERROR_EMAIL_EMPTY = "Email cannot be empty";
    private static final String ERROR_EMAIL_INVALID = "Invalid email format";
    private static final String ERROR_PASSWORD_EMPTY = "Password cannot be empty";
    private static final String ERROR_PASSWORD_LENGTH = "Password must be at least 6 characters long";
    private static final String ERROR_PHONE_NUMBER_LENGTH = "Phone number must be exactly 10 characters";

    // Email validation pattern
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    // --- END OF CONSTANTS ---

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody UserSignupRequest signupRequest) {
        Map<String, String> errors = new HashMap<>();
        validateSignupRequest(signupRequest, errors);

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            User userToRegister = UserMapper.toEntity(signupRequest);
            User registeredUser = userService.signupUser(userToRegister);
            UserResponse userResponse = UserMapper.toDto(registeredUser);
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            // Using the constant KEY_ERROR
            return new ResponseEntity<>(Map.of(KEY_ERROR, e.getMessage()), HttpStatus.CONFLICT);
        } catch (Exception e) {
            // In a real application, you should log the exception e.
            // Using the constant KEY_ERROR
            return new ResponseEntity<>(Map.of(KEY_ERROR, "An internal error occurred during signup."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginRequest loginRequest) {
        Map<String, String> errors = new HashMap<>();
        validateLoginRequest(loginRequest, errors);

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Optional<User> userOptional = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (userOptional.isPresent()) {
            User loggedInUser = userOptional.get();
            UserResponse userResponse = UserMapper.toDto(loggedInUser);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            // Using the constant KEY_ERROR
            return new ResponseEntity<>(Map.of(KEY_ERROR, "Invalid username or password"), HttpStatus.UNAUTHORIZED);
        }
    }

    private void validateSignupRequest(UserSignupRequest signupRequest, Map<String, String> errors) {
        validateUsername(signupRequest.getUsername(), errors);
        validateEmail(signupRequest.getEmail(), errors);
        validatePassword(signupRequest.getPassword(), errors);
        validatePhoneNumber(signupRequest.getPhoneNumber(), errors);
    }

    private void validateUsername(String username, Map<String, String> errors) {
        if (username == null || username.trim().isEmpty()) {
            errors.put(KEY_USERNAME, ERROR_USERNAME_EMPTY);
        } else if (username.trim().length() < 3 || username.trim().length() > 50) {
            errors.put(KEY_USERNAME, ERROR_USERNAME_LENGTH);
        }
    }

    private void validateEmail(String email, Map<String, String> errors) {
        if (email == null || email.trim().isEmpty()) {
            errors.put(KEY_EMAIL, ERROR_EMAIL_EMPTY);
        } else {
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                errors.put(KEY_EMAIL, ERROR_EMAIL_INVALID);
            }
        }
    }

    private void validatePassword(String password, Map<String, String> errors) {
        if (password == null || password.isEmpty()) {
            errors.put(KEY_PASSWORD, ERROR_PASSWORD_EMPTY);
        } else if (password.length() < 6) {
            errors.put(KEY_PASSWORD, ERROR_PASSWORD_LENGTH);
        }
    }

    private void validatePhoneNumber(String phoneNumber, Map<String, String> errors) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()&&phoneNumber.trim().length() != 10) {
            
                errors.put(KEY_PHONE_NUMBER, ERROR_PHONE_NUMBER_LENGTH);
            
        }
    }

    private void validateLoginRequest(UserLoginRequest loginRequest, Map<String, String> errors) {
        if (loginRequest.getUsername() == null || loginRequest.getUsername().trim().isEmpty()) {
            errors.put(KEY_USERNAME, ERROR_USERNAME_EMPTY);
        }
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            errors.put(KEY_PASSWORD, ERROR_PASSWORD_EMPTY);
        }
    }
}
