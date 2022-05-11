package com.project.myapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.models.Event;
import com.project.myapp.models.EventDetails;

public interface EventRepository extends JpaRepository<Event,Long> {
	public Optional<Event> findByEventDetails(EventDetails eventDetails);
}
