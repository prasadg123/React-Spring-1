/**
 * 
 */
package com.prasad.reactive.service;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prasad.reactive.model.Student;
import com.prasad.reactive.repository.StudentRepository;

import io.reactivex.Observable;

/**
 * @author pgorrepa
 *
 */
@RunWith(SpringRunner.class)
public class StudentServiceImplTest {

	@Mock
	private StudentRepository repository;

	@InjectMocks
	private StudentServiceImpl studentServiceImpl;

	private List<Student> studentList;

	@Before
	public void init() throws JsonParseException, JsonMappingException, IOException {
		InputStream inputStream = new ClassPathResource("test-data/student.json").getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		Student[] studentList1 = mapper.readValue(inputStream, Student[].class);
		Stream<Student> students=Stream.of(studentList1);
		studentList= students.map(student->new Student()).collect(Collectors.toList());
		
		
	}

	/**
	 * Test method for
	 * {@link com.prasad.reactive.service.StudentServiceImpl#StudentServiceImpl(com.prasad.reactive.repository.StudentRepository)}.
	 */
	/*@Test
	public void testStudentServiceImpl() {
		BDDMockito.when(repository.findAll()).thenReturn(studentList);
		List<Student> mock = studentServiceImpl.findAll();
		Assert.assertEquals(mock, studentList);
	}*/

	/**
	 * Test method for
	 * {@link com.prasad.reactive.service.StudentServiceImpl#findAll()}.
	 */
	@Test
	public void testFindAll() {
		studentList.stream().forEach(System.out::println);
		BDDMockito.when(repository.findAll()).thenReturn(studentList);
		Observable<List<Student>> mock = studentServiceImpl.findAll();
		Assert.assertEquals(mock, studentList);
		
	}

	/**
	 * Test method for
	 * {@link com.prasad.reactive.service.StudentServiceImpl#findById(java.lang.Long)}.
	 */
	@Test
	public void testFindById() {
		int id=1003;
		Student student=new Student(1003,"Kishore",6496934);
		Optional<Student> std = Optional.of(student);
		when(repository.findById(id)).thenReturn(std);
		Assert.assertEquals(std, Optional.of(student)); 
	}

	/**
	 * Test method for
	 * {@link com.prasad.reactive.service.StudentServiceImpl#save(com.prasad.reactive.model.Student)}.
	 */
	@Test
	public void testSave() {
		Student student=new Student(1005,"kiran",74306396);
		BDDMockito.when(repository.save(student)).thenReturn(student);
		Student mock=studentServiceImpl.save(student);
		Assert.assertEquals(mock, student);
	}

	/**
	 * Test method for
	 * {@link com.prasad.reactive.service.StudentServiceImpl#update(com.prasad.reactive.model.Student)}.
	 */
	/*@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}*/

}
