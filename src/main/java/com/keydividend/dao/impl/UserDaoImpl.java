package com.keydividend.dao.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import com.keydividend.entity.Subscription;
import com.keydividend.entity.Subscription;
import com.keydividend.util.ExceptionUtil;
import com.keydividend.util.MongoDBUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.keydividend.dao.UserDao;
import com.keydividend.entity.User;
import com.keydividend.constants.Constants;
import com.keydividend.constants.UserSchemaConstants;
import com.mongodb.client.result.UpdateResult;


/**
 * 
 * @author rupau
 *
 */

@Component
@Qualifier(Constants.USER_DAO)
public class UserDaoImpl implements UserDao {
	private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

	@Qualifier(Constants.KEY_DIVIDEND_MONGO_TEMPLATE)
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	@Override
	public Document login(String userName, String password) {
		Document user = null;
		try {
			Query query = new Query();
			query.addCriteria( Criteria.where(UserSchemaConstants.userName).is(userName));
			query.addCriteria(Criteria.where(UserSchemaConstants.password).is(password));
			query.addCriteria(Criteria.where(UserSchemaConstants.active).is(true));
			query.fields().exclude("_id");
			query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
			
			Document hint = new Document();
			hint.put(UserSchemaConstants.userName, 1.0);
			hint.put(UserSchemaConstants.password, 1.0);
			hint.put(UserSchemaConstants.active, 1.0);
			
			//query.withHint(hint);
			user = mongoTemplate.findOne(query, Document.class, Constants.USER_COLLECTION);

		}catch (Exception e) {
			LOGGER.error(ExceptionUtil.getDetails(e));
		} 
		return user;
	}
	
	@Override
	public User saveUser(User user)
	{
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		User userProfile = mongoTemplate.save(user, Constants.USER_COLLECTION);
		return userProfile;
	}
	
	public boolean updateUser(User user) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(user.getUserId()));
		query.fields().exclude("_id");
		query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
		Document hint = new Document();
		hint.put(UserSchemaConstants.userId, 1.0);
		query.withHint(hint);

		Gson gson = new Gson();
		Document userDocument = Document.parse(gson.toJson(user));
		Update update = MongoDBUtil.fromDocumentExcludeNullFields(userDocument);
		UpdateResult  updateResult = mongoTemplate.updateFirst(query, update, User.class, Constants.USER_COLLECTION);
		if(updateResult != null && updateResult.getModifiedCount() == 1)
			return true;
		else
			return false;
	}
	
	public boolean updatePassword(Document passwordDoc) {
		String userId = null;
		String password = null;
		String newPassword = null;
				
		if(passwordDoc != null && passwordDoc.containsKey("userId"))
			userId = passwordDoc.getString("userId");
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		query.addCriteria(Criteria.where(UserSchemaConstants.password).is(password));
		query.fields().exclude("_id");
		query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
		Document hint = new Document();
		hint.put(UserSchemaConstants.userId, 1.0);
		hint.put(UserSchemaConstants.password, 1.0);
		query.withHint(hint);

		Update update = new Update();
		update.set(UserSchemaConstants.password, newPassword);
		UpdateResult  updateResult = mongoTemplate.updateFirst(query, update, Constants.USER_COLLECTION);
		if(updateResult != null && updateResult.getModifiedCount() == 1)
			return true;
		else
			return false;
	}


	public List<Subscription> getSubscriptions()
	{
		List<Subscription> userPlans = new ArrayList<Subscription>();
			try {
				Query query = new Query();
				query.fields().exclude("_id");
				query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);

				Document hint = new Document();

				query.withHint(hint);
				List<Document> subscriptionDocs = mongoTemplate.find(query,Document.class, Constants.SUBSCRIPTION_COLLECTION);

				if(subscriptionDocs != null && !subscriptionDocs.isEmpty())
				{
					//TODO: map documents to entity
				}


			}catch (Exception e) {
				LOGGER.error(ExceptionUtil.getDetails(e));
			}
			return userPlans;

	}

	public boolean updateSubscription(String userId, String subscriptionId) {

		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		query.fields().exclude("_id");
		query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
		Document hint = new Document();
		hint.put(UserSchemaConstants.userId, 1.0);
		query.withHint(hint);

		Update update = new Update();
		update.set(UserSchemaConstants.subscriptionId, subscriptionId);
		UpdateResult  updateResult = mongoTemplate.updateFirst(query, update, Constants.USER_COLLECTION);
		if(updateResult != null && updateResult.getModifiedCount() == 1)
			return true;
		else
			return false;
	}

}
