package com.project.myapp.controller;

import java.util.HashSet;
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
import com.project.myapp.payload.request.Email;
import com.project.myapp.payload.request.StudentUpdateRequest;
import com.project.myapp.payload.response.MessageResponse;
import com.project.myapp.repositories.BatchRepository;
import com.project.myapp.repositories.RoleRepository;
import com.project.myapp.repositories.StudentRepository;
import com.project.myapp.repositories.UserRepository;
import com.project.myapp.services.StudentService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/api/student")
public class StudentController {
	@Autowired
	private BatchRepository batchRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	 private PasswordEncoder encoder;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/checkUsername/{username}")
	public boolean isUsernameExists(@PathVariable("username") String username)
	{
		return studentRepository.existsByUsername(username);
	}
	
	@GetMapping(path="/checkEmail/{email}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean check(@PathVariable("email") String email) {
		
		System.out.println(email);
		return studentRepository.existsByEmail(email);
	}
	@PostMapping(path="/addStudent")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addStudent(@Valid @RequestBody AddStudentRequest request){
		System.out.println(request.getUsername()+" "+request.getPassword());
		
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
	
	@GetMapping(path="/allStudents")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> allStudents(){
		return studentService.getAllStudents();
	}
	@GetMapping(path="/getStudent/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
	public ResponseEntity<?> getStudent(@PathVariable Long id) {
		return studentService.getStudent(id);
	}
	@PutMapping("/updateStudent/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody StudentUpdateRequest s) {
			
		return studentService.updateStudent(id, s);
	}
	@PutMapping("/updateMail/{id}/{mail}")
	public ResponseEntity<String> updateEmail(@PathVariable Long id,@PathVariable String mail){
		System.out.print(mail);
		return studentService.updateEmail(id, mail);
	}
	@PutMapping("/updateUsername/{id}/{username}")
	public ResponseEntity<String> updateUsername(@PathVariable Long id,@PathVariable String username){
		System.out.println(username);
		return studentService.updateUsername(id, username);
	}
	@DeleteMapping("deleteStudent/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id){
		return studentService.deleteStudent(id);
	}
	
	
	@GetMapping("getAllQuizzes/{id}")
	public ResponseEntity<?> getAllQuizzes(@PathVariable Long id){
		return studentService.getAllQuizzes(id);
	}
	@GetMapping("getQuizDetails/{studentId}/{quizId}")
	public ResponseEntity<?> getQuizDetails(@PathVariable Long studentId,@PathVariable Long quizId){
		return studentService.getQuizDetails(quizId, studentId);
	}
	@GetMapping("getQuizResult/{studentId}/{quizId}")
	public ResponseEntity<?> getQuizResult(@PathVariable Long studentId,@PathVariable Long quizId){
		return studentService.getQuizResults(quizId, studentId);
	}
	
}
