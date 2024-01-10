package com.shikharani.onlinelearningplatform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shikharani.onlinelearningplatform.repository.EnrollmentRepository;
import com.shikharani.onlinelearningplatform.service.InstructorService;

import jakarta.servlet.http.HttpSession;

/**
 * Controller for Instructor user type. It contains various controller method used by Instructor.
 */
@Controller
public class InstructorController extends CommonController {
	private static final Logger logger = LoggerFactory.getLogger(CreateNewCourseController.class);

	@Autowired
	EnrollmentRepository enrollmentRepo;

	@Autowired
	InstructorService instructorService;

	/** This controller method will get called once user clicks on Update Progress Link.
	 * 
	 * @param studentId
	 * @param courseId
	 * @param newProgress
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateProgress", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateProgressOfStudent(@RequestParam String studentId, @RequestParam String courseId,
			@RequestParam String newProgress, Model model, HttpSession session) {
		return instructorService.updateProgressOfStudent(studentId, courseId, newProgress, model, session);
	}

	/**
	 * To send the Instructor to its home page with details and prepare model objects.
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/instructorHome", method = { RequestMethod.GET, RequestMethod.POST })
	public String gotoInstructorHome(Model model, HttpSession session) {
		return instructorService.gotoInstructorHome(model, session);
	}
}
