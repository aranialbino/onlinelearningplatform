package com.shikharani.onlinelearningplatform.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shikharani.onlinelearningplatform.model.Course;

import jakarta.servlet.http.HttpSession;

@Service
public interface CreateNewCourseService {
	public String gotoCreateCoursePage(Model model, HttpSession session);

	public String saveNewCourse(Course course, Model model, HttpSession session);
}
