package com.expensomate.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensomate.entity.User;
@Repository
public interface UserRepository  extends JpaRepository<User, String>  {
	Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByUsernameAndPassword(String username, String password); // For simple login (pre-hashing)
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber); // Corrected method name

}
