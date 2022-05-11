package com.project.myapp.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.myapp.models.Batch;
import com.project.myapp.models.Event;
import com.project.myapp.models.EventDetails;
import com.project.myapp.models.EventResult;
import com.project.myapp.payload.request.EventAddRequest;
import com.project.myapp.repositories.BatchRepository;
import com.project.myapp.repositories.EventDetailsRepository;
import com.project.myapp.services.EventService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/api/event")
public class EventController {
	@Autowired
	private EventService eventService;
	@Autowired
private EventDetailsRepository eventDetailsRepository;
	@Autowired
	private BatchRepository batchRepository;
	@PostMapping(path="/addNewEvent")
	public ResponseEntity<?> addNewEvent(@RequestBody EventDetails e) {
		e.getBatches().stream().forEach((e1)->{
			System.out.println(e1.getBatchName());
			Batch batch=batchRepository.findByBatchName(e1.getBatchName());
			e1.setId(batch.getId());
		}
				);
		//return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		return eventService.addNewEvent(e);
	}
	@GetMapping("/register/{id}/{eventId}")
	public ResponseEntity<?> register(@PathVariable("id") Long id,@PathVariable("eventId") Long eventId) {	
		return eventService.register(id, eventId);
	}
	
	@GetMapping("/getRegisteredStudents/{eventId}")
	public ResponseEntity<?> getStudents(@PathVariable("eventId") Long eventId){
		return eventService.getAllRegisteredStudents(eventId);
	}
	@GetMapping("/getRegisteredFaculty/{eventId}")
	public ResponseEntity<?> getFaculty(@PathVariable("eventId") Long eventId){
		return eventService.getAllRegisteredFaculty(eventId);
	}
	@GetMapping("/getUnRegisteredFaculty/{eventId}")
	public ResponseEntity<?> get(@PathVariable("eventId") Long eventId){
		return eventService.getAllUnRegisteredFaculty(eventId);
	}
	@GetMapping("/getUnRegisteredStudents/{eventId}")
	public ResponseEntity<?> getStudent(@PathVariable("eventId") Long eventId){
		return eventService.getAllUnRegisteredStudents(eventId);
	}
	@GetMapping("/getAllEvents")
	public ResponseEntity<?> getAllEvents(){
		return eventService.getAllEvents();
	}
	@GetMapping("/getEvent/{id}")
	public ResponseEntity<?> getEvent(@PathVariable("id") Long id){
		return eventService.getEvent(id);
	}
	@PostMapping("/makePartcipated/{id}")
	public ResponseEntity<?> makeParticpated(@RequestBody List<Long> students,@PathVariable Long id)
	{
		return eventService.makePartcipate(students,id);
	}
	@PostMapping("/registerAll/{id}")
	public ResponseEntity<?> registerAll(@RequestBody List<Long> students,@PathVariable Long id)
	{
		return eventService.registerAll(students,id);
	}
	@PostMapping("/makeFacultyPartcipated/{id}")
	public ResponseEntity<?> makeFacultyParticpated(@RequestBody List<Long> students,@PathVariable Long id)
	{
		return eventService.makeFacultyPartcipate(students,id);
	}
	@PostMapping("/registerAllFaculty/{id}")
	public ResponseEntity<?> registerAllFaculty(@RequestBody List<Long> students,@PathVariable Long id)
	{
		return eventService.registerAllFaculty(students,id);
	}
	@GetMapping("/getPartcipatedStudents/{eventId}")
	public ResponseEntity<?> getStudentsPart(@PathVariable("eventId") Long eventId){
		return eventService.getAllParticipatedStudents(eventId);
	}
	@GetMapping("/getPartcipatedFaculty/{eventId}")
	public ResponseEntity<?> getFacullty(@PathVariable("eventId") Long eventId){
		return eventService.getAllParticipatedFaculty(eventId);
	}
	@GetMapping("/getEventsByBatch/{id}")
	public ResponseEntity<?> getEventsByBatch(@PathVariable("id") Long id){
		return eventService.getAllEventsByBatch(id);
	}
	@GetMapping("getAllEvents/{id}")
	public ResponseEntity<?> getAllEvents(@PathVariable Long id){
		return eventService.getAllEvents(id);
	}
	@GetMapping("getAllRegisteredEvents/{id}")
	public ResponseEntity<?> getAllRegisteredEvents(@PathVariable Long id){
		return eventService.getAllRegisteredEvents(id);
	}
	@GetMapping("getAllParticipatedEvents/{id}")
	public ResponseEntity<?> getAllParticipatedEvents(@PathVariable Long id){
		return eventService.getAllParticipatedEvents(id);
	}
	@GetMapping("/addQuiz/{eventId}/{quizId}")
	public ResponseEntity<?> addQuiz(@PathVariable("eventId") Long eventId,@PathVariable("quizId") Long quizId) {	
		return eventService.addQuiz(eventId,quizId);
	}
	@PostMapping("/changeScore/{eventId}")
	public ResponseEntity<?> changeScore(@PathVariable("eventId") Long eventId, @RequestBody EventResult e) {
//		System.out.println(eventId);
//		System.out.println(e.getFacultyId());
//		System.out.println(e.getStudentId());
		return eventService.changeScore(eventId, e.getFacultyId(),e.getStudentId(),e.getMarks());
	}
	@GetMapping("/getEventResultByFaculty/{eventId}/{facultyId}")
	public ResponseEntity<?> changeScore(@PathVariable("eventId") Long eventId, @PathVariable("facultyId") Long facultyId) {
		return eventService.getEventResultByFaculty(facultyId, eventId);
	}
	@GetMapping("/getEventResults/{eventId}")
	public ResponseEntity<?> changeScore(@PathVariable("eventId") Long eventId) {
		return eventService.getEventResult(eventId);
	}
	@GetMapping("/getEventName/{eventId}")
	public ResponseEntity<?> getEventName(@PathVariable("eventId") Long eventId) {
		return eventService.getEventName(eventId);
	}
	@GetMapping(path="/checkEventName/{name}")
	public boolean checkEventName(@PathVariable("name") String name) {
		return eventDetailsRepository.existsByEventName(name);
	}
}
