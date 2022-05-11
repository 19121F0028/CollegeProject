package com.project.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.myapp.models.EventResult;

public interface EventResultRepository extends JpaRepository<EventResult, Long> {
//
//	@Query("update EventResult s set s.score=?1 where s.id=?2")
//	void updateMail(int score,Long eventId,Long );
}
