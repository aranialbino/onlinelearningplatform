function withdrawFromCourse(courseId) {
	window.location.href = "/withdrawFromCourse?courseId=" + courseId;
}

function registerForCourseConfirm(couseId, courseName, instructor) {
	//alert(couseId + " : " + courseName + " : " + instructor);
	var startDateVar = prompt("Enter start date(MM/dd/yyyy)");
	var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
	while (!(date_regex.test(startDateVar))) {
		if (startDateVar === null) return;
		startDateVar = prompt("Please enter valid  start date(MM/dd/yyyy)");
	}
	window.location.href = "/enrollToCourse?courseId=" + couseId + "&startDate=" + startDateVar;
}

function updateScore(studentId, courseId) {
	//alert(studentId + " : " + courseId);
	var startDateVar = prompt("Please update the progress between 1 to 100 for courseId-" + courseId);
	while (startDateVar > 100 || startDateVar < 0) {
		if (startDateVar === null) return;
		startDateVar = prompt("Please enter progress between 1 and 100 only.");
	}
	window.location.href = "/updateProgress?studentId=" + studentId + "&courseId=" + courseId + "&newProgress=" + startDateVar;
}
/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {
	const registrationForm = document.getElementById('registrationForm');

	registrationForm.addEventListener('submit', function(event) {
		event.preventDefault();
		// Add your form submission logic here
	});
});
