package com.project.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.models.Choice;

public interface ChoiceRepository extends JpaRepository<Choice,Long> {

}
