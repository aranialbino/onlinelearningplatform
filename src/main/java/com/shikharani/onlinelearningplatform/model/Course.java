package com.shikharani.onlinelearningplatform.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity used to hold the courses table data.
 */
@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank(message = "Title is required")
	private String title;

	@NotBlank(message = "Description is required")
	private String description;

	private String duration;

	private Integer difficultyLevel;

	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;// one department can have multiple courses

	@ManyToOne
	@JoinColumn(name = "instructor", nullable = false)
	private User instructor;// one User/instructor can have multiple courses

	@OneToMany(mappedBy = "course")
	private List<Enrollment> studentCourses;// A course can be taken by o multiple students.
	//

	public Course(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", description=" + description + ", duration=" + duration
				+ ", difficultyLevel=" + difficultyLevel + "]";
	}

}
