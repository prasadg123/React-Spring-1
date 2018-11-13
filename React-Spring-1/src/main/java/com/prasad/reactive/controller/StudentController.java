package com.prasad.reactive.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasad.reactive.model.Student;
import com.prasad.reactive.service.StudentService;

import io.reactivex.Observable;



@RestController
@RequestMapping("/rest")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService=studentService;
	}
	
	
	@GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
	public Observable<List<Student>> findAll(){
		
		return studentService.findAll();
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Student> findByid(@PathVariable("id") int id) {
		
		return studentService.findById(id);
	}
	
	@PostMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Student> save(@RequestBody Student student) {
		
		studentService.save(student);
		return studentService.findById(student.getId());
	}
	
	/*@PutMapping("/")
	public Optional<Student> update(@RequestBody Student student){
		
		Optional<Student> oldStudent=studentService.findById(student.getId());
		oldStudent.setStudentName(student.getStudentName());
		oldStudent.setPassportNumber(student.getPassportNumber());
		return studentService.save(oldStudent);
	}*/
}
