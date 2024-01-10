package com.shikharani.onlinelearningplatform.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;

import com.shikharani.onlinelearningplatform.constants.Constants;
import com.shikharani.onlinelearningplatform.model.Course;
import com.shikharani.onlinelearningplatform.model.Department;
import com.shikharani.onlinelearningplatform.model.Enrollment;
import com.shikharani.onlinelearningplatform.model.User;
import com.shikharani.onlinelearningplatform.repository.CourseRepository;
import com.shikharani.onlinelearningplatform.repository.DepartmentRepository;
import com.shikharani.onlinelearningplatform.repository.EnrollmentRepository;
import com.shikharani.onlinelearningplatform.repository.ModelMockGenerator;
import com.shikharani.onlinelearningplatform.repository.UserRepository;
import com.shikharani.onlinelearningplatform.service.impl.InstructorServiceImpl;

@SpringBootTest
public class InstructorServiceImplTest {

	@Autowired
	
	EnrollmentRepository enrollmentRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	DepartmentRepository departmentRepo;

	@Autowired
	CourseRepository courseRepo;

	@Mock
	Model model;

	Enrollment enrollment = null;
	User student = null;
	Department department;
	Course course = null;

	@Autowired
	InstructorServiceImpl instructorService;

	@Test
	void test_updateProgressOfStudent() {
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
		MockHttpSession httpSession = new MockHttpSession();
		httpSession.setAttribute(Constants.USER_ID, student.getId());
		instructorService.updateProgressOfStudent(student.getId() + "", course.getId() + "", "12", model, httpSession);
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
