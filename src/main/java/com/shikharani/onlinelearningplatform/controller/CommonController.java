package com.shikharani.onlinelearningplatform.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.shikharani.onlinelearningplatform.constants.Constants;
import com.shikharani.onlinelearningplatform.model.User;
import com.shikharani.onlinelearningplatform.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

/**
 * This is a common controller which will get extended by all other Controllers.
 */
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	UserRepository userRepository;

	/**
	 * TO load the User Object from DB by user_id.
	 * 
	 * @param session
	 * @return
	 */
	public User getUserObject(HttpSession session) {
		Object user_id_obj = session.getAttribute(Constants.USER_ID);
		if (user_id_obj != null) {
			Long user_id = (Long) user_id_obj;
			logger.info("Inside getUserObject. user_id= " + user_id);
			Optional<User> userObj = userRepository.findById(user_id);
			if (userObj.isPresent()) {
				User user = userObj.get();
				return user;
			}
		}
		return null;
	}
}
