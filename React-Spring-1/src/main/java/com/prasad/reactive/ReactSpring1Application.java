package com.prasad.reactive;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



import com.prasad.reactive.model.Student;
import com.prasad.reactive.repository.StudentRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@EnableGlobalMethodSecurity
//@EnableJpaRepositories(basePackages="com.prasad.reactor.repository")
public class ReactSpring1Application {

	
	public static void main(String[] args) {
		SpringApplication.run(ReactSpring1Application.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(StudentRepository repository) {
		
		return args ->{
			repository.save(new Student(1001,"Prasad",12749470));
			repository.save(new Student(1002,"Mahesh",127456654));
			repository.save(new Student(1003,"Naresh",127474577));
			repository.save(new Student(1004,"Ajay",127464777));
		};
	}
}
