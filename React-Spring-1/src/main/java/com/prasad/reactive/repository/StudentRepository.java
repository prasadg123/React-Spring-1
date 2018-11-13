package com.prasad.reactive.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prasad.reactive.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	public Optional<Student> findById(int id);
}
