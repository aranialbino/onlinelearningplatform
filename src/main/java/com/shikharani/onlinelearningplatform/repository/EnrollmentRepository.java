package com.shikharani.onlinelearningplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shikharani.onlinelearningplatform.model.Enrollment;

import jakarta.transaction.Transactional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	@Query("SELECT e FROM Enrollment e WHERE e.course.id = :courseId")
	public List<Enrollment> findByCourseId(Long courseId);

	public List<Enrollment> findByCourseIdAndStudentId(Long courseId, Long studentId);

	@Transactional
	public int deleteByCourseIdAndStudentId(Long courseId, Long studentId);

}
