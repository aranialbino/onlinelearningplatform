package com.shikharani.onlinelearningplatform.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shikharani.onlinelearningplatform.model.User;
import com.shikharani.onlinelearningplatform.repository.UserRepository;

/**
 * Custom UserDetailsService class. This is part of Spring Security
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	// Initialize the Logger.
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	public CustomUserDetailsService() {
	}

	/**
	 * Spring security will call this method while Authorize the user and check
	 * User's role.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // LoadUserByUsername Method
		logger.info("Inside CustomUserDetailsService.loadUserByUsername() and email=" + email);
		// declares a method named loadUserByUsername that implements the
		// UserDetailsService interface.
		User user = userRepo.findByUsername(email);
		if (user != null) {
			logger.info("user_role=" + user.getRole());
		}

		if (user != null) {

			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					List.of(new SimpleGrantedAuthority(user.getRole())));

		} else {
			throw new UsernameNotFoundException("Invalid email or password");
		}
	}
}
