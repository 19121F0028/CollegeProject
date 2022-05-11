package com.project.myapp.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.myapp.models.Batch;
import com.project.myapp.models.Student;
import com.project.myapp.payload.response.AllQuizResponse;
import com.project.myapp.payload.response.AllStudentResponse;
import com.project.myapp.payload.response.BatchRespose;
import com.project.myapp.repositories.BatchRepository;

@Service
public class BatchService {
@Autowired
private BatchRepository batchRepository;

public ResponseEntity<?> getAllBatches(){
	List<BatchRespose> l=new ArrayList<BatchRespose>();
	for(Batch b:batchRepository.findAll()) {
		l.add(new BatchRespose(b.getId(),b.getBatchName(),b.getStudents().size()));
	}
	return new ResponseEntity<List<BatchRespose>>(l,HttpStatus.OK);
}
public ResponseEntity<?> create(Batch batch){
	batchRepository.save(batch);
	return new ResponseEntity<>(HttpStatus.OK);
}
public ResponseEntity<?> getAllStudents(Long id){
	List<AllStudentResponse> l=new ArrayList<AllStudentResponse>();
Batch b=batchRepository.getById(id);
for(Student s:b.getStudents()) {
	l.add(new AllStudentResponse(s.getId(),s.getUsername(),s.getFirstName(),s.getLastName(),s.getBatch().getBatchName()));
}
l.sort(Comparator.comparing(AllStudentResponse::getRollNo));
return new ResponseEntity<List<AllStudentResponse>>(l,HttpStatus.OK);
}
}