package com.project.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.models.StudentScore;

public interface StudentScoreRepository extends JpaRepository<StudentScore,Long>{

}
