package com.shikharani.onlinelearningplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shikharani.onlinelearningplatform.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	@Query("SELECT c FROM Course c WHERE c.instructor.id = :instructor_id")
	public List<Course> getCourseByInstructor(Long instructor_id);
}
