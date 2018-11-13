package com.prasad.reactive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasad.reactive.model.Student;
import com.prasad.reactive.repository.StudentRepository;

import io.reactivex.Observable;


@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository repository;
	
	@Override
	public Observable<List<Student>> findAll() {

		return Observable.just(repository.findAll());
	}

	@Override
	public Optional<Student> findById(int id) {
	
		return repository.findById(id);
	}

	@Override
	public Student save(Student student) {
		
		return repository.save(student);
	}

	@Override
	public Student update(Student student){
		
		return repository.save(student);
	}

}
