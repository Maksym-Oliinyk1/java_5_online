package ua.com.alevel.service;

import ua.com.alevel.entity.Operation;

import java.util.List;

public interface OperationService {

    void create(Operation operation);

    List<Operation> findAllByAccountFromId(Long accountFromId);

    List<Operation> findAllByAccountToId(Long accountToId);
}
