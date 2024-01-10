package com.shikharani.onlinelearningplatform.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import com.shikharani.onlinelearningplatform.model.User;
import com.shikharani.onlinelearningplatform.repository.ModelMockGenerator;
import com.shikharani.onlinelearningplatform.repository.UserRepository;
import com.shikharani.onlinelearningplatform.service.impl.RegisterServiceImpl;

@SpringBootTest
public class RegisterServiceImplTest {

	@Autowired
	RegisterServiceImpl registerService;

	@Autowired
	private UserRepository userRepository;

	User user;

	@Mock
	Model model;

	@Test
	void test_saveRegistration() {
		String email = "abc1@email.com";
		String role = "ROLE_STUDENT";

		user = ModelMockGenerator.getUser(email, role);
		registerService.saveRegistration(user, model);
	}

	@AfterEach
	void cleanUp() {
		if (user != null)
			userRepository.delete(user);
	}

}
