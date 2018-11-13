/**
 * 
 */
package com.prasad.reactive.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prasad.reactive.model.Student;
import com.prasad.reactive.repository.StudentRepository;
import com.prasad.reactive.service.StudentService;

import io.reactivex.Observable;

/**
 * @author pgorrepa
 *
 */
@RunWith(SpringRunner.class)
public class StudentControllerTest {

	@Mock
	private StudentService studentService;
	
	@Mock
	private StudentRepository repository;
	
	@InjectMocks
	private StudentController studentController;
	
	
	private List<Student> studentList;
	
	private MockMvc mockMvc;
	
	@Before
	public void init() throws JsonParseException, JsonMappingException, IOException {
		
		this.mockMvc=MockMvcBuilders.standaloneSetup(studentController).build();
		InputStream inputStream = new ClassPathResource("test-data/student.json").getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		Student[] studentList1 = mapper.readValue(inputStream, Student[].class);
		System.out.println(studentList1[1]);
		Stream<Student> students=Stream.of(studentList1);
		studentList= students.map(student->new Student(student.getId(),student.getStudentName(),student.getPassportNumber())).collect(Collectors.toList());
	}
	
	
	
	/**
	 * Test method for {@link com.prasad.reactive.controller.StudentController#findAll()}.
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testFindAll() throws Exception {
		
		when(studentService.findAll()).thenReturn((Observable<List<Student>>) studentList);
		studentList.stream().forEach(System.out::println);
		mockMvc.perform(get("/rest/students"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].id").value(1005))      
	            .andExpect(jsonPath("$[0].studentName").value("Kishore"))
	            .andExpect(jsonPath("$[0].passportNumber").value(127499847));
		verify(studentService,times(1)).findAll();
		verifyNoMoreInteractions(studentService);
	}

	/**
	 * Test method for {@link com.prasad.reactive.controller.StudentController#findByid(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	public void testFindByid() throws Exception {
		int id=1003;
		Student student=new Student(1003,"Kishore",6496934);
		Optional<Student> std = Optional.of(student);
		when(studentService.findById(id)).thenReturn(std);
		mockMvc.perform(get("/rest/{id}",1003))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id").value(1003))      
	            .andExpect(jsonPath("$.studentName").value("Kishore"))
	            .andExpect(jsonPath("$.passportNumber").value(6496934));
		verify(studentService, times(1)).findById(id);
	    verifyNoMoreInteractions(studentService);
	}

	/**
	 * Test method for {@link com.prasad.reactive.controller.StudentController#save(com.prasad.reactive.model.Student)}.
	 * @throws Exception 
	 */
	@Test
	public void testSave() throws Exception {
		Student student=new Student(1003,"Kishore",6496934);
		/*Optional<Student> std = Optional.of(student);*/
		when(studentService.save(student)).thenReturn(student);
		mockMvc.perform(post("/rest/"))
		.andExpect(status().is(400));
		/*.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").value(1003))      
        .andExpect(jsonPath("$.studentName").value("Kishore"))
        .andExpect(jsonPath("$.passportNumber").value(6496934));*/
		//verify(studentService, times(1)).save(student);
	    verifyNoMoreInteractions(studentService);
	}

}
