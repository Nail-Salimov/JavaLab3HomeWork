package spring;

import java.util.List;

public interface UserRepository {

    List<User> findAllInvalidDataUsers();
    void addUser(User user);
    void update(User user);
    void deleteUser(String id);
}
