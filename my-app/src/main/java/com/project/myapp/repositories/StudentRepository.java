package com.project.myapp.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.myapp.models.Student;





public interface StudentRepository extends JpaRepository<Student,Long> {
	
	Optional<Student> findByUsername(String email);
	Boolean existsByEmail(String email);
	Boolean existsByPhoneNumber(String phoneNumber);
	Boolean existsByUsername(String username);
	@Transactional
	@Modifying
	@Query("update Student s set s.email=?1 where s.id=?2")
	void updateMail(String mail,Long id);
	@Transactional
	@Modifying
	@Query("update Student s set s.username=?1 where s.id=?2")
	void updateUsername(String username,Long id);
	@Transactional
	@Modifying
	@Query("update Student s set s.password=?1 where s.id=?2")
	void updatePassword(String password,Long id);
}
