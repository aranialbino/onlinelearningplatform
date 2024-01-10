package com.shikharani.onlinelearningplatform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shikharani.onlinelearningplatform.model.Course;
import com.shikharani.onlinelearningplatform.repository.CourseRepository;
import com.shikharani.onlinelearningplatform.repository.DepartmentRepository;
import com.shikharani.onlinelearningplatform.service.CreateNewCourseService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CreateNewCourseController extends CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CreateNewCourseController.class);

	@Autowired
	private CreateNewCourseService createNewCourseService;

	@Autowired
	private DepartmentRepository departmentRepo;

	@Autowired
	private CourseRepository courseRepo;

	// Handles requests to "/gotoCreateCoursePage" using GET or POST method
	@RequestMapping(value = "/gotoCreateCoursePage", method = { RequestMethod.GET, RequestMethod.POST })
	public String gotoCreateCoursePage(Model model, HttpSession session) {
		// Delegates the request to the CreateNewCourseService for processing
		logger.info("Inside gotoCreateCoursePage.");
		return createNewCourseService.gotoCreateCoursePage(model, session);
	}

	@RequestMapping(value = "/saveNewCourse", method = { RequestMethod.GET, RequestMethod.POST })
	public String saveNewCourse(@ModelAttribute("createNewCourseModel") Course course, Model model,
			HttpSession session) {
		logger.info("Inside saveNewCourse and course= " + course);

		return createNewCourseService.saveNewCourse(course, model, session);
	}
}
