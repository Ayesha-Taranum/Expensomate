package com.expensomate.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensomate.entity.User;
import com.expensomate.exception.UserAlreadyExistsException;
import com.expensomate.repository.UserRepository;
import com.expensomate.service.UserService;
@Service
public class UserServiceImpl  implements UserService  {
	private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signupUser(User user) throws UserAlreadyExistsException {

        if (user.getDateCreated() == null) { 
            user.setDateCreated(LocalDate.now());
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Username '" + user.getUsername() + "' already taken.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Email '" + user.getEmail() + "' already registered.");
        }
        if (user.getPhoneNumber() != null && !user.getPhoneNumber().trim().isEmpty() && userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new UserAlreadyExistsException("Phone number '" + user.getPhoneNumber() + "' already registered.");
        }

      

        return userRepository.save(user); 
    }

    @Override
    public Optional<User> loginUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isPhoneNumberTaken(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }
}
