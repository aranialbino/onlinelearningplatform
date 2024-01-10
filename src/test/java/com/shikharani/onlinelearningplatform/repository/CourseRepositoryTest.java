package com.shikharani.onlinelearningplatform.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shikharani.onlinelearningplatform.model.Course;
import com.shikharani.onlinelearningplatform.model.Department;
import com.shikharani.onlinelearningplatform.model.User;

@SpringBootTest
public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	DepartmentRepository departmentRepo;

	Department department = null;
	User instructor = null;
	Course course = null;

	@Test
	void test_getCourseByInstructor() {
		String email = "abcd@email.com";
		String role = "ROLE_INSTRUCTOR";
		department = ModelMockGenerator.getDepartment();
		departmentRepo.save(department);
		instructor = ModelMockGenerator.getUser(email, role);
		userRepo.save(instructor);
		course = ModelMockGenerator.getCourse(department, instructor);
		// First create Department, then user(Instructor) and ln last, course.
		courseRepo.save(course);
		List<Course> coursesByInstructor = courseRepo.getCourseByInstructor(instructor.getId());
		assertNotNull(coursesByInstructor);
		assertEquals(coursesByInstructor.size(), 1);

	}

	@AfterEach
	void cleanUp() {
		if (course != null)
			courseRepo.delete(course);
		if (instructor != null)
			userRepo.delete(instructor);
		if (department != null)
			departmentRepo.delete(department);
	}

}
