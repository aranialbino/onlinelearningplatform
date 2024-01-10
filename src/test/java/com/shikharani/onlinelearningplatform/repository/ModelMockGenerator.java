package com.shikharani.onlinelearningplatform.repository;

import java.util.Date;

import com.shikharani.onlinelearningplatform.model.Course;
import com.shikharani.onlinelearningplatform.model.Department;
import com.shikharani.onlinelearningplatform.model.Enrollment;
import com.shikharani.onlinelearningplatform.model.User;

public class ModelMockGenerator {

	public static User getUser(String email, String role) {
		return new User(null, email, "pass", "test_fname", "test_lname", "123", "2324", role, null, null);
	}

	public static Course getCourse(Department department, User instructor) {
		return new Course(null, "Sample title", "Sample desc", "12", 7, department, instructor, null);
	}

	public static Department getDepartment() {
		return new Department(null, "Department name", "Desc", "ABC", 1975, null);
	}

	public static Enrollment getEnrollment(User instructor, Course course) {
		return new Enrollment(null, instructor, course, new Date(), 0);

	}

}
