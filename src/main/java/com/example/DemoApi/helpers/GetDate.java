package com.example.DemoApi.helpers;

import java.time.LocalDate;
import java.util.Map;

public final class GetDate {

	public static LocalDate convertResponseBodyToDate(Map<String, String> body) {
		
		Integer year = Integer.parseInt(body.get("year"));
		Integer month = Integer.parseInt(body.get("month"));
		Integer day = Integer.parseInt(body.get("day"));
		
		LocalDate output = LocalDate.of(year, month, day);
		
		return output;
		
	}
	
}
