package org.ganzhi.test.springmongodb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DBCollection;

public class Main {
    public static void main( String[] args )
    {
        //For Annotation
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);

        //For XML
        //ApplicationContext ctx = new GenericXmlApplicationContext("mongo-config.xml");

        MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");

        User user = new User("1001", "yong", "mook kim", 30);

        //save
        mongoOperation.save(user, "userprofile");

        //find
        User savedUser = mongoOperation.findOne(
                new Query(Criteria.where("id").is("1001")),
                User.class, "userprofile");

        System.out.println("savedUser : " + savedUser);

        //update
        mongoOperation.updateFirst(
                new Query(Criteria.where("firstname").is("yong")),
                Update.update("lastname", "new lastname"), User.class);

        //find
        User updatedUser = mongoOperation.findOne(
                new Query(Criteria.where("id").is("1001")),
                User.class, "userprofile");

        System.out.println("updatedUser : " + updatedUser);

        //delete
        mongoOperation.remove(
                new Query(Criteria.where("id").is("1001")),
                User.class);

        //List
        DBCollection listUser = mongoOperation.getCollection("userprofile");
        System.out.println("Number of user = " + listUser.count());

        UserService userService = (UserService)ctx.getBean("userService");

    }
}
