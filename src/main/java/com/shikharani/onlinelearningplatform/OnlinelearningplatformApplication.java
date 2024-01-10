package com.shikharani.onlinelearningplatform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Bootstrap class, which will get called when the Spring Boot
 * application will fire up.
 */
@SpringBootApplication
public class OnlinelearningplatformApplication {

	private static final Logger logger = LoggerFactory.getLogger(OnlinelearningplatformApplication.class);

	/**
	 * This is the very first method to get called once Spring boot application will
	 * start. Everything, else will be loaded afterward only.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Starting Spring boot application.");
		SpringApplication.run(OnlinelearningplatformApplication.class, args);
	}
}
