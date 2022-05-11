package com.project.myapp.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.myapp.models.Batch;
import com.project.myapp.models.ERole;
import com.project.myapp.models.EventCordinator;
import com.project.myapp.models.Faculty;
import com.project.myapp.models.Role;
import com.project.myapp.models.Student;
import com.project.myapp.payload.response.AllCoordinatorResponse;
import com.project.myapp.payload.response.MessageResponse;
import com.project.myapp.repositories.FacultyRepository;
import com.project.myapp.repositories.UserRepository;
@Service
public class FacultyService {
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public ResponseEntity<?> addFaculty(Faculty e){
		if(facultyRepository.existsByEmail(e.getEmail())) {
			return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: Email is already taken!"));
		}
		else if(userRepository.existsByUsername(e.getUsername())) {
			return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: username is already taken!"));
		}
		else {
			e.setPassword(passwordEncoder.encode(e.getPassword()));
			facultyRepository.save(e);
			return ResponseEntity.ok(new MessageResponse("Faculty added successfully!"));
		}
	}
	public ResponseEntity<?> getAllFaculty(){
		List<Faculty> l=facultyRepository.findAll();
		List<AllCoordinatorResponse> k=new ArrayList<AllCoordinatorResponse>();
		l.stream().forEach((e)->k.add(new AllCoordinatorResponse(e.getId(),e.getUsername(),e.getFirstName(),e.getLastName(),e.getEmail(),e.getGender(),e.getPhoneNumber())));
		return new ResponseEntity<List<AllCoordinatorResponse>>(k,HttpStatus.OK);
	}
	public ResponseEntity<?> getFaculty(Long id){
		Optional<Faculty> s=facultyRepository.findById(id);
		if(s.isPresent()) {
			Faculty e=s.get();
			AllCoordinatorResponse a=new AllCoordinatorResponse(e.getId(),e.getUsername(), e.getFirstName(),e.getLastName(),e.getEmail(),e.getGender(),e.getPhoneNumber());
			return new ResponseEntity<AllCoordinatorResponse>(a,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}
