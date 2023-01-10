package com.example.DemoApi.demo.models;

import java.time.LocalDate;
import java.time.Period;

public class Patient {
	
	private Integer id;
	private String lastName;
	private String firstName;
	private LocalDate dateOfBirth;
	private Integer age;
	
	public Patient() {
		
	}
	
	public Patient(
			Integer id, 
			String lastName, 
			String firstName, 
			LocalDate dateOfBirth) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.age = CalcAge();
				
	}
	
	private Integer CalcAge() {
		return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		this.age = CalcAge();
	}

	public Integer getAge() {
		return age;
	}
	
	
		
		
	}
	
	

