package com.nanosoft.mystockportfolio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nanosoft.mystockportfolio.model.User;

@RestController//@RequestMapping("/users")
public class UserController {
	
	public static Map<Integer,User> userData ;//= new HashMap<Integer,User>();
	
	@RequestMapping("/users/{userId}")
	public User getUserData(@PathVariable("userId") int userId) {
		if(userData == null) {
			userData = new HashMap<Integer,User>();
		}
		if(userData.get(userId)==null)
			userData.put(userId, new User("Warren Buffet",userId,50000,0));
		return userData.get(userId);
	}
	
	
	

}
