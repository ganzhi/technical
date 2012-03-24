package org.ganzhi.test.springmongodb.repository;

import java.util.List;

import org.ganzhi.test.springmongodb.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    List<User> findByLastName(String lastName);
}
