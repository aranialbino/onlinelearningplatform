package com.shikharani.onlinelearningplatform.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.shikharani.onlinelearningplatform.constants.Constants;
import com.shikharani.onlinelearningplatform.model.User;
import com.shikharani.onlinelearningplatform.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

/**
 * This common service have methods which are used across multiple services.
 * This will get extended by all ServiceImpl.
 */
public class CommonService {

	private static final Logger logger = LoggerFactory.getLogger(CommonService.class);

	@Autowired
	UserRepository userRepository;

	// Retrieves a User object based on the user ID stored in the session
	/**
	 * This method will read USER_ID from HttpSession and load USER object from DB
	 * and return that.
	 * 
	 * @param session
	 * @return
	 */
	public User getUserObject(HttpSession session) {
		Object user_id_obj = session.getAttribute(Constants.USER_ID);// Retrieve user ID from the session
		if (user_id_obj != null) { // Check if user ID is not null
			Long user_id = (Long) user_id_obj;
			logger.info("Inside getUserObject. user_id= " + user_id);
			Optional<User> userObj = userRepository.findById(user_id); // Retrieve User object from the repository based
			if (userObj.isPresent()) { // Check if User object is present
				User user = userObj.get();
				return user;// Return the User object
			}
		}
		return null; // Return null if user ID is null or User object is not found
	}

	/**
	 * Save the User First Name in model object so that we can show User First Name
	 * on HTML Header on each HTML page. ea
	 * 
	 * @param model
	 * @param user
	 */
	public void setUserNameModel(Model model, User user) {
		if (user != null && model != null)
			model.addAttribute("user", user.getFName());

	}
}
