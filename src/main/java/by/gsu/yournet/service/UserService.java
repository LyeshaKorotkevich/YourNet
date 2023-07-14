package by.gsu.yournet.service;

import by.gsu.yournet.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    User findById(Long id);

    void addFriends(User user, String username);
}
