package com.myPostApp.myPostApp.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myPostApp.myPostApp.entity.Role;
import com.myPostApp.myPostApp.entity.User;
import com.myPostApp.myPostApp.pojo.UserRegistration;
import com.myPostApp.myPostApp.service.UserService;

@RestController
public class UserController {

	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/api/register")
	public ResponseEntity<?> register(@RequestBody Map<String, Object> userRegistration){
		
		ObjectMapper mapper = new ObjectMapper();
		UserRegistration userReg = mapper.convertValue(userRegistration.get("user"), UserRegistration.class);
		
		Map<String, String> retval = new HashMap<String, String>();
		
		if(!userReg.getPassword().equals(userReg.getPasswordConfirmation())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password and password confirmation must be the same.");
		}	
		else if(userService.getUser(userReg.getUsername()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is a user with the same name, please try with another username");
		}else {
			userService.save(new User(userReg.getUsername(), userReg.getPassword(), Arrays.asList(new Role("USER"))));
			retval.put("success", "User created");
			return ResponseEntity.ok().body(retval);
		}
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<Map<String,Object>> users(){
		Map<String,Object> retval = new HashMap<>();
		retval.put("posts", userService.findAll());
		
		return ResponseEntity.ok().body(retval);
		
	}
	
	@GetMapping("/getUsername")
	public String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	
	@GetMapping(value="/logouts")
	public void logout(@RequestParam(value = "access_token") String accessToken) {
		tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
	}
}
