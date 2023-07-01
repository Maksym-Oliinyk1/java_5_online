package ua.com.alevel.service.impl;

import jakarta.transaction.Transactional;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ua.com.alevel.entity.User;
import ua.com.alevel.repository.UserRepository;
import ua.com.alevel.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteAllAccountsById(Long userId) {
        userRepository.deleteAllAccountsById(userId);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }
}
