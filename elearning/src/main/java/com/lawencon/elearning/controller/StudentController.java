package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.dto.InsertRes;
import com.lawencon.elearning.dto.UpdateRes;
import com.lawencon.elearning.dto.student.StudentData;
import com.lawencon.elearning.dto.student.StudentFindByIdRes;
import com.lawencon.elearning.dto.student.StudentInsertReq;
import com.lawencon.elearning.dto.student.StudentUpdateReq;
import com.lawencon.elearning.service.StudentService;
import com.lawencon.model.SearchQuery;

@RestController
@RequestMapping("students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "startPosition", required = false) Integer startPosition,
			@RequestParam(value = "limit", required = false) Integer limit) throws Exception {
		SearchQuery<StudentData> result = studentService.findAll(query, startPosition, limit);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) throws Exception {
		StudentFindByIdRes result = studentService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody StudentInsertReq data) throws Exception {
		InsertRes result = studentService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody StudentUpdateReq data) throws Exception {
		UpdateRes result = studentService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
