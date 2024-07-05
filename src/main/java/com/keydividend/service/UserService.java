package com.keydividend.service;

import com.keydividend.entity.Subscription;
import com.keydividend.entity.Watchlist;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keydividend.dao.UserDao;
import com.keydividend.helper.EmailTemplateHelper;
import com.keydividend.entity.User;

import java.util.List;

/**
 * 
 * @author rupau
 *
 */

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;

	@Autowired
	MailSenderService mailSenderService;

	public Document login(String userName, String password) {
		return userDao.login(userName, password);
	}
	
	public User saveUser(User user) {
		try {
			user = userDao.saveUser(user);

			mailSenderService.sendNewMail(user.getEmail(), "User Activation", new EmailTemplateHelper().userSignUpTemplate(user));
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}
	
	public boolean updatePassword(Document passwordDoc) {
		return userDao.updatePassword(passwordDoc);
	}

	public boolean updateSubscription(String userId, String subscriptionId)
	{
		return userDao.updateSubscription(userId,subscriptionId);
	}


	public List<Subscription> getSubscriptions()
	{
		return userDao.getSubscriptions();
	}
}
