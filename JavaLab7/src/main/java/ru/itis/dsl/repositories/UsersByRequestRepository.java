package ru.itis.dsl.repositories;

import ru.itis.dsl.dto.UserDto;
import ru.itis.dsl.dto.UserRequest;

import java.util.List;

public interface UsersByRequestRepository {
    List<UserDto> findByRequest(UserRequest userRequest);
}
