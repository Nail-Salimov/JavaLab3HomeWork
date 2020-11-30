package ru.itis.mongohateoas.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.mongohateoas.entities.User;
import ru.itis.mongohateoas.repositories.UsersRepository;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User changeSex(String id) {
        Optional<User> optionalUser = usersRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("user not exist");
        }

        User user = optionalUser.get();
        if (user.getSex().equals("Man")) {
            user.setSex("Female");
        }else if(user.getSex().equals("Female")){
            user.setSex("Man");
        }

        usersRepository.save(user);
        return user;
    }
}
