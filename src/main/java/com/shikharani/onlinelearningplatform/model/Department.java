package com.shikharani.onlinelearningplatform.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Entity used to hold the Department table data.
 */
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;

	private String description;

	private String head_of_department;

	private int yearOfStart;

	@OneToMany(mappedBy = "department")
	private List<Course> courses;// one department can have multiple courses

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}

}