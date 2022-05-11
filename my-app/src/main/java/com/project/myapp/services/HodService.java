package com.project.myapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.myapp.models.Faculty;
import com.project.myapp.models.HOD;
import com.project.myapp.payload.response.AllCoordinatorResponse;
import com.project.myapp.payload.response.MessageResponse;
import com.project.myapp.repositories.HODRepository;
import com.project.myapp.repositories.UserRepository;

@Service
public class HodService {
	@Autowired
	private HODRepository hodRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public ResponseEntity<?> addHod(HOD e){
		if(hodRepository.existsByEmail(e.getEmail())) {
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
			hodRepository.save(e);
			return ResponseEntity.ok(new MessageResponse("Faculty added successfully!"));
		}
	}
	public ResponseEntity<?> getHod(){
		List<HOD> l=hodRepository.findAll();
		List<AllCoordinatorResponse> k=new ArrayList<AllCoordinatorResponse>();
		l.stream().forEach((e)->k.add(new AllCoordinatorResponse(e.getId(),e.getUsername(),e.getFirstName(),e.getLastName(),e.getEmail(),e.getGender(),e.getPhoneNumber())));
		return new ResponseEntity<AllCoordinatorResponse>(k.get(0),HttpStatus.OK);
	}
}
