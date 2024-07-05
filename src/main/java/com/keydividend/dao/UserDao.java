package com.keydividend.dao;

import com.keydividend.entity.Subscription;
import org.bson.Document;

import com.keydividend.entity.User;

import java.util.List;

/**
 * 
 * @author rupau
 *
 */
public interface UserDao {
	
	public Document login(String userName, String password);
	public User saveUser(User user);
	public boolean updateUser(User user);
	public boolean updatePassword(Document passwordDoc);
	public boolean updateSubscription(String userId, String subscriptionId);

	public List<Subscription> getSubscriptions();



}
