package com.shikharani.onlinelearningplatform.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

@Service
public interface InstructorService {

	public String updateProgressOfStudent(String studentId, String courseId, String newProgress, Model model,
			HttpSession session);

	public String gotoInstructorHome(Model model, HttpSession session);

}
