package com.expensomate.service;

import java.util.Optional;

import com.expensomate.entity.User;
import com.expensomate.exception.UserAlreadyExistsException;

public interface UserService {
	
	 User signupUser(User user) throws UserAlreadyExistsException;
	    Optional<User> loginUser(String username, String password);
	    boolean isUsernameTaken(String username);
	    boolean isEmailTaken(String email);
	    boolean isPhoneNumberTaken(String phoneNumber);

}
