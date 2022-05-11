package com.project.myapp.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.myapp.models.ERole;
import com.project.myapp.models.HOD;
import com.project.myapp.models.Role;
import com.project.myapp.repositories.RoleRepository;
import com.project.myapp.services.HodService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/api/hod")
public class HodController {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private HodService hodService;
	
	@PostMapping(path="/addHod")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> add(@RequestBody HOD e) {
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName(ERole.ROLE_HOD).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
		e.setRoles(roles);
		return hodService.addHod(e);
		
	}
	@GetMapping(path = "/getHod")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HOD')")
	public ResponseEntity<?> getAllFaculty(){
		return hodService.getHod();
	}
}
