package ua.com.alevel.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Operation;

import java.util.List;

@Repository
public interface AccountRepository extends BaseRepository<Account> {

    List<Account> findAllByUserId(Long userId);

    Account findByNumber(Long number);

    Boolean existsAccountByNumber(Long number);

    void deleteAllByUserId(Long userId);


}
