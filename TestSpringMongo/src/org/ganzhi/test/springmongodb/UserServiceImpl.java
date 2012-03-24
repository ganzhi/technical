package org.ganzhi.test.springmongodb;

import java.util.List;

import org.ganzhi.test.springmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repo) {
        this.repository = repo;
    }

    public List<User> findByLastName(String lastName){
        return repository.findByLastName(lastName);
    }

}
