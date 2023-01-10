package com.example.DemoApi.demo.controllers;

import com.example.DemoApi.demo.models.Patient;
import com.example.DemoApi.demo.services.*;
import com.example.DemoApi.factory.Factory;
import com.example.DemoApi.helpers.GetDate;
import com.example.DemoApi.factory.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/patient")
public class PatientController {

	private IPatientData db;
	
	public PatientController() {
		this.db = Factory.getDatabase();
	}
	
	@GetMapping(path = "/getpatient/{id}")
	public Patient getPatient(@PathVariable("id") int id) {
		
		//responds with the single patient matching the id, will return null if the patient doesn't exist
		return db.getPatient(id);
		
	}
	
	@GetMapping(path = "/getallpatients")
	public List<Patient> getAllPatients(){
		//responds with everything in the database
		return db.getAllPatients();
	}
	
	@PutMapping(path = "/update/{id}")
	public Patient updatePatient(@PathVariable("id") Integer id,
								@RequestBody Map<String, String> body) {
		
		//checks if a patient with the id exists
		if (db.doesPatientExist(id) == false) {
			return null;
		}
		
		//gets the patient needing updating
		Patient pt = db.getPatient(id);
		
		//checks if the json response contains a field for lastName
		if (body.containsKey("lastName")) {
			pt.setLastName(body.get("lastName"));
		}
		
		//checks if the json response contains a field for firstName
		if (body.containsKey("firstName")) {
			pt.setFirstName(body.get("firstName"));
		}
		
		//checks if the json response contains all date information
		if (body.containsKey("year") && (body.containsKey("month")) && (body.containsKey("day"))) {
			
			//age is set in the setter for dateOfBirth
			pt.setDateOfBirth(GetDate.convertResponseBodyToDate(body));
		}
	
		//updates the patient in the database
		db.updatePatient(pt);
		
		//sends the updated patient as a response
		return pt;
		
	}

	@PostMapping(path = "/newpatient")
	public boolean addPatient(@RequestBody Map<String, String> body) {
		
		
		Integer id = Integer.parseInt(body.get("id"));
		
		//checks if a patient can be created with the given id
		if (db.doesPatientExist(id) == true) {
			return false;
		}
		
		String lastName = body.get("lastName");
		String firstName = body.get("firstName");
		LocalDate dob = GetDate.convertResponseBodyToDate(body);
		
		//creates a new Patient object to be added to the database
		Patient pt = new Patient(id, lastName, firstName, dob);
	
		//adds patient to the database
		db.addPatient(pt);
		
		//returns a response that the patient was successfully added
		return true;
	}
	
	
	@DeleteMapping(path = "delete/{id}")
	public boolean deletePatient(@PathVariable("id") Integer id) {
		
		//initial check if patient to be deleted actually exists
		if (db.doesPatientExist(id) == true) {
			db.deletePatient(id);
			return true;
		}
		
		//patient didn't exist so response returns failed
		return false;
			
		
	}
		
	
	
	
}
