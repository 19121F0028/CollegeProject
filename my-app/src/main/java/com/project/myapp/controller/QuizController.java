package com.project.myapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.myapp.models.Batch;
import com.project.myapp.models.Choice;
import com.project.myapp.models.Question;
import com.project.myapp.models.Quiz;
import com.project.myapp.repositories.BatchRepository;
import com.project.myapp.repositories.QuizRepository;
import com.project.myapp.repositories.StudentRepository;
import com.project.myapp.services.QuizService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/api/quiz")
public class QuizController {
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuizService quizService;
	@Autowired
	private BatchRepository batchRepository;
	@PostMapping("/createQuiz/{id}")
	public ResponseEntity<?> createQuiz(@PathVariable Long id,@RequestBody Quiz quiz) {
		quiz.getBatches().stream().forEach((e1)->{
			Batch batch=batchRepository.findByBatchName(e1.getBatchName());
			e1.setId(batch.getId());
		}
				);
		return quizService.createQuiz(id,quiz);
	}
	@PostMapping("/addQuestion/{quizId}")
	public ResponseEntity<?> addQuestion(@PathVariable Long quizId,@RequestBody Question question){
		
//		return new ResponseEntity<>(HttpStatus.OK);
		return quizService.addQuestion(quizId, question);
	}
	@GetMapping("/getAllQuestions/{id}")
	public ResponseEntity<?> getAllQuestions(@PathVariable Long id){
		return quizService.getAllQuestions(id);
	}
	@GetMapping("/getQuizDuration/{id}")
	public Long getQuizDuration(@PathVariable Long id) {
		return quizService.getQuizDuration(id);
	}
	@PostMapping("/submitQuiz/{id}/{duration}/{studentId}")
	public ResponseEntity<?> submitQuiz(@PathVariable Long id,@PathVariable Long duration,@PathVariable Long studentId,@RequestBody  Map<Long,Integer> map){
//		for(Map.Entry m : map.entrySet()){    
//		    System.out.println(m.getKey()+" "+m.getValue());    
//		   }  
//		return new ResponseEntity<>(HttpStatus.OK);
		return quizService.submitQuiz(id,duration,studentId, map);
	}
	@GetMapping("/getQuizResult/{id}")
	public ResponseEntity<?> getQuizResult(@PathVariable Long id){
		return quizService.getQuizResult(id);
	}
	@GetMapping("/getStudentsAttempted/{id}")
	public ResponseEntity<?> getStudentsAttempted(@PathVariable Long id){
		return quizService.getStudentsAttempted(id);
	}
	@GetMapping("/getQuizDetails/{id}")
	public ResponseEntity<?> getQuizDetails(@PathVariable Long id){
		return quizService.getQuizDetails(id);
	}
	@GetMapping("getQuizResult/{rollNo}/{quizId}")
	public ResponseEntity<?> getQuizResult(@PathVariable String rollNo,@PathVariable Long quizId){
		return quizService.getQuizResults(quizId,rollNo);
	}
	@GetMapping("/getAllQuizzes/{id}")
	@PreAuthorize("hasRole('ROLE_FACULTY') or hasRole('ROLE_CORDINATOR')")
	public ResponseEntity<?> getAllQuizzes(@PathVariable Long id){
		return quizService.getAllQuizzes(id);
	}
	@GetMapping("/getAllQuizzNames/{id}")
	public ResponseEntity<?> getAllQuizzNames(@PathVariable Long id){
		return quizService.getAllQuizzNames(id);
	}
	@GetMapping("/checkQuizActivated/{id}")
	public boolean checkQuizExpires(@PathVariable Long id){
		Date d=new Date();
		Date d1=new Date();
		d1.setHours(12);
		d1.setMinutes(40);
//		Quiz quiz=quizRepository.getById(id);
//		System.out.println(quiz.getQuizActivationTime().);
		
		System.out.println(d);
		System.out.println(d1);
		if(d.after(d1)) {
			return true;
		}
		else {
			return false;
		}
	}
	@GetMapping("/getQuizName/{id}")
	public ResponseEntity<?> getQuizName(@PathVariable Long id){
		return quizService.getQuizName(id);
	}
	@GetMapping(path="/checkQuizName/{name}")
	public boolean checkQuizName(@PathVariable("name") String name) {
		return quizRepository.existsByQuizName(name);
	}
}
