package com.example.DemoApi.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.DemoApi.demo.models.Patient;

public interface IPatientData {

	boolean doesPatientExist(Integer id);
	
	Patient getPatient(Integer id);
	
	List<Patient> getAllPatients();
	
	void addPatient(Patient pt);
	
	void updatePatient(Patient pt);
	
	void deletePatient(Integer id);

}