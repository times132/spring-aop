package com.times132.aop.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.data.mongodb")
@Getter
@Setter
public class MongoDataSourceProperties {

    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
}
