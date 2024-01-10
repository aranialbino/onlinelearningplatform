package com.shikharani.onlinelearningplatform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This file is part of Spring Security and the heart of Spring security.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	/**
	 * Custom Password Encryptor
	 * 
	 * @return
	 */
	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		// return new Password();
	}

	/**
	 * Security main configuration. What all URLs are secured and who (user will
	 * role) can access them
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/login/**", "/register/**", "/static/**", "*.jpg", "*.avif", "*.jfif", "*.PNG",
						"*.png").permitAll()
				.requestMatchers("/gotoCreateDepartment", "/saveNewDepartment").hasRole("ADMIN")
				.requestMatchers("/studentHome", "/withdrawFromCourse", "/newCourseRegistrationHome", "/enrollToCourse")
				.hasRole("STUDENT")
				.requestMatchers("/updateProgress", "/instructorHome", "/gotoCreateCoursePage", "/saveNewCourse")
				.hasRole("INSTRUCTOR")
				.requestMatchers("/gotoHome").authenticated())
				.userDetailsService(customUserDetailsService)
				.formLogin(form -> form.loginPage("/login").successForwardUrl("/gotoHome"))
				.logout((logout) -> logout.permitAll());
		return http.build();
	}

	/**
	 * Custom UserDetailsService
	 * 
	 * @return
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return customUserDetailsService;
	}

}
