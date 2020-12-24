package ru.itis.dsl.controllers;

import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import ru.itis.dsl.dto.UserDto;
import ru.itis.dsl.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dsl.entities.User;
import ru.itis.dsl.repositories.UsersByRequestRepository;
import ru.itis.dsl.repositories.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SearchUserController {


    @Autowired
    private UsersByRequestRepository usersByRequestRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/search/user")
    public ResponseEntity<List<UserDto>> searchByRequest(UserRequest userRequest){
        return ResponseEntity.ok(
                usersByRequestRepository.findByRequest(userRequest)
        );
    }

    @GetMapping("/search2/user")
    public ResponseEntity<List<UserDto>> search2ByRequest(@QuerydslPredicate(root = User.class) Predicate predicate){
        return ResponseEntity.ok(
                StreamSupport.stream(usersRepository.findAll(predicate).spliterator(), true)
                .map(user ->
                    UserDto.builder()
                            .name(user.getName())
                            .surname(user.getSurname())
                            .age(user.getAge())
                            ._id(user.get_id())
                            .sex(user.getSex())
                            .build()
                ).collect(Collectors.toList())
        );
    }

}
