package com.shikharani.onlinelearningplatform.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;

import com.shikharani.onlinelearningplatform.service.impl.CreateNewCourseServiceImpl;

@SpringBootTest
public class CreateNewCourseServiceImplTest {

	@Autowired
	CreateNewCourseServiceImpl newCourseService;

	@Mock
	Model model;

	@Test
	void test_gotoCreateCoursePage() {
		MockHttpSession httpSession = new MockHttpSession();
		String viewName = newCourseService.gotoCreateCoursePage(model, httpSession);
		assertNotNull(viewName);
	}
}
