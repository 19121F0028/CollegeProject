package com.project.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.myapp.models.Batch;
import com.project.myapp.services.BatchService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/api/batch")
public class BatchController {
	@Autowired
	private BatchService batchService;
	@PostMapping(path="/addBatch")
	public ResponseEntity<?> addBatch(@RequestBody Batch batch){
		return batchService.create(batch);
	}
	@GetMapping(path="/getAllStudents/{id}")
	public ResponseEntity<?> getAllStudents(@PathVariable Long id){
		return batchService.getAllStudents(id);
	}
	@GetMapping(path="/getAllBatches")
	public ResponseEntity<?> getAllBatches(){
		return batchService.getAllBatches();
	}
}
