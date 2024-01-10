package com.shikharani.onlinelearningplatform.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shikharani.onlinelearningplatform.constants.Constants;
import com.shikharani.onlinelearningplatform.dto.UserData;
import com.shikharani.onlinelearningplatform.exception.UserRoleNotDefinedException;
import com.shikharani.onlinelearningplatform.model.User;
import com.shikharani.onlinelearningplatform.service.UserService;

import jakarta.servlet.http.HttpSession;

/**
 * Controlled to handle all Users's action.
 */
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(CreateNewCourseController.class);

	@Autowired
	private UserService userService;

	/**
	 * Send User to login View (login.html)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String gotoLogin(Model model) {
		model.addAttribute("userModel", new UserData());
		logger.info("Inside gotoLogin ");
		return "login";
	}

	/**
	 * After USer is authorized (i.e. id/password is verifies) \, this controller
	 * method will get called. We will forward the user to different Home pages asa
	 * per user's ROLE.
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/gotoHome")
	public String gotoHome(Model model, HttpSession session) {
		logger.info("Inside gotoHome ");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String emailOfUser = authentication.getName();//To get the userName
		Set<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority())
				.collect(Collectors.toSet());// TO get the User's ROLE
		logger.info("User Roles=" + roles + ", emailOfUser=" + emailOfUser);
		User user = userService.getUser(emailOfUser);
		session.setAttribute(Constants.USER_OBJECT, user);
		session.setAttribute(Constants.USER_ID, user.getId());
		model.addAttribute("user", user.getFName());// To Show the user first name on HTML page

		if (roles.contains("ROLE_STUDENT")) {
			logger.info("User is student.");
			return "forward:/studentHome";
		} else if (roles.contains("ROLE_INSTRUCTOR")) {
			logger.info("User is Instructor.");
			return "forward:/instructorHome";
		} else if (roles.contains("ROLE_ADMIN")) {
			logger.info("User is Admin.");
			return "forward:/gotoCreateDepartment";
		} else {
			logger.info("User role is Unknown..");
			throw new UserRoleNotDefinedException();
		}
	}

	/**
	 * Logout the user
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "login";
	}
}
