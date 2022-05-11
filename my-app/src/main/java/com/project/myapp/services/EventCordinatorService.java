package com.project.myapp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.myapp.models.Event;
import com.project.myapp.models.EventCordinator;
import com.project.myapp.models.Quiz;
import com.project.myapp.models.Student;
import com.project.myapp.payload.response.AllCoordinatorResponse;
import com.project.myapp.payload.response.AllQuizResponse;
import com.project.myapp.payload.response.AllStudentResponse;
import com.project.myapp.payload.response.MessageResponse;
import com.project.myapp.repositories.EventCordinatorRepository;
import com.project.myapp.repositories.StudentRepository;

@Service
public class EventCordinatorService {

	@Autowired
	private EventCordinatorRepository eventCordinatorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public ResponseEntity<?> add(EventCordinator e){
		if(eventCordinatorRepository.existsByEmail(e.getEmail())) {
			return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: Email is already taken!"));
		}
		else if(eventCordinatorRepository.existsByUsername(e.getUsername())) {
			return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: username is already taken!"));
		}
		else {
			e.setPassword(passwordEncoder.encode(e.getPassword()));
			eventCordinatorRepository.save(e);
			return ResponseEntity.ok(new MessageResponse("Coordinator added successfully!"));
		}
	}
	public ResponseEntity<?> getAllCoordinators(){
		List<EventCordinator> l=eventCordinatorRepository.findAll();
		List<AllCoordinatorResponse> k=new ArrayList<AllCoordinatorResponse>();
		l.stream().forEach((e)->k.add(new AllCoordinatorResponse(e.getId(),e.getUsername(),e.getFirstName(),e.getLastName(),e.getEmail(),e.getGender(),e.getPhoneNumber())));
		return new ResponseEntity<List<AllCoordinatorResponse>>(k,HttpStatus.OK);
	}
	public ResponseEntity<String> deleteCoordinator(Long  id){
		Optional<EventCordinator> k=eventCordinatorRepository.findById(id);
		if(k.isPresent()) {
			EventCordinator eventCordinator=k.get();
			eventCordinatorRepository.delete(eventCordinator);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> getStudent(Long id){
		Optional<EventCordinator> s=eventCordinatorRepository.findById(id);
		if(s.isPresent()) {
			EventCordinator e=s.get();
			AllCoordinatorResponse a=new AllCoordinatorResponse(e.getId(),e.getUsername(), e.getFirstName(),e.getLastName(),e.getEmail(),e.getGender(),e.getPhoneNumber());
			return new ResponseEntity<AllCoordinatorResponse>(a,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
 
}
