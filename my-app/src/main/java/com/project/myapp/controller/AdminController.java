package com.project.myapp.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.myapp.models.Batch;
import com.project.myapp.models.ERole;
import com.project.myapp.models.Role;
import com.project.myapp.models.Student;
import com.project.myapp.payload.request.AddStudentRequest;
import com.project.myapp.payload.response.MessageResponse;
import com.project.myapp.repositories.BatchRepository;
import com.project.myapp.repositories.EventCordinatorRepository;
import com.project.myapp.repositories.FacultyRepository;
import com.project.myapp.repositories.HODRepository;
import com.project.myapp.repositories.RoleRepository;
import com.project.myapp.repositories.StudentRepository;
import com.project.myapp.repositories.UserRepository;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/admin")
public class AdminController {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private EventCordinatorRepository cordinatorRepository;
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private HODRepository hodRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BatchRepository batchRepository;
	
		@Autowired
	  private PasswordEncoder encoder;
		
		@Autowired
		private RoleRepository roleRepository;
	
	@PostMapping(path="/addStudent")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addStudent(@Valid @RequestBody AddStudentRequest request){
		
		if(userRepository.existsByUsername(request.getUsername())) {
			return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: username is already taken!"));
		}
		
		if (studentRepository.existsByEmail(request.getEmail())) {
		      return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: Email is already taken!"));
		    }
		if (studentRepository.existsByPhoneNumber(request.getEmail())) {
		      return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: phone number  is already taken!"));
		    }
		
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName(ERole.ROLE_STUDENT).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
		Batch batch=batchRepository.findByBatchName(request.getBatch());
		Student student=new Student(request.getUsername(),
	               encoder.encode(request.getPassword()),request.getFirstName(),request.getLastName(),request.getEmail(),request.getGender(),request.getPhoneNumber(),batch);
		student.setRoles(roles);
		
		studentRepository.save(student);
		
		return ResponseEntity.ok(new MessageResponse("student added successfully!"));
	}
	@GetMapping(path="/checkEmail/{email}")
	public boolean check(@PathVariable("email") String email) {
		return studentRepository.existsByEmail(email) || cordinatorRepository.existsByEmail(email) || facultyRepository.existsByEmail(email) || hodRepository.existsByEmail(email);
	}
	@GetMapping(path="/checkUsername/{username}")
	public boolean checkUsername(@PathVariable("username") String username) {
		return userRepository.existsByUsername(username);
	}
	@GetMapping(path = "/allBatches")
	public ResponseEntity<?> getAllBatches(){
		return new ResponseEntity<List<Batch>>(batchRepository.findAll(),HttpStatus.OK);
	}
	@GetMapping(path="batch/allStudent/{id}")
	public ResponseEntity<?> getAll(@PathVariable Long id){
		Batch batch=batchRepository.findById(id).get();
		Set<Student> l=batch.getStudents();
		return new ResponseEntity<Set<Student>>(l,HttpStatus.OK);
	}
	@DeleteMapping(path="deleteBatch/{id}")
	public ResponseEntity<?> deleteBatch(@PathVariable Long id){
		batchRepository.deleteById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
