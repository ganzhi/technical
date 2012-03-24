package org.ganzhi.test.springmongodb;

import org.ganzhi.test.springmongodb.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;

import com.mongodb.Mongo;

/**
 * Spring MongoDB configuration file
 *
 */
@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration{

    @Override
    public @Bean Mongo mongo() throws Exception {
        return new Mongo("localhost");
    }

    @Override
    public @Bean MongoTemplate mongoTemplate() throws Exception {

        return new MongoTemplate(mongo(),"test");
    }

    @Override
    public String getDatabaseName() {
        return "test";
    }

    /*
    <bean class="org.springframework.data.document.mongodb.repository.MongoRepositoryFactoryBean">
    <property name="template" ref="template" />
    <property name="repositoryInterface" value="org.springframework.data.document.mongodb.repository.PersonRepository" />
</bean>
*/
    public @Bean UserRepository userRepository() throws Exception{
        MongoRepositoryFactoryBean <UserRepository, User, String> bean = new MongoRepositoryFactoryBean<UserRepository, User, String>();
        bean.setMongoOperations(mongoTemplate());
        bean.setRepositoryInterface(UserRepository.class);
        return bean.getObject();
    }

    public @Bean UserServiceImpl userService() throws Exception {
        return new UserServiceImpl(userRepository());
    }
}
