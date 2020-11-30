package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.*;

public class UserRepositoryImpl {

    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public List<User> findAllInvalidDataUsers() {

        return mongoTemplate.find(new Query(
                where("surname").exists(false)
                        .orOperator(where("name").exists(false), where("confirmed").is(false))
        ), User.class, "user");
    }

    public void addUser(User user){
        mongoTemplate.save(user);
    }

    public void update(User user){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(user.get_id()));

        Update update = new Update();
        update.set("name", user.getName());
        update.set("surname", user.getSurname());
        update.set("age", user.getAge());
        update.set("auto", user.getAuto());
        update.set("city", user.getCity());
        update.set("sex", user.getSex());

        mongoTemplate.updateFirst(query, update, User.class, "user");
    }

    public void deleteUser(String id){
        mongoTemplate.remove(new Query(where("_id").is(id)), User.class, "user");
    }

}
