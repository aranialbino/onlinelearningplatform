package com.shikharani.onlinelearningplatform.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.shikharani.onlinelearningplatform.service.impl.StudentServiceImpl;

@SpringBootTest
public class StudentServiceImplTest {

	@Autowired
	StudentServiceImpl studentService;

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

	@Test
	public void test_enrollToCourse() {

		String email = "abs@email.com";
		String role = "ROLE_INSTRUCTOR";
		student = ModelMockGenerator.getUser(email, role);
		userRepo.save(student);

		department = ModelMockGenerator.getDepartment();
		departmentRepo.save(department);
		course = ModelMockGenerator.getCourse(department, student);
		// First create Department, then user(Instructor) and ln last, course.
		courseRepo.save(course);
		String startDate = "02/02/2024";
		MockHttpSession httpSession = new MockHttpSession();
		httpSession.setAttribute(Constants.USER_ID, student.getId());
		String forwardUrl = studentService.enrollToCourse(course.getId() + "", startDate, model, httpSession);
		assertNotNull(forwardUrl);
	}

	@AfterEach
	void cleanUp() {
		if (enrollment != null) {
			enrollmentRepo.delete(enrollment);
		}

		if (course != null && student != null) {
			enrollmentRepo.deleteByCourseIdAndStudentId(course.getId(), student.getId());
		}

		if (course != null)
			courseRepo.delete(course);
		if (student != null)
			userRepo.delete(student);
		if (department != null)
			departmentRepo.delete(department);
	}

}
