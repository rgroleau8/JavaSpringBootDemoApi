package com.example.DemoApi.factory;
import com.example.DemoApi.demo.services.*;

public class Factory {
	
	public static IPatientData getDatabase() {
		//gets the current instance from the singleton InMemoryPatientData
		return InMemoryPatientData.getInstance();
	}
}
