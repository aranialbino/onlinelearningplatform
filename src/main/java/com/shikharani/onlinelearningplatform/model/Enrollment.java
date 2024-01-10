package com.shikharani.onlinelearningplatform.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is an entity which holds the enrollment information for students.
 * Students can know what is their progress and what is the start date for the
 * Course.
 */
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	/**
	 * this is many-to-one relation which is having join column student_id which
	 * will be generated in database automatically .One student (user_id) can enroll
	 * to multiple course so duplicate user_id can be but with combination with
	 * distinct course_id
	 */
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private User student;// One student (user_id) can enroll to multiple course so duplicate user_id can
							// be but with combination with distinct course_id
	/**
	 * this is many to one relation which is having join column course_id which will
	 * be generated in database automatically.One course can be taken by multiple
	 * students
	 */
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;// One course can be taken by multiple students

	private Date startDate;
	private int progress;

}