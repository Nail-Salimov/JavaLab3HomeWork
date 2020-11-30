package ru.itis.mongohateoas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.mongohateoas.entities.User;

import java.util.List;

public interface UsersRepository extends MongoRepository<User, String> {

    @RestResource(path="not_confirmed", rel = "not_confirmed")
    @Query(value = "{ confirmed: false })")
    List<User> findAllNotConfirmed();

    @RestResource(path="name-length", rel = "name-length")
    @Query(value = " { name: {$exists: true}, $expr: {$gt: [{$strLenCP: $name }, ?0]}}")
    List<User> findAllWithShortName(@Param("len") int length);

}
