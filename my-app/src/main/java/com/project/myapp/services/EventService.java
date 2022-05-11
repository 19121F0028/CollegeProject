package com.project.myapp.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.myapp.models.Batch;
import com.project.myapp.models.Event;
import com.project.myapp.models.EventDetails;
import com.project.myapp.models.EventResult;
import com.project.myapp.models.Faculty;
import com.project.myapp.models.Quiz;
import com.project.myapp.models.Student;
import com.project.myapp.models.StudentAttempt;
import com.project.myapp.payload.response.AllCoordinatorResponse;
import com.project.myapp.payload.response.AllEventsResponse;
import com.project.myapp.payload.response.AllRegisterStudentsResponse;
import com.project.myapp.payload.response.EventResponse;
import com.project.myapp.payload.response.EventResultResponse;
import com.project.myapp.payload.response.MessageResponse;
import com.project.myapp.repositories.BatchRepository;
import com.project.myapp.repositories.EventDetailsRepository;
import com.project.myapp.repositories.EventRepository;
import com.project.myapp.repositories.EventResultRepository;
import com.project.myapp.repositories.FacultyRepository;
import com.project.myapp.repositories.QuizRepository;
import com.project.myapp.repositories.StudentRepository;

@Service
@Transactional
public class EventService {
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private EventDetailsRepository eventDetailsRepository;
	@Autowired
	private BatchRepository batchRepository;
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private EventResultRepository eventResultRepository;
	public ResponseEntity<?> register(Long id,Long eventId){
		Optional<Student> k=studentRepository.findById(id);
		Optional<Faculty> f=facultyRepository.findById(id);
		if(k.isPresent())
		{
			Student student=k.get();
			Optional<Event> e=eventRepository.findById(eventId);
			if(e.isPresent()) {
				Event event=e.get();
				Date d=new Date();
				
				if(event.getStudentsRegistered().contains(student)) {
					return new ResponseEntity<String>("Already Registered For This Event",HttpStatus.OK);
				}
				if(d.after(event.getEventDetails().getRegistrationExpiresAt())) {
					return new ResponseEntity<String>("Registrations Expired",HttpStatus.OK);
				}
				student.addEventRegistered(event);
				eventRepository.save(event);
				return new ResponseEntity<String>("Registered Successfully",HttpStatus.OK);
			}
			else {
				return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: eventId not exists"));
			}
		}else if(f.isPresent()) {
			Faculty faculty=f.get();
			Optional<Event> e=eventRepository.findById(eventId);
			if(e.isPresent()) {
				Event event=e.get();
				Date d=new Date();
				
				if(event.getFacultyRegistered().contains(faculty)) {
					return new ResponseEntity<String>("Already Registered For This Event",HttpStatus.OK);
				}
				if(d.after(event.getEventDetails().getRegistrationExpiresAt())) {
					return new ResponseEntity<String>("Registrations Expired",HttpStatus.OK);
				}
				faculty.addEventRegistered(event);
				eventRepository.save(event);
				return new ResponseEntity<String>("Registered Successfully",HttpStatus.OK);
			}
			else {
				return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: eventId not exists"));
			}
		}
		else {
			return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: id not exists"));
		}
	}

