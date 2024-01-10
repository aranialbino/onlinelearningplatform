package com.shikharani.onlinelearningplatform.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shikharani.onlinelearningplatform.dto.CourseElection;
import com.shikharani.onlinelearningplatform.dto.StudentData;
import com.shikharani.onlinelearningplatform.model.Course;
import com.shikharani.onlinelearningplatform.model.Enrollment;
import com.shikharani.onlinelearningplatform.model.User;
import com.shikharani.onlinelearningplatform.repository.CourseRepository;
import com.shikharani.onlinelearningplatform.repository.EnrollmentRepository;
import com.shikharani.onlinelearningplatform.service.CommonService;
import com.shikharani.onlinelearningplatform.service.StudentService;

import jakarta.servlet.http.HttpSession;

/**
 * Service class for StudentContoller.java.
 */
@Service
public class StudentServiceImpl extends CommonService implements StudentService {

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Autowired
	private CourseRepository courseRepository;

	/**
	 * Will get called from StudentController. This will prepare the data to show on
	 * student_home.html. We will prepare a list of courses with data where Student
	 * is already enrolled and set in Model attribute
	 */
	@Override
	public String gotoStudentHome(Model model, HttpSession session) {
		// Get the current user from the session
		logger.info("Inside gotoStudentHome");

		User user = getUserObject(session);

		// Retrieve the list of courses that the student is enrolled in
		List<Enrollment> studentCoursesEnrolled = user.getStudentCourses();

		// Create a list to store data about enrolled courses for display in the view
		List<StudentData> studentsDataList = new ArrayList();
		setUserNameModel(model, user);

		// Check if the student is not enrolled in any courses
		if (studentCoursesEnrolled == null || studentCoursesEnrolled.size() == 0) {
			// Set an error message for display in the view
			model.addAttribute("errorMessage", "You are not registered for any course. Please start enrolling.");
		} else {
			// Iterate through the list of enrolled courses
			for (int i = 0; i < studentCoursesEnrolled.size(); i++) {
				// Get details of each enrolled course
				Enrollment enrolledCourse = studentCoursesEnrolled.get(i);
				Course course = enrolledCourse.getCourse();

				// Create a new StudentData object to store details for display
				StudentData studentData = new StudentData();

				// Set serial number, department, course ID, title, description, instructor,
				// start date, and progress
				studentData.setSerialNum(i + 1);
				if (course.getDepartment() != null) {
					studentData.setDepartment(course.getDepartment().getName());
				}
				studentData.setCourseId(course.getId());
				studentData.setTitle(course.getTitle());
				studentData.setDescription(course.getDescription());
				studentData.setInstructor(course.getInstructor().getFName() + " " + course.getInstructor().getLName());
				studentData.setStartDate(enrolledCourse.getStartDate() + "");
				studentData.setProgress(enrolledCourse.getProgress() + "%");

				// Add the StudentData object to the list
				studentsDataList.add(studentData);
			}
		}

		// Add the list of enrolled courses to the model for display in the view
		model.addAttribute("studentsDataList", studentsDataList);

		// Return the name of the view to render (in this case, "studentHome")
		return "student_home";
	}

	/**
	 * Will be called when student want to withdraw from a course he is already We
	 * will delete an entry from ENROLLMENT table.
	 */
	@Override
	public String withdrawFromCourse(Long courseId, Model model, HttpSession session) {

		logger.info("Inside withdrawFromCourse()");

		User user = getUserObject(session);
		setUserNameModel(model, user);

		if (courseId == null) {
			logger.info("Required data is null.");
			return "forward:/studentHome";
		}
		Long userId = user.getId();
		enrollmentRepository.deleteByCourseIdAndStudentId(courseId, userId);
		model.addAttribute("successMessage", "Withdrawl from course is successfully.");
		return "forward:/studentHome";
	}

	/**
	 * If Student want to enroll a new course, this is going to be starting
	 * Controller method. This will filter out the coursed where user is already
	 * enrolled-in and will show only course where he is not enrolled yet. Set the
	 * List<CourseElection> in Model to show on html.
	 */

