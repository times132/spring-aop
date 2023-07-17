package com.times132.aop.core;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
@EnableConfigurationProperties(MongoDataSourceProperties.class)
@RequiredArgsConstructor
public class MongoDataSourceConfiguration {

    private final MongoDataSourceProperties mongoDataSourceProperties;

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        String connectionString = "mongodb://" + mongoDataSourceProperties.getUsername() + ":" + mongoDataSourceProperties.getPassword() + "@"
                + mongoDataSourceProperties.getHost() + ":" + mongoDataSourceProperties.getPort() + "/"
                + mongoDataSourceProperties.getDatabase() + "?retryWrites=false";
        return new SimpleMongoClientDatabaseFactory(connectionString);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory());
    }
}
