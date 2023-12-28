package com.example.controller;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.service.StudentServiceImpl;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/app")
public class StudentController {

	@Autowired
	private StudentServiceImpl service;
	
	@Timed
	@GetMapping("/findall")
	public ResponseEntity<Object> findAllStudents(){
		Collection<Student> student = service.getAllStudents();
		
		return new ResponseEntity<Object>(student, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> registerStudent(@RequestBody Student student){
		
		student.setId(UUID.randomUUID());
		
		Student std = service.saveStudent(student);
		
		return new ResponseEntity<Object>(std, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{studentId}")
	public ResponseEntity<Object> updateStudent(@PathVariable String studentId, @RequestBody Student student){
		UUID id = UUID.fromString(studentId);
		
		Student std = service.updateStudent(id, student);
		
		return new ResponseEntity<Object>(std, HttpStatus.OK);
	}
	
	@Counted @Timed
	@GetMapping("/student/{studentId}")
	public ResponseEntity<Object> findStudentById(@PathVariable String studentId){
		
		UUID id = UUID.fromString(studentId);
		
		Student stud = service.getStudentById(id);
		
		if(stud == null) {
			return new ResponseEntity<Object>("Record not found", HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Object>(stud, HttpStatus.OK);
		}
		
		
	}
	
	@DeleteMapping("/remove/{studentid}")
	public ResponseEntity<Object> removeStudent(@PathVariable String studentid){
		UUID id = UUID.fromString(studentid);
		
		service.deleteStudent(id);
		
		return new ResponseEntity<Object>("Removed Student Successfully", HttpStatus.OK);
	}
}
