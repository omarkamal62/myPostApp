package com.myPostApp.myPostApp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.myPostApp.myPostApp.config.CustomUserDetails;
import com.myPostApp.myPostApp.entity.Role;
import com.myPostApp.myPostApp.entity.User;
import com.myPostApp.myPostApp.repository.UserRepository;
import com.myPostApp.myPostApp.service.UserService;

@SpringBootApplication
@EnableAuthorizationServer
public class MyPostAppApplication {

	@Autowired
	private PasswordEncoder getPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(MyPostAppApplication.class, args);
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//	  return new WebMvcConfigurerAdapter() {
//	    @Override
//	    public void addCorsMappings(CorsRegistry registry) {
//	      registry.addMapping("/api/*").allowedOrigins("http://localhost:8080");
//	    }
//	  };
//	}
	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService userService) throws Exception {
		if (repository.count()==0)
			userService.save(new User("user", "password", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
		builder.userDetailsService(userDetailsService(repository));
	}
	
	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}

}
