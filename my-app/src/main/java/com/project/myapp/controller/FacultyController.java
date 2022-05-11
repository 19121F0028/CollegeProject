package com.project.myapp.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.myapp.models.ERole;
import com.project.myapp.models.Faculty;
import com.project.myapp.models.Role;
import com.project.myapp.repositories.RoleRepository;
import com.project.myapp.services.FacultyService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/api/faculty")
public class FacultyController {
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping(path="/addFaculty")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> add(@RequestBody Faculty e) {
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName(ERole.ROLE_FACULTY).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
		e.setRoles(roles);
		return facultyService.addFaculty(e);
		
	}
	@GetMapping(path = "/allFaculty")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllFaculty(){
		return facultyService.getAllFaculty();
	}
	@GetMapping(path="/getFaculty/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACULTY')")
	public ResponseEntity<?> getStudent(@PathVariable Long id) {
		return facultyService.getFaculty(id);
	}
}
