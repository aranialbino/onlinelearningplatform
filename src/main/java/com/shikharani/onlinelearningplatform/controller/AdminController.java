package com.shikharani.onlinelearningplatform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shikharani.onlinelearningplatform.model.Department;
import com.shikharani.onlinelearningplatform.repository.DepartmentRepository;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	DepartmentRepository departmentRepo;

	/**
	 * Only Admin can access this module/page. Admin user can create a new
	 * department from here. This is the first method which will set a blank bean in
	 * Model to show on UI.
	 * 
	 * @return
	 */
	@RequestMapping("/gotoCreateDepartment")
	public String gotoCreateDepartmentPage(Model model) {
		logger.info("Inside gotoCreateDepartmentPage");
		model.addAttribute("departmentModel", new Department());
		return "create_department";
	}

	/**
	 * Here Model object will come with values entered by User(Department
	 * departmentModel) and we will save Model Object in DB.
	 * 
	 * @param departmentModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveNewDepartment")
	public String saveNewDepartment(@ModelAttribute Department departmentModel, Model model) {
		logger.info("Inside gotoCreateDepartmentPage");
		departmentRepo.save(departmentModel);
		model.addAttribute("successMessage", "Department has been created successfully.");
		return "create_department";
	}
}
