package com.project.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.models.Quiz;
public interface QuizRepository extends JpaRepository<Quiz,Long> {
	Boolean existsByQuizName(String name);
}
