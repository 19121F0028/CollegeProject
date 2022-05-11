package com.project.myapp.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.myapp.models.ERole;
import com.project.myapp.models.EventCordinator;
import com.project.myapp.models.Role;
import com.project.myapp.payload.response.MessageResponse;
import com.project.myapp.repositories.EventCordinatorRepository;
import com.project.myapp.repositories.RoleRepository;
import com.project.myapp.services.EventCordinatorService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/api/eventCordinator")
@RestController
public class EventCordinatorController {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private EventCordinatorRepository eventCordinatorRepository;
	
	@Autowired
	private EventCordinatorService eventCordinatorService;

	@PostMapping(path="/addEventCordinator")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> add(@RequestBody EventCordinator e) {
		if(eventCordinatorRepository.existsByUsername(e.getUsername())) {
			return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: username is already taken!"));
		}
		
		if (eventCordinatorRepository.existsByEmail(e.getEmail())) {
		      return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: Email is already taken!"));
		    }
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName(ERole.ROLE_CORDINATOR).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
		e.setRoles(roles);
		return eventCordinatorService.add(e);
		
	}
	@GetMapping(path = "/allCoordinators")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllCoordinators(){
		return eventCordinatorService.getAllCoordinators();
	}
	@GetMapping("/checkUsername/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean isUsernameExists(@PathVariable("username") String username)
	{
		return eventCordinatorRepository.existsByUsername(username);
	}
	@GetMapping(path="/checkEmail/{email}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean check(@PathVariable("email") String email) {
		return eventCordinatorRepository.existsByEmail(email);
	}
	@DeleteMapping("deleteCoordinator/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id){
		return eventCordinatorService.deleteCoordinator(id);
	}
	@GetMapping(path="/getCoordinator/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CORDINATOR')")
	public ResponseEntity<?> getStudent(@PathVariable Long id) {
		return eventCordinatorService.getStudent(id);
	}
	
}
