package com.forex.predicter.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoDbConfig {

    @Value("${db.address}")
    private String dbAddress;

    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(dbAddress), "predicter");
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {

        return new MongoTemplate(mongoDbFactory());

    }

}