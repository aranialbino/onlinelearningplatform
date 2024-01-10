package com.shikharani.onlinelearningplatform.service;

import org.springframework.stereotype.Service;

import com.shikharani.onlinelearningplatform.model.User;

@Service
public interface UserService {

	void save(User user);

	User getUser(String email);

}
