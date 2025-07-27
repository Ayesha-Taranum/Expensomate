package com.expensomate.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name = "usercredentials")
public class User {

	    @Id
	    @Column(name = "userid")
	    private String userId; 

	    @Column(name = "username", unique = true, nullable = false)
	    private String username;

	    @Column(name = "emailid", unique = true, nullable = false)
	    private String email;

	    @Column(name = "phonenumber")
	    private String phoneNumber;

	    @Column(name = "password", nullable = false)
	    private String password;

	    @Column(name = "createdate")
	    private LocalDate dateCreated; 
	}
