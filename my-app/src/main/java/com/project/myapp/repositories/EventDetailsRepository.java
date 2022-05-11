package com.project.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.models.EventDetails;

public interface EventDetailsRepository  extends JpaRepository<EventDetails, Long>{

	Boolean existsByEventName(String name);
}
