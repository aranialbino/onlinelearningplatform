package com.shikharani.onlinelearningplatform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shikharani.onlinelearningplatform.OnlinelearningplatformApplication;
import com.shikharani.onlinelearningplatform.dto.InstructorData;
import com.shikharani.onlinelearningplatform.model.Course;
import com.shikharani.onlinelearningplatform.model.Enrollment;
import com.shikharani.onlinelearningplatform.model.User;
import com.shikharani.onlinelearningplatform.repository.EnrollmentRepository;
import com.shikharani.onlinelearningplatform.service.CommonService;
import com.shikharani.onlinelearningplatform.service.InstructorService;

import jakarta.servlet.http.HttpSession;

/**
 * Service class for all Instructor's actions.
 */
@Service
public class InstructorServiceImpl extends CommonService implements InstructorService {

	@Autowired
	EnrollmentRepository enrollmentRepo;
	private static final Logger logger = LoggerFactory.getLogger(OnlinelearningplatformApplication.class);

	/**
	 * Will populate the Home screen data for Instructor.
	 */
	@Override
	public String gotoInstructorHome(Model model, HttpSession session) {

		logger.info("Inside gotoInstructorHome");
		User user = getUserObject(session);
		setUserNameModel(model, user);

		List<Course> instructorCoursesList = user.getCourses();
		List<InstructorData> instructorDataList = new ArrayList();
		if (instructorCoursesList != null && instructorCoursesList.size() > 0) {
			int courseSize = instructorCoursesList.size();
			for (int i = 0; i < courseSize; i++) {
				Course course = instructorCoursesList.get(i);
				// Get number of students enrolled to this course.
				List<Enrollment> courseEnrollmentList = enrollmentRepo.findByCourseId(course.getId());
				if (courseEnrollmentList != null && courseEnrollmentList.size() > 0) {
					for (int j = 0; j < courseEnrollmentList.size(); j++) {
						Enrollment enrollment = courseEnrollmentList.get(j);
						User student = enrollment.getStudent();
						InstructorData instructorData = new InstructorData();
						instructorData.setSerialNum(instructorDataList.size() + 1);
						if (course.getDepartment() != null) {
							instructorData.setDepartment(course.getDepartment().getName());
						}
						instructorData.setCourseId(course.getId().toString());
						instructorData.setCourseTitle(course.getTitle());
						instructorData.setCourseDescription(course.getDescription());
						instructorData.setStudentId(student.getId().toString());
						instructorData.setStudentName(student.getFName() + " " + student.getLName());
						instructorData.setProgress(enrollment.getProgress());
						instructorData.setShowUpdate(true);
						instructorDataList.add(instructorData);
					}
				} else {
					InstructorData instructorData = new InstructorData();
					instructorData.setSerialNum(instructorDataList.size() + 1);
					if (course.getDepartment() != null) {
						instructorData.setDepartment(course.getDepartment().getName());
					}
					instructorData.setCourseId(course.getId().toString());
					instructorData.setCourseTitle(course.getTitle());
					instructorData.setCourseDescription(course.getDescription());
					instructorData.setStudentId("-");
					instructorData.setStudentName("-");
					instructorData.setShowUpdate(false);
					instructorDataList.add(instructorData);
				}
			}
		} else {
			model.addAttribute("errorMessage", "No course is available.");
		}

		model.addAttribute("instructorDataList", instructorDataList);
		return "instructor_home";
	}

	/**
	 * Will get called once Instructor will click on UPDATE PROGRESS link on UI> It
	 * will load object from DB and update Student's progress.
	 */
	@Override
	public String updateProgressOfStudent(String studentId, String courseId, String newProgress, Model model,
			HttpSession session) {

		logger.info("Inside updateProgressOfStudent");
		// TODO Auto-generated method stub
		User user = getUserObject(session);
		setUserNameModel(model, user);
		Enrollment enrollmentEntity = enrollmentRepo
				.findByCourseIdAndStudentId(Long.valueOf(courseId), Long.valueOf(studentId)).get(0);
		enrollmentEntity.setProgress(Integer.valueOf(newProgress));
		enrollmentRepo.save(enrollmentEntity);
		model.addAttribute("successMessage", "Progress is updated successfully.");
		return "forward:/instructorHome";
	}
}
