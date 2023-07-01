package ua.com.alevel.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.Operation;

import java.util.List;

@Repository
public interface OperationRepository extends BaseRepository<Operation> {

    List<Operation> findAllByAccountFromId(Long accountFromId);

    List<Operation> findAllByAccountToId(Long accountToId);

    void deleteAllByAccountFromId(Long accountFormId);

    void deleteAllByAccountToId(Long accountToId);
}
