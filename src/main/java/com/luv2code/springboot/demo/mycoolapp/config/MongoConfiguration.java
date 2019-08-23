package com.luv2code.springboot.demo.mycoolapp.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import com.mongodb.MongoClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories({"com.luv2code.springboot.demo.mycoolapp.dao"})
@PropertySource("classpath:application.properties")
public class MongoConfiguration extends AbstractMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return "web_customer_tracker";
    }

    @Autowired
    private Environment env;

    @Override
    public MongoClient mongoClient() {
        String mongoURI = env.getProperty("spring.data.mongodb.uri");
        if (mongoURI == null)
            throw new NullPointerException("No URI for mongodb access is setuped in application properties");
        MongoClientURI uri = new MongoClientURI(mongoURI);
        return new MongoClient(uri);
    }
}
