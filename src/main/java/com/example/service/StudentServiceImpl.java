package com.example.service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Student;
import com.example.repository.StudentRepository;

@Service
public class StudentServiceImpl {

	@Autowired
	private StudentRepository repo;
	
	public Collection<Student> getAllStudents(){
		return repo.findAll();
	}
	
	public Student saveStudent(Student student) {
		
		
		Student std = repo.save(student);
		
		return std;
	}
	
	public Student getStudentById(UUID studentId) {
		Optional<Student> option = repo.findById(studentId);
		
		Student student = null;
		
		if(option.isPresent()) {
			student = option.get();
			
		}
		
		return student;
	}
	
	public Student updateStudent(UUID studentId, Student student) {
		Optional<Student> option = repo.findById(studentId);
		
		Student std = new Student();
		
		if(option.isPresent()) {
			std = option.get();
			
			std.setDepartment(student.getDepartment());
			std.setEmail(student.getEmail());
			std.setFirstname(student.getFirstname());
			std.setLastname(student.getLastname());
			std.setPassword(student.getPassword());
			
					
		}
		std = repo.save(std);	
		
		return std;
	}
	
	public void deleteStudent(UUID studentId) {
		repo.deleteById(studentId);
	}
}