	@Override
	public String gotoNewCourseRegistrationHome(Model model, HttpSession session) {

		logger.info("Inside gotoNewCourseRegistrationHome()");

		// Get the current user from the session
		User user = getUserObject(session);

		// Retrieve the list of courses that the student is already enrolled in
		List<Enrollment> studentCourses = user.getStudentCourses();

		// Get course IDs already enrolled by the user
		Set<Long> courseIdsAlreadyEnrolled = getCourseIds(studentCourses);

		// Retrieve all courses from the repository, sorted by department and ID
		List<Course> allCoursesList = courseRepository.findAll(Sort.by(Sort.Direction.DESC, "department", "id"));

		// Create a list to store data about available courses for display in the view
		List<CourseElection> courseElectionList = new ArrayList();

		// Iterate through the list of all courses
		for (int i = 0; i < allCoursesList.size(); i++) {
			// Get details of each course
			Course course = allCoursesList.get(i);

			// Check if the course is already selected by the user
			if (courseIdsAlreadyEnrolled.contains(course.getId())) {
				// Skip this course if it's already selected
				continue;
			}

			// Create a new CourseElection object to store details for display
			CourseElection courseElection = new CourseElection();

			// Set course details, department details, instructor details, duration, and
			// difficulty level
			courseElection.setCourseId(course.getId());
			courseElection.setDepartmentId(course.getDepartment().getId());
			courseElection.setDepartmentName(course.getDepartment().getName());
			courseElection.setTitle(course.getTitle());
			courseElection.setDescription(course.getDescription());
			courseElection.setInstructor(course.getInstructor().getFName() + " " + course.getInstructor().getLName());
			courseElection.setDuration(course.getDuration() + " months");

			// Add the CourseElection object to the list
			courseElectionList.add(courseElection);
		}
		setUserNameModel(model, user);

		// Check if there are no available courses to enroll
		if (courseElectionList == null || courseElectionList.size() == 0) {
			logger.info("No course is available to enroll.");
			model.addAttribute("errorMessage", "No course is available.");
		}

		// Add the list of available courses to the model for display in the view
		model.addAttribute("courseElectionList", courseElectionList);

		// Return the name of the view to render (in this case, "newCourseRegistration")
		return "new_course_registration";
	}

	/**
	 * Will be called from new_course_registration.html. after student choose a
	 * course, we will save value in ENROLLMENT table.
	 */
	@Override
	public String enrollToCourse(String courseId, String startDateStr, Model model, HttpSession session) {

		logger.info("Inside enrollToCourse()");

		// Get the current user from the session
		User user = getUserObject(session);
		setUserNameModel(model, user);

		// Check if required data for enrollment is null or empty or invalid
		if (courseId == null || courseId.length() == 0 || startDateStr == null || startDateStr.length() == 0
				|| convertDate(startDateStr) == null) {
			logger.info("Required data is null.");
			// Redirect to the new course registration home page
			return "forward:/newCourseRegistrationHome";
		}

		// Convert the start date string to a Date object
		Date startDate = convertDate(startDateStr);

		// Get the user ID from the session
		Long userId = user.getId();

		// Create an Enrollment object with enrollment details
		Enrollment enrollment = new Enrollment();
		enrollment.setCourse(new Course(Long.parseLong(courseId)));
		enrollment.setProgress(0);
		enrollment.setStartDate(startDate);
		enrollment.setStudent(new User(userId));

		// Save the enrollment to the repository
		enrollmentRepository.save(enrollment);

		// Add a success message to the model for display in the view
		model.addAttribute("successMessage", "You have been successfully enrolled in the course.");

		// Redirect to the new course registration home page
		return "forward:/newCourseRegistrationHome";
	}

	private Set<Long> getCourseIds(List<Enrollment> userCoursesSelected) {
		Set<Long> set = new HashSet();
		for (Enrollment c : userCoursesSelected) {
			set.add(c.getCourse().getId());
		}
		return set;
	}

	private Date convertDate(String dateStr) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateStr);
		} catch (Exception e) {
			logger.error("Date parsing failed. dateStr= " + dateStr);
		}
		return null;
	}

}
