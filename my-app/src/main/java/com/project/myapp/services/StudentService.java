package com.project.myapp.services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.myapp.models.Batch;
import com.project.myapp.models.ERole;
import com.project.myapp.models.Event;
import com.project.myapp.models.EventDetails;
import com.project.myapp.models.Quiz;
import com.project.myapp.models.Role;
import com.project.myapp.models.Student;
import com.project.myapp.models.StudentAttempt;
import com.project.myapp.models.StudentScore;
import com.project.myapp.payload.request.StudentUpdateRequest;
import com.project.myapp.payload.response.AllEventsResponse;
import com.project.myapp.payload.response.AllQuizResponse;
import com.project.myapp.payload.response.AllStudentResponse;
import com.project.myapp.payload.response.StudentQuizDetails;
import com.project.myapp.repositories.BatchRepository;
import com.project.myapp.repositories.EventRepository;
import com.project.myapp.repositories.QuizRepository;
import com.project.myapp.repositories.RoleRepository;
import com.project.myapp.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private BatchRepository batchRepository;
	@Autowired
	private RoleRepository roleRepository;
		
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private QuizRepository quizRepository;
	
	
	public ResponseEntity<?> getAllStudents()
	{
		List<Student> list=studentRepository.findAll();
		Student s=new Student();
		s.getUsername();
		List<AllStudentResponse> l=new ArrayList<AllStudentResponse>();
		list.stream().forEach((e)->l.add(new AllStudentResponse(e.getId(),e.getUsername(),e.getFirstName(),e.getLastName(),e.getEmail(),e.getGender(),e.getPhoneNumber()
				,e.getBatch().getBatchName()))
				);

		return new ResponseEntity<List<AllStudentResponse>>(l,HttpStatus.OK);
	}
	public ResponseEntity<Student> updateStudent(Long id,StudentUpdateRequest s) {
		Optional<Student> k=studentRepository.findById(id);
		if(k.isPresent()) {
			Batch batch=batchRepository.findByBatchName(s.getBatch());
			Student student=k.get();
			student.setFirstName(s.getFirstName());
			student.setLastName(s.getLastName());
			student.setGender(s.getGender());
			student.setBatch(batch);
			student.setPhoneNumber(s.getPhoneNumber());
			
			Set<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName(ERole.ROLE_STUDENT).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
			student.setRoles(roles);
			return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	public ResponseEntity<String> updateEmail(Long id,String Email){
		if(studentRepository.existsById(id)) {
			studentRepository.updateMail(Email,id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
	}
	public ResponseEntity<String> updateUsername(Long id,String username){
		if(studentRepository.existsById(id)) {
			if(studentRepository.existsByUsername(username)) {
				return new ResponseEntity<String>("username already exists",null, 10);
			}
			
			studentRepository.updateUsername(username, id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<String> updatePassword(Long id,String password){
		if(studentRepository.existsById(id)) {
			studentRepository.updatePassword(password, id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	@Transactional
	public ResponseEntity<String> deleteStudent(Long  id){
		Optional<Student> k=studentRepository.findById(id);
		if(k.isPresent()) {
			Student student=k.get();
			Set<Event> events=student.getEventsRegistered();
			List<Event> k1=new ArrayList<Event>();
			Set<Event> events1=student.getEventsParticipated();
			List<Event> k2=new ArrayList<Event>();
			events.stream().forEach((e)->{
				k1.add(e);
			}
					);
			k1.stream().forEach((e)->{
				student.removeEventRegistered(e);
			}
					);
			events1.stream().forEach((e)->{
				k2.add(e);
			}
					);
			k2.stream().forEach((e)->{
				student.removeEventParticipated(e);
			});
			studentRepository.delete(student);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> getStudent(Long id){
		Optional<Student> s=studentRepository.findById(id);
		if(s.isPresent()) {
			Student student=s.get();
			AllStudentResponse a=new AllStudentResponse(student.getId(),student.getUsername(), student.getFirstName(),student.getLastName(),student.getEmail(),student.getGender(),student.getPhoneNumber(),student.getBatch().getBatchName());
			return new ResponseEntity<AllStudentResponse>(a,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}





public ResponseEntity<?> getAllQuizzes(Long id){
	Optional<Student> s=studentRepository.findById(id);
	if(s.isPresent()) {
		Student student=s.get();
		Batch batch=batchRepository.getById(student.getBatch().getId());
		Set<Quiz> registered=batch.getQuizzesRegistered();
		
		List<AllQuizResponse> a = new ArrayList<AllQuizResponse>();
		registered.stream().forEach((k)
				->{
					
						Long a1=k.getId();
						a.add(new AllQuizResponse(a1,k.getQuizName()));
					
					});
		a.sort(Comparator.comparing(AllQuizResponse::getId));
//		Collections.sort(a);
		return new ResponseEntity<List<AllQuizResponse>>(a,HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>("Student Id NOT FOUND",HttpStatus.NOT_FOUND);
	}
}
public ResponseEntity<?> getQuizDetails(Long quizId,Long studentId){
	Optional<Student> s=studentRepository.findById(studentId);
	if(s.isPresent()) {
		Student student=s.get();
		Quiz quiz=quizRepository.getById(quizId);
		List<StudentAttempt> k=quiz.getStudentsAttempted();
		int c=0;
		for(StudentAttempt temp:k) {
			if(temp.getRollNo().equals(student.getUsername())) {
				c=temp.getAttempts();
			}
		}
		String quizStatus="";
		Date d=new Date();
		if(d.after(quiz.getQuizExpiresDate())) {
			quizStatus="expired";
		}
		if(d.before(quiz.getQuizActivationDate())) {
			quizStatus="not activated";
		}
		if(d.after(quiz.getQuizActivationDate()) && d.before(quiz.getQuizExpiresDate())) {
			quizStatus="active";
		}
		StudentQuizDetails s1=new StudentQuizDetails(quiz.getQuizName(),quiz.getQuizActivationDate(),quiz.getQuizExpiresDate(),quiz.getDuration(),quiz.getNoOfAttempts(),quiz.getQuestions().size(),c,quizStatus);
		
		return new ResponseEntity<StudentQuizDetails>(s1,HttpStatus.OK);
	}else{
		return new ResponseEntity<String>("Student Id NOT FOUND",HttpStatus.NOT_FOUND);
	}
}
	public ResponseEntity<?> getQuizResults(Long quizId,Long studentId){
		Optional<Student> s=studentRepository.findById(studentId);
		if(s.isPresent()) {
			Student student=s.get();
			Quiz quiz=quizRepository.getById(quizId);
			List<StudentScore> s1=new ArrayList<StudentScore>();
			for(StudentScore t:quiz.getQuizResult().getStudents()) {
				if(t.getRollNo().equals(student.getUsername())) {
					s1.add(t);
				}
			}
			return new ResponseEntity<List<StudentScore>>(s1,HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("Student Id NOT FOUND",HttpStatus.NOT_FOUND);
		}
}
}
