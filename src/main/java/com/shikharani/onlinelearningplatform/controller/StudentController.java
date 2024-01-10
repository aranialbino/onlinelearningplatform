package com.shikharani.onlinelearningplatform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shikharani.onlinelearningplatform.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	private StudentService studentService;

	/**
	 * This will be called UserController after user is authenticated and authorized
	 * with "STUDENT" role. THis is the landing page for student home page.
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/studentHome")
	public String gotoStudentHome(Model model, HttpSession session) {
		// Delegates the request to the StudentService for processing
		return studentService.gotoStudentHome(model, session);
	}

	/**
	 * Will be called from student_home once a student will click on withdraw link
	 * from user_home.html
	 * 
	 * @param courseId
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/withdrawFromCourse")
	public String withdrawFromCourse(@RequestParam Long courseId, Model model, HttpSession session) {
		logger.info("Inside withdrawFromCourse");
		// Delegates the request to the StudentService for processing
		return studentService.withdrawFromCourse(courseId, model, session);
	}

	/**
	 * If Student want to enroll/register a new course this link will be called and
	 * student will land on page where student can choose a Course a register into.
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/newCourseRegistrationHome")
	public String gotoNewCourseRegistrationHome(Model model, HttpSession session) {
		logger.info("Inside gotoNewCourseRegistrationHome");
		// Delegates the request to the StudentService for processing
		return studentService.gotoNewCourseRegistrationHome(model, session);
	}

	/**
	 * Will be called from new_course_registration.html. After student will select a
	 * course, student will click link of page to enroll to a new course
	 * 
	 * @param courseId
	 * @param startDateStr
	 * @param model
	 * @param session
	 * @return
	 */
	// Handles requests to "/enrollToCourse"
	@RequestMapping(value = "/enrollToCourse")
	public String enrollToCourse(@RequestParam String courseId, @RequestParam("startDate") String startDateStr,
			Model model, HttpSession session) {
		logger.info("Inside enrollToCourse");
		// Delegates the request to the StudentService for processing
		return studentService.enrollToCourse(courseId, startDateStr, model, session);
	}
}
