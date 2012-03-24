package org.ganzhi.test.springmongodb;

import java.util.List;

public interface UserService {
    public List<User> findByLastName(String lastName);
}
