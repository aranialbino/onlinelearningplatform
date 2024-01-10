package com.shikharani.onlinelearningplatform.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is an entity which holds the user information and user's role. On based
 * of user role, we will have access to different section of application.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Email(message = "Invalid email address")
	@Column(unique = true, name = "username")
	private String username;

	@NotBlank(message = "Password is required")
	private String password;

	@NotBlank(message = "First Name is required")
	private String fName;

	@NotBlank(message = "Last Name is required")
	private String lName;

	@NotBlank(message = "Phone is required")
	private String phone;

	@NotBlank(message = "Zip is required")
	private String zip;

	@NotBlank(message = "role is required")
	private String role;
	// INSTRUCTOR/STUDENT;

	@OneToMany(mappedBy = "instructor")
	private List<Course> courses;// A user (INSTRUCTOR) can provide multiple courses.

	@OneToMany(mappedBy = "student")
	private List<Enrollment> studentCourses;// A user (STUDENT) can enroll to multiple courses.

	public User(Long id) {
		this.id = id;
	}

}
