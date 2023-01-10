package com.example.DemoApi.demo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.bind.annotation.PostMapping;

import com.example.DemoApi.demo.models.Patient;




public class InMemoryPatientData implements IPatientData {

	private CopyOnWriteArrayList<Patient> patientList;
	
	private static Object lock = new Object();
	
	private static IPatientData instance = null;
	
	
	public InMemoryPatientData() {
		
		
		patientList = new CopyOnWriteArrayList<Patient>();
		patientList.add(new Patient(1, "Smith", "John", LocalDate.of(2000, 1, 3)));
		patientList.add(new Patient(2, "Potter", "Harry", LocalDate.of(1998, 1, 16)));
		patientList.add(new Patient(3, "Weasly", "Ron", LocalDate.of(1997, 3, 4)));
		
	}
	
	
	
	/**
	 *Checks if the id returns a null patient, if so returns false
	 */
	public boolean doesPatientExist(Integer id) {
		if (getPatient(id) == null) {
			return false;
		}
		
		return true;
	}

	
	/**
	 *@return Returns the patient matching the given id, will return null if no patient matching that id exists
	 */
	public Patient getPatient(Integer id) {
		
		return patientList.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
		
	}
	
	
	
	/**
	 *@return Returns the entire list of patients
	 */
	public List<Patient> getAllPatients(){
		return patientList;
	}
	
	
	
	/**
	 *Currently does noting as the object in the list has already been modified
	 */
	public void updatePatient(Patient pt) {
		//no code to update patient with the InMemoryDatabase
		//the object has already been altered via the object in the controller pointing to the one in the list
		//if database changes, code may need to be added, code in PatientController shouldnt need to be altered as 
		//db.updatePatient is being called
	}
	
	
	
	/**
	 *Adds the passed in patient to the list
	 */
	public void addPatient(Patient pt) {
		
		patientList.add(pt);
		
	}
	
	
	/**
	 *Gets the patient based on id and removes the object from the list
	 */
	public void deletePatient(Integer id) {
		Patient pt = getPatient(id);
		patientList.remove(pt);
	}
	
	
	
	/**
	 * @return A thread-safe method to return the private instance
	 */
	public static IPatientData getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new InMemoryPatientData();
				}
				
			}
		}
		
		return instance;
	}
	
	
	
}
