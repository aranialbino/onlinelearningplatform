package com.shikharani.onlinelearningplatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shikharani.onlinelearningplatform.model.Course;
import com.shikharani.onlinelearningplatform.model.Department;
import com.shikharani.onlinelearningplatform.model.User;
import com.shikharani.onlinelearningplatform.repository.CourseRepository;
import com.shikharani.onlinelearningplatform.repository.DepartmentRepository;
import com.shikharani.onlinelearningplatform.service.CommonService;
import com.shikharani.onlinelearningplatform.service.CreateNewCourseService;

import jakarta.servlet.http.HttpSession;

@Service
public class CreateNewCourseServiceImpl extends CommonService implements CreateNewCourseService {

	@Autowired
	private DepartmentRepository departmentRepo;

	@Autowired
	private CourseRepository courseRepo;

	@Override
	public String gotoCreateCoursePage(Model model, HttpSession session) {
		// TODO Auto-generated method stub

		User user = getUserObject(session);
		setUserNameModel(model, user);
		List<Department> allDepartmentsList = departmentRepo.findAll();
		model.addAttribute("deparmentList", allDepartmentsList);
		model.addAttribute("createNewCourseModel", new Course());

		return "create_new_course";
	}

	@Override
	public String saveNewCourse(Course course, Model model, HttpSession session) {
		// TODO Auto-generated method stub
		User user = getUserObject(session);
		setUserNameModel(model, user);
		course.setInstructor(user);
		courseRepo.save(course);
		model.addAttribute("successMessage", "Course has been created successfully.");

		return "forward:/gotoCreateCoursePage";
	}

}
