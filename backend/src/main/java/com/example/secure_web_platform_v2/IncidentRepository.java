package com.example.secure_web_platform_v2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
	List<Incident> findAllByOrderByIncidentIDDesc();
	List<Incident> findByLoginAttemptUserUsernameOrderByIncidentIDDesc(String username);
}
