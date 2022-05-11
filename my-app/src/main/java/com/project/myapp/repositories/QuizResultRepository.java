package com.project.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.models.QuizResult;


public interface QuizResultRepository extends JpaRepository<QuizResult,Long>{

}
