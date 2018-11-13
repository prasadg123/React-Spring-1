package com.prasad.reactive.model;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="STUDENT")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Student implements Serializable{

	@Id
	private int id;
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName + ", passportNumber=" + passportNumber + "]";
	}

	private String studentName;
	
	private long passportNumber;
	
	public Student() {
		
	}
	
	public Student(int id, String studentName,long passportNumber) {
		this.id=id;
		this.studentName=studentName;
		this.passportNumber=passportNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Long getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(Long passportNumber) {
		this.passportNumber = passportNumber;
	}
	
	
}
