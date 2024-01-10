package com.shikharani.onlinelearningplatform.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shikharani.onlinelearningplatform.model.User;
import com.shikharani.onlinelearningplatform.repository.UserRepository;
import com.shikharani.onlinelearningplatform.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
	private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Handles user registration and returns the appropriate view name
	/**
	 * This will be called from Controller and save User in DB.
	 */
	@Override
	public String saveRegistration(User userModel, Model model) {
		logger.info("Inside saveRegistration ");
		logger.info("userModelObj= " + userModel);
		if (userModel != null) {
			// Check if userModel is not null

			try {
				save(userModel);
				return "login"; // Redirect to login page after successful registration

			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("userModel", userModel);
				model.addAttribute("errorMessage", "Email already exists.");
				return "register"; // Display registration form with error message

			}
		}
		// If userModel is null, return to the registration form
		model.addAttribute("userModel", userModel);
		return "register";
	}

	/**
	 * To Encode the password and save User object in DB.
	 * 
	 * @param user
	 */
	private void save(User user) { // Saves a user to the repository
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
