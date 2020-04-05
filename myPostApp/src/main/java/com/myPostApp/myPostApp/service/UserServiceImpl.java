package com.myPostApp.myPostApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myPostApp.myPostApp.entity.User;
import com.myPostApp.myPostApp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


	@Override
	public void save(User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
	}

	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}


	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
