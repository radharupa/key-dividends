package com.keydividend.controller;

import com.keydividend.entity.Subscription;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.keydividend.entity.Portfolio;
import com.keydividend.entity.User;
import com.keydividend.service.UserService;

import java.util.List;

/**
 * 
 * @author rupau
 *
 */

@RestController
@RequestMapping("v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/login")
	public Document login(@RequestBody Document rqDocument) throws Exception{
		Document rsDocument = null;
		if(rqDocument != null 
				&& rqDocument.containsKey("userName") && rqDocument.getString("userName") != null
				&& rqDocument.containsKey("password") && rqDocument.getString("password") != null)
			rsDocument = userService.login(rqDocument.getString("userName"), rqDocument.getString("password"));
		return rsDocument;
	}
	
	@PostMapping(path = "/saveUser")
	public User saveUser(@RequestBody User user) throws Exception{
		User userProfile = userService.saveUser(user);
		return userProfile;
	}
	
	@PostMapping(path = "/updateUser")
	public boolean updateUser(@RequestBody User user) throws Exception{
		return userService.updateUser(user);
	}

	@GetMapping(path = "/activateUser")
	public boolean activateUser(@RequestBody String userId) throws Exception{
		User user = new User();
		user.setUserId(userId);
		user.setActive(true);
		return userService.updateUser(user);
	}
	
	@PostMapping(path = "/updatePassword")
	public boolean updatePassword(@RequestBody Document passwordDoc) throws Exception{
		return userService.updatePassword(passwordDoc);
	}

	@PostMapping(path = "/updateSubscription")
	public boolean updateSubscription(@RequestBody String userId, String subscriptionId) throws Exception{
		return userService.updateSubscription(userId, subscriptionId);
	}

	@GetMapping(path = "/subscriptions")
	public List<Subscription> getSubscriptions() throws Exception{

		return userService.getSubscriptions();
	}
}
