package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {

}
