package com.prasad.reactive.testData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prasad.reactive.model.Student;

public class StudentTestData {

	@SuppressWarnings("unchecked")
	public static List<Student> fetchStudentData() throws IOException {
		
		InputStream inputStream=new ClassPathResource("test-data/student.json").getInputStream();
		ObjectMapper mapper=new ObjectMapper();
		List<Student> studentList=(List<Student>) mapper.readValue(inputStream, Student.class);
		return studentList;
	}
}
