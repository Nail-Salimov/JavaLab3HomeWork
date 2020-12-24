package ru.itis.dsl.repositories;

import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import ru.itis.dsl.entities.QUser;
import ru.itis.dsl.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UsersRepository extends MongoRepository<User, String>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {

    @Override
    default void customize(QuerydslBindings querydslBindings, QUser qUser) {
        querydslBindings.bind(qUser.name).first(
                StringExpression::containsIgnoreCase
        );

    }
}
