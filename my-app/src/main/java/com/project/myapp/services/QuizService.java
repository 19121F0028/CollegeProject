package com.project.myapp.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.myapp.models.Batch;
import com.project.myapp.models.EventCordinator;
import com.project.myapp.models.Faculty;
import com.project.myapp.models.Question;
import com.project.myapp.models.Quiz;
import com.project.myapp.models.QuizResult;
import com.project.myapp.models.Student;
import com.project.myapp.models.StudentAttempt;
import com.project.myapp.models.StudentScore;
import com.project.myapp.payload.response.AllQuizResponse;
import com.project.myapp.repositories.ChoiceRepository;
import com.project.myapp.repositories.EventCordinatorRepository;
import com.project.myapp.repositories.FacultyRepository;
import com.project.myapp.repositories.QuestionRepository;
import com.project.myapp.repositories.QuizRepository;
import com.project.myapp.repositories.QuizResultRepository;
import com.project.myapp.repositories.StudentAttemptRepository;
import com.project.myapp.repositories.StudentRepository;
import com.project.myapp.repositories.StudentScoreRepository;

@Service
public class QuizService {
	@Autowired
	private StudentAttemptRepository studentAttemptRepository;
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ChoiceRepository choiceRepository;
	@Autowired
	private QuizResultRepository quizResultRepository;
	@Autowired
	private StudentScoreRepository studentScoreRepository;
	@Autowired 
	private EventCordinatorRepository eventCordinatorRepository;
	@Autowired
	private FacultyRepository facultyRepository;
	public ResponseEntity<?> createQuiz(Long id,Quiz quiz){
		Optional<EventCordinator> e=eventCordinatorRepository.findById(id);
		Optional<Faculty> faculty=facultyRepository.findById(id);
		if(e.isPresent()) {
			EventCordinator k=e.get();
			QuizResult quizResult=new QuizResult();
			quizResultRepository.save(quizResult);
			Set<Batch> b=quiz.getBatches();
			b.stream().forEach(a->{
				quiz.addBatch(a);
			});
			k.getQuizzesAdded().add(quiz);
			
			quiz.setQuizResult(quizResult);
			quizRepository.save(quiz);
			return new ResponseEntity<String>(HttpStatus.OK);
		}else if(faculty.isPresent()){
			Faculty k=faculty.get();
			QuizResult quizResult=new QuizResult();
			quizResultRepository.save(quizResult);
			Set<Batch> b=quiz.getBatches();
			b.stream().forEach(a->{
				quiz.addBatch(a);
			});
			k.getQuizzesAdded().add(quiz);
			
			quiz.setQuizResult(quizResult);
			quizRepository.save(quiz);
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	public ResponseEntity<?> addQuestion(Long id,Question question) {
		Optional<Quiz> q=quizRepository.findById(id);
		if(q.isPresent()) {
			Quiz quiz=q.get();
			choiceRepository.saveAll(question.getChoices());
			questionRepository.save(question);
			quiz.getQuestions().add(question);
			quizRepository.save(quiz);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> getAllQuestions(Long id) {
		Optional<Quiz> q=quizRepository.findById(id);
		if(q.isPresent()) {
			Quiz quiz=q.get();
			return new ResponseEntity<List<Question>>(quiz.getQuestions(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	public Long getQuizDuration(Long id) {
		Optional<Quiz> q=quizRepository.findById(id);
		if(q.isPresent()) {
			Quiz quiz=q.get();
			return quiz.getDuration();
		}
		else {
			return (long) 0;
		}
	}
	public ResponseEntity<?> submitQuiz(Long quizId,Long duration,Long studentId,Map<Long,Integer> map){
		Optional<Quiz> q=quizRepository.findById(quizId);
		if(q.isPresent()) {
			int count=0;
			Quiz quiz=q.get();
			for(Map.Entry m : map.entrySet()){    
			        Question k=questionRepository.getById((Long) m.getKey());
			        String s=k.getChoices().get((int) m.getValue()-1).getChoice();
			        if(k.getAnswer().equals(s)) {
			        	count++;
			        }
			        
			   }
			Student s=studentRepository.getById(studentId);
			String rollNo=s.getUsername();
			
			Boolean flag=false;
			List<StudentAttempt> a= quiz.getStudentsAttempted();
			StudentAttempt stdAttempt=new StudentAttempt(rollNo,count,duration,1);
			for(StudentAttempt temp:a) {
				if(temp.getRollNo().equals(rollNo)) {
					stdAttempt=temp;
					stdAttempt.setAttempts(stdAttempt.getAttempts()+1);
					stdAttempt.setScore(count);
					stdAttempt.setDuration(duration);
					flag=true;
					break;
				}
			}
			if(!flag) {
				studentAttemptRepository.save(stdAttempt);
				quiz.getStudentsAttempted().add(stdAttempt);
			}
			
			StudentScore s1=new StudentScore(s.getUsername(), count, duration,stdAttempt.getAttempts(),quiz.getQuestions().size(),map.size());
			studentScoreRepository.save(s1);
			QuizResult quizResult=quiz.getQuizResult();
			quizResult.getStudents().add(s1);
			quizResultRepository.save(quizResult);
			quiz.setQuizResult(quizResult);
			quizRepository.save(quiz);
			return new ResponseEntity<>(count,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> getQuizResult(Long quizId){
		Optional<Quiz> q=quizRepository.findById(quizId);
		if(q.isPresent()) {
			Quiz quiz=q.get();
			QuizResult k=quiz.getQuizResult();
			return new ResponseEntity<QuizResult>(k,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> getStudentsAttempted(Long quizId){
		Optional<Quiz> q=quizRepository.findById(quizId);
		if(q.isPresent()) {
			Quiz quiz=q.get();
			List<StudentAttempt> k=quiz.getStudentsAttempted();
			Comparator<StudentAttempt> scoreComparator = Comparator
	                .comparing(StudentAttempt::getScore);
			Comparator<StudentAttempt> timeComparator = Comparator
	                .comparing(StudentAttempt::getDuration);
			Comparator<StudentAttempt> attemptComparator = Comparator
	                .comparing(StudentAttempt::getAttempts);
			Comparator<StudentAttempt> multipleFieldComparator = scoreComparator.reversed()
					.thenComparing(attemptComparator)
	                .thenComparing(timeComparator);
	                
			Collections.sort(k, multipleFieldComparator);
			return new ResponseEntity<List<StudentAttempt>>(k,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> getQuizDetails(Long quizId){
		Optional<Quiz> q=quizRepository.findById(quizId);
		if(q.isPresent()) {
			Quiz quiz=q.get();
			AllQuizResponse a=new AllQuizResponse(quizId,quiz.getQuizName(),quiz.getQuizActivationDate(),quiz.getQuizExpiresDate(),quiz.getDuration(),quiz.getNoOfAttempts(),quiz.getQuestions().size(),quiz.getStudentsAttempted().size());
			return new ResponseEntity<AllQuizResponse>(a,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> getQuizResults(Long quizId,String rollNo){
		Optional<Student> s=studentRepository.findByUsername(rollNo);
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
	public ResponseEntity<?> getAllQuizzes(Long id){
		Optional<EventCordinator> s=eventCordinatorRepository.findById(id);
		Optional<Faculty> faculty=facultyRepository.findById(id);
		if(s.isPresent()) {
			EventCordinator e=s.get();
			List<AllQuizResponse> k=new ArrayList<AllQuizResponse>();
			
			for(Quiz q:e.getQuizzesAdded()) {
				k.add(new AllQuizResponse(q.getId(),q.getQuizName()));}
			return new ResponseEntity<List<AllQuizResponse>>(k,HttpStatus.OK);
		}else if(faculty.isPresent()){
			Faculty e=faculty.get();
			List<AllQuizResponse> k=new ArrayList<AllQuizResponse>();
			
			for(Quiz q:e.getQuizzesAdded()) {
				k.add(new AllQuizResponse(q.getId(),q.getQuizName()));}
			return new ResponseEntity<List<AllQuizResponse>>(k,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> getAllQuizzNames(Long id){
		Optional<EventCordinator> s=eventCordinatorRepository.findById(id);
		Optional<Faculty> faculty=facultyRepository.findById(id);
		if(s.isPresent()) {
			EventCordinator e=s.get();
			List<Long> k=new ArrayList<Long>();
			
			for(Quiz q:e.getQuizzesAdded()) {
				k.add(q.getId());}
			return new ResponseEntity<List<Long>>(k,HttpStatus.OK);
		}else if(faculty.isPresent()){
			Faculty e=faculty.get();
List<Long> k=new ArrayList<Long>();
			
			for(Quiz q:e.getQuizzesAdded()) {
				k.add(q.getId());}
			return new ResponseEntity<List<Long>>(k,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> getQuizName(Long quizId){
		Optional<Quiz> q=quizRepository.findById(quizId);
		if(q.isPresent()) {
			Quiz quiz=q.get();
			return new ResponseEntity<String>(quiz.getQuizName(), HttpStatus.OK);
	}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}

