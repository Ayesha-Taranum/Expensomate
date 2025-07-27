package com.expensomate.dto;

import lombok.Data;

import java.time.LocalDate;

@Data

public class UserResponse {
	 private String userId;
	    private String username;
	    private String email;
	    private String phoneNumber;
	    private LocalDate dateCreated;

}
