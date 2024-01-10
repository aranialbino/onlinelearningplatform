package com.shikharani.onlinelearningplatform.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

@Service
public interface StudentService {

	public String enrollToCourse(String courseId, String startDateStr, Model model, HttpSession session);

	public String gotoNewCourseRegistrationHome(Model model, HttpSession session);

	public String gotoStudentHome(Model model, HttpSession session);

	public String withdrawFromCourse(Long courseId, Model model, HttpSession session);

}
