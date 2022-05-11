package com.project.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.models.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
	Boolean existsByEmail(String email);
}