	public ResponseEntity<?> addNewEvent(EventDetails e){
		EventDetails eventDetails=new EventDetails(e.getEventName(),e.getEventDescription(),e.getEventDate(),e.getEventType(),e.isQuiz());
		Set<Batch> b=e.getBatches();
		b.stream().forEach(k->{
			eventDetails.addBatch(k);
		});
		eventDetails.setRegistrationExpiresAt(e.getRegistrationExpiresAt());
		eventDetailsRepository.save(eventDetails);
		Event event=new Event();
		event.setEventDetails(eventDetails);
		eventRepository.save(event);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	public ResponseEntity<?> getAllRegisteredStudents(Long eventId){
		
		Optional<Event> e=eventRepository.findById(eventId);
		if(e.isPresent()) {
			Event event=e.get();
			Set<Student> registered=event.getStudentsRegistered();
			
			List<AllRegisterStudentsResponse> allRegisterStudentsResponses = new ArrayList<AllRegisterStudentsResponse>();
			registered.stream().forEach((k)
					->allRegisterStudentsResponses.add(new AllRegisterStudentsResponse(k.getId(),k.getUsername(), k.getFirstName(),k.getLastName(),k.getBatch().getBatchName())));
			
			return new ResponseEntity<List<AllRegisterStudentsResponse>>(allRegisterStudentsResponses,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
public ResponseEntity<?> getAllRegisteredFaculty(Long eventId){
		
		Optional<Event> e=eventRepository.findById(eventId);
		if(e.isPresent()) {
			Event event=e.get();
			Set<Faculty> registered=event.getFacultyRegistered();
			
			List<AllCoordinatorResponse> allRegisterStudentsResponses = new ArrayList<AllCoordinatorResponse>();
			registered.stream().forEach((k)
					->allRegisterStudentsResponses.add(new AllCoordinatorResponse(k.getId(), k.getUsername(), k.getFirstName(),k.getLastName())));
			
			return new ResponseEntity<List<AllCoordinatorResponse>>(allRegisterStudentsResponses,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
public ResponseEntity<?> getAllUnRegisteredStudents(Long eventId){
		
		Optional<Event> e=eventRepository.findById(eventId);
		if(e.isPresent()) {
			Event event=e.get();
			Set<Student> registered=event.getStudentsRegistered();
			List<Student> allStudents=new ArrayList<Student>();
			eventRepository.getById(eventId).getEventDetails().getBatches().stream().forEach(
					(k)->{
					allStudents.addAll(k.getStudents());
					}
					);
			List<AllRegisterStudentsResponse> allUnRegisterStudentsResponses = new ArrayList<AllRegisterStudentsResponse>();
			allStudents.stream().forEach((k)
					->{
						if(!registered.contains(k))
						allUnRegisterStudentsResponses.add(new AllRegisterStudentsResponse(k.getId(),k.getUsername(), k.getFirstName(),k.getLastName(),k.getBatch().getBatchName()));
						
					});
			
			return new ResponseEntity<List<AllRegisterStudentsResponse>>(allUnRegisterStudentsResponses,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
public ResponseEntity<?> getAllUnRegisteredFaculty(Long eventId){
	
	Optional<Event> e=eventRepository.findById(eventId);
	if(e.isPresent()) {
		Event event=e.get();
		Set<Faculty> registered=event.getFacultyRegistered();
		List<Faculty> allStudents=facultyRepository.findAll();
		List<AllCoordinatorResponse> allUnRegisterStudentsResponses = new ArrayList<AllCoordinatorResponse>();
		allStudents.stream().forEach((k)
				->{
					if(!registered.contains(k))
					allUnRegisterStudentsResponses.add(new AllCoordinatorResponse(k.getId(), k.getUsername(), k.getFirstName(),k.getLastName()));
					
				});
		
		return new ResponseEntity<List<AllCoordinatorResponse>>(allUnRegisterStudentsResponses,HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
	}
}
	public ResponseEntity<?> getAllEvents()
	
	{
		List<Event> allEvents=eventRepository.findAll();
		
		List<AllEventsResponse> all=new ArrayList<>();
		allEvents.stream().forEach((e)->{
			EventDetails k=e.getEventDetails();
			all.add(new AllEventsResponse(e.getId(),k.getEventName(),k.getEventType(),k.getEventDate()));
		}
				);
		return new ResponseEntity<List<AllEventsResponse>>(all,HttpStatus.OK);
	}
	public ResponseEntity<?> getEvent(Long id){
		Optional<Event> e=eventRepository.findById(id);
	
		if(e.isPresent()) {
			int count=0;
			Event event=e.get();
			EventDetails k=event.getEventDetails();
			List<String> a=new ArrayList<String>();
			for(Batch b:k.getBatches()) {
				count+=b.getStudents().size();
				a.add(b.getBatchName());
			}
			Long temp=facultyRepository.count();
			int temp1=(int) (temp-event.getFacultyRegistered().size());
			count=count-event.getStudentsRegistered().size();
			EventResponse eventResponse=new EventResponse(event.getId(),k.getEventName(),k.getEventDescription(),k.getEventDate(),k.getEventType(),k.isQuiz(),a,count,event.getStudentsRegistered().size(),event.getStudentsParticipated().size(),temp1,event.getFacultyRegistered().size(),event.getFacultyParticipated().size(),k.getRegistrationExpiresAt());
			if(k.getQuizId()!=null) {
			eventResponse.setQuizId(k.getQuizId());
			Quiz q=quizRepository.getById(k.getQuizId());
			eventResponse.setQuizName(q.getQuizName());}
			return new ResponseEntity<EventResponse>(eventResponse,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> makePartcipate(List<Long> list,Long id){
		Optional<Event> e=eventRepository.findById(id);
		if(e.isPresent()) {
			Event event=e.get();
			Set<Student> s=event.getStudentsParticipated();
			List<Student> s1=new ArrayList<Student>();
			s.stream().forEach((k)->{
				s1.add(k);});
		for(Student k:s1) {
			event.removeEventParticipated(k);
		}
		EventDetails ed=event.getEventDetails();
		List<EventResult> temp=ed.getEventResult();
		ed.setEventResult(new ArrayList<EventResult>());
		List<EventResult> a=new ArrayList<EventResult>();
			list.stream().forEach((k)->{
				Student s11=studentRepository.getById(k);
				studentRepository.findById(k).get().addEventParticipated(event);
				for(Faculty f:event.getFacultyParticipated()) {
					EventResult er=new EventResult(f.getId(),k,0,s11.getUsername());
					eventResultRepository.save(er);
					ed.getEventResult().add(er);
				}
				
			});
			eventDetailsRepository.save(ed);
			for(EventResult q:temp) {
				eventResultRepository.delete(q);
			}
			eventRepository.save(event);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> registerAll(List<Long> list,Long id){
		Optional<Event> e=eventRepository.findById(id);
		if(e.isPresent()) {
			Event event=e.get();
			list.stream().forEach((k)->{
				studentRepository.findById(k).get().addEventRegistered(event);
			});
			eventRepository.save(event);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> registerAllFaculty(List<Long> list,Long id){
		Optional<Event> e=eventRepository.findById(id);
		if(e.isPresent()) {
			Event event=e.get();
			list.stream().forEach((k)->{
				facultyRepository.findById(k).get().addEventRegistered(event);
			});
			eventRepository.save(event);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> makeFacultyPartcipate(List<Long> list,Long id){
		Optional<Event> e=eventRepository.findById(id);
		if(e.isPresent()) {
			Event event=e.get();
			Set<Faculty> s=event.getFacultyParticipated();
			List<Faculty> s1=new ArrayList<Faculty>();
			s.stream().forEach((k)->{
				s1.add(k);});
		for(Faculty k:s1) {
			event.removeEventParticipated(k);
		}
			list.stream().forEach((k)->{
				facultyRepository.findById(k).get().addEventParticipated(event);
				
			});
			eventRepository.save(event);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
public ResponseEntity<?> getAllParticipatedStudents(Long eventId){
		
		Optional<Event> e=eventRepository.findById(eventId);
		if(e.isPresent()) {
			Event event=e.get();
			Set<Student> registered=event.getStudentsParticipated();
			
			List<AllRegisterStudentsResponse> allRegisterStudentsResponses = new ArrayList<AllRegisterStudentsResponse>();
			registered.stream().forEach((k)
					->allRegisterStudentsResponses.add(new AllRegisterStudentsResponse(k.getId(),k.getUsername(), k.getFirstName(),k.getLastName(),k.getBatch().getBatchName())));
			
			return new ResponseEntity<List<AllRegisterStudentsResponse>>(allRegisterStudentsResponses,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
public ResponseEntity<?> getAllParticipatedFaculty(Long eventId){
	
	Optional<Event> e=eventRepository.findById(eventId);
	if(e.isPresent()) {
		Event event=e.get();
		Set<Faculty> registered=event.getFacultyParticipated();
		
		List<AllCoordinatorResponse> allRegisterStudentsResponses = new ArrayList<AllCoordinatorResponse>();
		registered.stream().forEach((k)
				->allRegisterStudentsResponses.add(new AllCoordinatorResponse(k.getId(),k.getUsername(), k.getFirstName(),k.getLastName())));
		
		return new ResponseEntity<List<AllCoordinatorResponse>>(allRegisterStudentsResponses,HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
	}
}
public ResponseEntity<?> getAllEventsByBatch(Long id){
	Optional<Batch> b=batchRepository.findById(id);
	if(b.isPresent()) {
		Batch batch=b.get();
		return new ResponseEntity<Set<EventDetails>>(batch.getEventsRegistered(),HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
	}
}
public ResponseEntity<?> getAllEvents(Long id){
	Optional<Student> s=studentRepository.findById(id);
	Optional<Faculty> f=facultyRepository.findById(id);
	if(s.isPresent()) {
		Student student=s.get();
		Batch batch=batchRepository.getById(student.getBatch().getId());
		Set<EventDetails> registered=batch.getEventsRegistered();
		
		List<AllEventsResponse> a = new ArrayList<AllEventsResponse>();
		registered.stream().forEach((k)
				->{
					Event event=eventRepository.findByEventDetails(k).get();
					if(!student.getEventsRegistered().contains(event)) {
						Long a1=event.getId();
						a.add(new AllEventsResponse(a1,k.getEventName(),k.getEventType(),k.getEventDate()));
					}
					});
		
		return new ResponseEntity<List<AllEventsResponse>>(a,HttpStatus.OK);
	}else if(f.isPresent()) {
		Faculty faculty=f.get();
		List<EventDetails> registered=eventDetailsRepository.findAll();
		
		List<AllEventsResponse> a = new ArrayList<AllEventsResponse>();
		registered.stream().forEach((k)
				->{
					Event event=eventRepository.findByEventDetails(k).get();
					if(!faculty.getEventsRegistered().contains(event)) {
						Long a1=event.getId();
						a.add(new AllEventsResponse(a1,k.getEventName(),k.getEventType(),k.getEventDate()));
					}
					});
		
		return new ResponseEntity<List<AllEventsResponse>>(a,HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>("Student Id NOT FOUND",HttpStatus.NOT_FOUND);
	}
}

public ResponseEntity<?> getAllRegisteredEvents(Long id){
	
	Optional<Student> s=studentRepository.findById(id);
	Optional<Faculty> f=facultyRepository.findById(id);
	if(s.isPresent()) {
		Student student=s.get();
		Set<Event> registered=student.getEventsRegistered();
		
		List<AllEventsResponse> a = new ArrayList<AllEventsResponse>();
		registered.stream().forEach((k)
				->a.add(new AllEventsResponse(k.getId(),k.getEventDetails().getEventName(),k.getEventDetails().getEventType(),k.getEventDetails().getEventDate())));
		
		return new ResponseEntity<List<AllEventsResponse>>(a,HttpStatus.OK);
	}else if(f.isPresent()) {
		Faculty faculty=f.get();
Set<Event> registered=faculty.getEventsRegistered();
		
		List<AllEventsResponse> a = new ArrayList<AllEventsResponse>();
		registered.stream().forEach((k)
				->a.add(new AllEventsResponse(k.getId(),k.getEventDetails().getEventName(),k.getEventDetails().getEventType(),k.getEventDetails().getEventDate())));
		
		return new ResponseEntity<List<AllEventsResponse>>(a,HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
	}
}
public ResponseEntity<?> getAllParticipatedEvents(Long id){
	
	Optional<Student> s=studentRepository.findById(id);
	Optional<Faculty> f=facultyRepository.findById(id);
	if(s.isPresent()) {
		Student student=s.get();
		Set<Event> registered=student.getEventsParticipated();
		
		List<AllEventsResponse> a = new ArrayList<AllEventsResponse>();
		registered.stream().forEach((k)
				->{
				a.add(new AllEventsResponse(k.getId(),k.getEventDetails().getEventName(),k.getEventDetails().getEventType(),k.getEventDetails().getEventDate()));});
		
		return new ResponseEntity<List<AllEventsResponse>>(a,HttpStatus.OK);
	}else if(f.isPresent()) {
		Faculty faculty=f.get();
		Set<Event> registered=faculty.getEventsParticipated();
		
		List<AllEventsResponse> a = new ArrayList<AllEventsResponse>();
		registered.stream().forEach((k)
				->{
				a.add(new AllEventsResponse(k.getId(),k.getEventDetails().getEventName(),k.getEventDetails().getEventType(),k.getEventDetails().getEventDate()));});
		
		return new ResponseEntity<List<AllEventsResponse>>(a,HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>("Event Id NOT FOUND",HttpStatus.NOT_FOUND);
	}
}
public ResponseEntity<?> addQuiz(Long eventId,Long quizId)
{
	Optional<Event> e=eventRepository.findById(eventId);
	
	if(e.isPresent()) {
		Event k=e.get();
		EventDetails k1=k.getEventDetails();
		k1.setQuizId(quizId);
		eventDetailsRepository.save(k1);
		return new ResponseEntity<String>("Quiz Triggerred for the event Successfully",HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
public ResponseEntity<?> changeScore(Long eventId,Long facultyId,Long studentId,int score){
	
Optional<Event> e=eventRepository.findById(eventId);
	
	if(e.isPresent()) {
		Event k=e.get();
		EventDetails k1=k.getEventDetails();
		List<EventResult> a=k1.getEventResult();
		for(EventResult r:a) {
			if(r.getFacultyId()==facultyId && r.getStudentId()==studentId) {
				r.setMarks(score);
			}
			
		}
		return new ResponseEntity<String>("Score Updated Successfully",HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
public ResponseEntity<?> getEventResultByFaculty(Long facultyId,Long eventId){
Optional<Event> e=eventRepository.findById(eventId);
	
	if(e.isPresent()) {
		Event k=e.get();
		EventDetails k1=k.getEventDetails();
		List<EventResult> a=new ArrayList<EventResult>();
		
		for(EventResult r:k1.getEventResult()) {
			if(r.getFacultyId()==facultyId) {
				a.add(r);
			}
			
		}
		return new ResponseEntity<List<EventResult>>(a,HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
public ResponseEntity<?> getEventResult(Long eventId){
Optional<Event> e=eventRepository.findById(eventId);
	
	if(e.isPresent()) {
		Event k=e.get();
		EventDetails k1=k.getEventDetails();
		List<EventResultResponse> a=new ArrayList<EventResultResponse>();
		List<EventResult> r=k1.getEventResult();
		for(Student s:k.getStudentsParticipated()) {
			int sum=0;
			for(EventResult m:r) {
				if(m.getStudentId()==s.getId()) {
					sum+=m.getMarks();
				}
			}
			a.add(new EventResultResponse(s.getUsername(), sum));
		}
		Comparator<EventResultResponse> scoreComparator = Comparator
                .comparing(EventResultResponse::getScore).reversed();
		Collections.sort(a, scoreComparator);
		return new ResponseEntity<List<EventResultResponse>>(a,HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
public ResponseEntity<?> getEventName(Long eventId){
Optional<Event> e=eventRepository.findById(eventId);
	
	if(e.isPresent()) {
		Event k=e.get();
		EventDetails k1=k.getEventDetails();
		return new ResponseEntity<String>(k1.getEventName(),HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
}
