package com.shikharani.onlinelearningplatform.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shikharani.onlinelearningplatform.model.User;
import com.shikharani.onlinelearningplatform.repository.ModelMockGenerator;
import com.shikharani.onlinelearningplatform.repository.UserRepository;

@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	UserService userService;

	@Autowired
	private UserRepository userRepository;

	User user = null;

	@Test
	public void test_getUser() {
		String email = "abc@email.com";
		String role = "ROLE_STUDENT";
		user = ModelMockGenerator.getUser(email, role);
		userRepository.save(user);
		User actualUser = userService.getUser(email);
		assertNotNull(actualUser);
		assertEquals(email, actualUser.getUsername());
	}

	@AfterEach
	void cleanUp() {
		if (user != null)
			userRepository.delete(user);
	}

}
