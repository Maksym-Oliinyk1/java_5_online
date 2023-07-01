package ua.com.alevel.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User> {

    void deleteAllAccountsById(Long userId);
}
