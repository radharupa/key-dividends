package com.keydividend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.keydividend.constants.Constants;



public class MongoConfiguration {

    SimpleMongoClientDatabaseFactory keyDividendMongoDbFactory;



    @Bean(name = Constants.KEY_DIVIDEND_MONGO_TEMPLATE)
    public MongoTemplate mongoTemplate() throws Exception{
        return new MongoTemplate(keyDividendFactory());
    }


    @Bean
    public SimpleMongoClientDatabaseFactory keyDividendFactory()throws Exception{
        keyDividendMongoDbFactory = new SimpleMongoClientDatabaseFactory(mongoConnectionString());
        return keyDividendMongoDbFactory;
    }

    private String mongoConnectionString()
    {
       // StringBuffer sConnection = new StringBuffer("mongodb://ec2-18-224-95-198.us-east-2.compute.amazonaws.com:27017/key-tracker?");
        StringBuffer sConnection = new StringBuffer("mongodb://127.0.0.1:27017/key-tracker?");

        return sConnection.toString();
    }
}
