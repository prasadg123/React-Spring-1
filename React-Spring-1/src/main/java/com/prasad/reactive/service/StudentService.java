package com.prasad.reactive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prasad.reactive.model.Student;

import io.reactivex.Observable;



@Service
public interface StudentService {

	public Observable<List<Student>> findAll();
	
	public Optional<Student> findById(int id);
	
	public Student save(Student student);
	
	public Student  update(Student student);
}
