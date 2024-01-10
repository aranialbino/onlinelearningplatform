package com.shikharani.onlinelearningplatform.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shikharani.onlinelearningplatform.model.User;

@Service
public interface RegisterService {

	public String saveRegistration(User userModel, Model model);

}
