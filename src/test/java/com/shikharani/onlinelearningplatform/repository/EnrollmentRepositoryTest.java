package com.shikharani.onlinelearningplatform.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shikharani.onlinelearningplatform.model.Course;
import com.shikharani.onlinelearningplatform.model.Department;
import com.shikharani.onlinelearningplatform.model.Enrollment;
import com.shikharani.onlinelearningplatform.model.User;

@SpringBootTest
public class EnrollmentRepositoryTest {
	@Autowired
	EnrollmentRepository enrollmentRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	DepartmentRepository departmentRepo;

	@Autowired
	CourseRepository courseRepo;

	Enrollment enrollment = null;
	User student = null;
	Department department;
	Course course = null;

	@Test
	public void test_findByCourseId() {

		String email = "abs@email.com";
		String role = "ROLE_INSTRUCTOR";
		student = ModelMockGenerator.getUser(email, role);
		userRepo.save(student);

		department = ModelMockGenerator.getDepartment();
		departmentRepo.save(department);
		course = ModelMockGenerator.getCourse(department, student);
		// First create Department, then user(Instructor) and ln last, course.
		courseRepo.save(course);
		enrollment = ModelMockGenerator.getEnrollment(student, course);
		enrollmentRepo.save(enrollment);

		List<Enrollment> enrollment = enrollmentRepo.findByCourseId(course.getId());
		assertNotNull(enrollment);
		assertEquals(1, enrollment.size());
	}

	@Test
	public void test_findByCourseIdAndStudentId() {

		String email = "abs@email.com";
		String role = "ROLE_INSTRUCTOR";
		student = ModelMockGenerator.getUser(email, role);
		userRepo.save(student);

		department = ModelMockGenerator.getDepartment();
		departmentRepo.save(department);
		course = ModelMockGenerator.getCourse(department, student);
		// First create Department, then user(Instructor) and ln last, course.
		courseRepo.save(course);
		enrollment = ModelMockGenerator.getEnrollment(student, course);
		enrollmentRepo.save(enrollment);

		List<Enrollment> enrollment = enrollmentRepo.findByCourseIdAndStudentId(course.getId(), student.getId());
		assertNotNull(enrollment);
		assertEquals(1, enrollment.size());
	}

	@Test
	public void test_deleteByCourseIdAndStudentId() {

		String email = "abs@email.com";
		String role = "ROLE_INSTRUCTOR";
		student = ModelMockGenerator.getUser(email, role);
		userRepo.save(student);

		department = ModelMockGenerator.getDepartment();
		departmentRepo.save(department);
		course = ModelMockGenerator.getCourse(department, student);
		// First create Department, then user(Instructor) and ln last, course.
		courseRepo.save(course);
		enrollment = ModelMockGenerator.getEnrollment(student, course);
		enrollmentRepo.save(enrollment);

		int count = enrollmentRepo.deleteByCourseIdAndStudentId(course.getId(), student.getId());
		assertNotNull(enrollment);
		assertEquals(1, count);
	}

	@AfterEach
	void cleanUp() {
		if (enrollment != null)
			enrollmentRepo.delete(enrollment);
		if (course != null)
			courseRepo.delete(course);
		if (student != null)
			userRepo.delete(student);
		if (department != null)
			departmentRepo.delete(department);
	}
}
