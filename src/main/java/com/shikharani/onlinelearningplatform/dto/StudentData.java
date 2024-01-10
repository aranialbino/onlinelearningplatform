package com.shikharani.onlinelearningplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StudentData {
	int serialNum;
	Long courseId;
	String title;
	String department;
	String description;
	String instructor;
	String startDate;
	String progress;
}// DTOs are commonly used in applications to encapsulate and transfer data
	// between
//different layers (e.g., between controllers and services or between services and repositories). 
//They help in keeping the data structure clean, providing a clear contract for communication,
//and avoiding tight coupling between different parts of the system.
