package ua.com.alevel.service;

import ua.com.alevel.entity.User;

import java.util.List;

public interface UserService {

    void create(User user);

    List<User> findAll();

    void delete(Long userId);

    void deleteAllAccountsById(Long userId);

    User findUserById(Long userId);
}
