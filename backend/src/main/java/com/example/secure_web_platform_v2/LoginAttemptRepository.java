package com.example.secure_web_platform_v2;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long>{

	List<LoginAttempt> findByUsernameUsedAndAttemptTimeAfterOrderByAttemptTimeDesc(String username, LocalDateTime cutoffTime);
}
