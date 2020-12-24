package ru.itis.dsl.repositories;

import com.querydsl.core.BooleanBuilder;
import ru.itis.dsl.dto.UserDto;
import ru.itis.dsl.dto.UserRequest;


import ru.itis.dsl.entities.QUser;
import ru.itis.dsl.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class UsersByRequestRepositoryImpl implements UsersByRequestRepository{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserDto> findByRequest(UserRequest userRequest) {
        BooleanBuilder predicate = new BooleanBuilder();

        if(userRequest.getName() != null){
            predicate.or(QUser.user.name.containsIgnoreCase(userRequest.getName()));
        }

        if(userRequest.getSurname() != null){
            predicate.or(QUser.user.surname.containsIgnoreCase(userRequest.getSurname()));
        }

        assert predicate.getValue() != null;
        Collection<User>  collection = StreamSupport.stream(usersRepository.findAll(predicate.getValue()).spliterator(), false)
                .collect(Collectors.toList());

        List<UserDto> userDtoList = new LinkedList<>();
        for (User user : collection){
            UserDto userDto = new UserDto();
            userDto.setAge(user.getAge());
            userDto.setName(user.getName());
            userDto.setSurname(user.getSurname());
            userDto.setSex(user.getSex());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
