package com.myPostApp.myPostApp.service;

import java.util.List;

import com.myPostApp.myPostApp.entity.User;

public interface UserService {
	void save(User user);
	User getUser(String username);
	List<User> findAll();
}
