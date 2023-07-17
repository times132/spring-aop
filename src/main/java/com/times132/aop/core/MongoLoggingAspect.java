package com.times132.aop.core;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class MongoLoggingAspect {

    private final MongoTemplate mongoTemplate;

    @Around("@annotation(logAnnotation)")
    public Object logEnabledAdvice(ProceedingJoinPoint joinPoint, LogAnnotation logAnnotation) throws Throwable {
        // Get the MongoDB collection name from the annotation
        String collectionName = logAnnotation.collection();

        // Get the method name
        String methodName = joinPoint.getSignature().getName();

        // Perform any pre-logging operations

        // Call the method and get the result
        Object result = joinPoint.proceed();

        // Log the data to MongoDB
        logToMongoDB(methodName, collectionName, result);

        // Perform any post-logging operations

        return result;
    }

    private void logToMongoDB(String methodName, String collectionName, Object data) {
        MongoDatabase database = mongoTemplate.getDb();
        MongoCollection<Document> collection = database.getCollection(collectionName);

        // Create a new document to store the log data
        Document document = new Document();
        document.append("methodName", methodName);
        document.append("data", data);

        // Insert the document into the MongoDB collection
        collection.insertOne(document);
    }
}
