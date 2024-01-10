package com.shikharani.onlinelearningplatform.dto;

import lombok.Data;

@Data
public class CourseElection {

	private Long courseId;
	private Long departmentId;
	private String departmentName;
	private String title;
	private String description;
	private String instructor;
	private String duration;
	// DTOs are commonly used in applications to encapsulate and transfer data
	// between different layers
	// (e.g., between controllers and services or between services and
	// repositories). They help in keeping
	// the data structure clean, providing a clear contract for communication, and
	// avoiding tight coupling
	// between different parts of the system.//
}
