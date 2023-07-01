package ua.com.alevel.service.impl;

import ua.com.alevel.entity.Operation;
import ua.com.alevel.repository.OperationRepository;
import ua.com.alevel.service.OperationService;

import java.util.List;

public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;

    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public void create(Operation operation) {
        operationRepository.save(operation);
    }

    @Override
    public List<Operation> findAllByAccountFromId(Long accountFromId) {
        return operationRepository.findAllByAccountFromId(accountFromId);
    }

    @Override
    public List<Operation> findAllByAccountToId(Long accountToId) {
        return operationRepository.findAllByAccountToId(accountToId);
    }
}
