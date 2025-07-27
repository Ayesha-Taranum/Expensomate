
package com.expensomate.dto;

import lombok.Data;

@Data
public class UserSignupRequest {
	 private String username;
	    private String email;
	    private String phoneNumber;
	    private String password;

}
