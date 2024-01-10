package com.shikharani.onlinelearningplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorData {
	int serialNum;
	String department;
	String courseId;
	String courseTitle;
	String courseDescription;
	String studentId;
	String studentName;
	int progress;
	boolean showUpdate;
}
