/**
 * 
 */
package com.shikharani.onlinelearningplatform.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shikharani.onlinelearningplatform.model.User;

@SpringBootTest
class UserRepositoryTest {

	@Autowired
	UserRepository userRepo;

	User user = null;

	@Test
	void test_findByUsername() {
		String email = "abs@email.com";
		String role = "ROLE_INSTRUCTOR";
		user = ModelMockGenerator.getUser(email, role);
		userRepo.save(user);
		User actualUser = userRepo.findByUsername(email);
		assertNotNull(actualUser);
		assertEquals(actualUser.getFName(), "test_fname");
		assertEquals(actualUser.getLName(), "test_lname");
		assertEquals(actualUser.getUsername(), email);
	}

	@AfterEach
	void cleanUp() {
		if (user != null)
			userRepo.delete(user);
	}

}
