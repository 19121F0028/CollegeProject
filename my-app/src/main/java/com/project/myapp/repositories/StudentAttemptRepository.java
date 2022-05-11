package com.project.myapp.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.models.StudentAttempt;

public interface StudentAttemptRepository extends JpaRepository<StudentAttempt,Long> {
	
	public StudentAttempt findByRollNo(String rollNo);
}
