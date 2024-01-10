package com.shikharani.onlinelearningplatform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shikharani.onlinelearningplatform.model.User;
import com.shikharani.onlinelearningplatform.service.RegisterService;

@Controller
public class RegisterController {
	private static final Logger logger = LoggerFactory.getLogger(CreateNewCourseController.class);

	@Autowired
	private RegisterService registerService;

	/**
	 * 
	 * Will goto User registratiom home page and save blank User() in MOdel.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/register")
	public String gotoRegister(Model model) {
		logger.info("Inside gotoRegister ");
		User user = new User();
		model.addAttribute("userModel", user);
		return "register";
	}

	/**
	 * This will get called when user is saving registration data i.e create a new
	 * user.
	 * 
	 * @param userModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register/save", method = RequestMethod.POST)
	public String saveRegistration(@ModelAttribute User userModel, Model model) {
		return registerService.saveRegistration(userModel, model);
	}

}
