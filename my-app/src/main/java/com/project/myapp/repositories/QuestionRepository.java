package com.project.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.models.Question;



public interface QuestionRepository extends JpaRepository<Question,Long> {

}
